package com.inexture.userportal.userportalproject.controller;

import com.inexture.userportal.userportalproject.model.User;
import com.inexture.userportal.userportalproject.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/* USING HIDDEN INPUT FIELDS TO CONTROL WHAT METHOD THIS GOES INTO .....
 * IF THE PARAMETER IS EQUAL TO comingFromExists THEN IT WON'T CHECK THE EMAIL AGAIN,
 * IT'LL GO STRAIGHT TO VERIFYING THE VERIFICATION CODE */

@RequestMapping("/ForgotPassword")
@Controller
public class ForgotPasswordController {

    private static final long serialVersionUID = 1L;
    private static Logger logger = LogManager.getLogger("ForgotPasswordController");

    //made them transient as they were non-transient 'non-serializable instances' in a serializable class
    @Autowired
    UserService userServiceObject;
//    User user = new User(); //commenting this as im using the ModelAttribute with the User entity's object
    HttpSession session;
    HttpServletRequest request;
    HttpServletResponse response;
    int randomNumber;

    public ForgotPasswordController() throws SQLException {
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView userCheckPassword(@ModelAttribute("userModelAttribute") User user,
                                          @RequestParam("emailid") String emailid, @RequestParam("pageIdentification") String pageIdentification) {
        ModelAndView modelAndView = new ModelAndView();

        /*retrieve the current session, and ONLY if one doesn't exist yet, create it*/
        session = null;
        if (session == null) {
            session = request.getSession(true); //basically the same as request.getSession() or rq.getSess(true)
        }
        //random number between 0 and 1 is generated, then multiplied manually by 10^6 to get a six digit number
        if (randomNumber == 0) {
            randomNumber = (int) Math.floor((Math.random()) * Math.pow(10, 6)); //for verification code
        }

        //check if email exists, if it does, send the verification codes
        if (pageIdentification.equals("emailDoesntExist") || pageIdentification.equals("getVerificationCode")) {
            if (userServiceObject.checkEmail(emailid)) { //if emailid EXISTS in the database
                user.setUserEmailID(emailid);
                session.setAttribute("CurrentUser", user);
        /* For the above User class' 'user' object being stored in the session... the error you're encountering, "Store of non serializable object into HttpSession," is related to storing
        non-serializable objects in an HttpSession. This can potentially cause issues when the session needs to be
        serialized and persisted, such as when the server restarts or when sessions are migrated between different instances.
        Let's break down the issue and discuss how to address it: CurrentUser, and AddressList

        * In your UserLoginController servlet, you are storing the User and List<Address> objects in the session:

        * By implementing the Serializable interface, you are telling Java that instances of these classes can be
         safely serialized and deserialized, which prevents potential issues when storing them in the session.

        * Additionally, make sure that any other classes you use in your servlet that are stored in the session also
         implement Serializable if they are part of the session attributes.
        */
                session.setAttribute("VerificationCode", randomNumber);

                logger.info("the Verification Code is : " + session.getAttribute("VerificationCode"));
                //response.sendRedirect(request.getContextPath() + "/forgotPassword.jsp?emailExists=exists");
                try {
                    modelAndView.addObject("emailExists", "exists");
                    modelAndView.setViewName("forgotPassword.jsp");

//                    RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp?emailExists=exists");
//                    rd.forward(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else if (!userServiceObject.checkEmail(emailid)) { // if emailid DOESN'T EXIST in the database
                //response.sendRedirect(request.getContextPath() +  "/forgotPassword.jsp?emailExists=doesNotExist");
                try {
                    modelAndView.addObject("emailExists", "doesNotExist");
                    modelAndView.setViewName("forgotPassword.jsp");

//                    RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp?emailExists=doesNotExist");
//                    rd.forward(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (pageIdentification.equals("sendingVerificationCode")) {
            //verification code check, and redirection to get new password
            String verificationcode = request.getParameter("verificationcode");
//        if (!(verificationcode == null) && !(verificationcode.equals(""))) {
            if (verificationcode.equals(String.valueOf(session.getAttribute("VerificationCode")))) { //if Verification Code matches
                //response.sendRedirect(request.getContextPath() + "/forgotPassword.jsp?emailExists=getNewPassword");
                try {
                    modelAndView.addObject("emailExists", "getNewPassword");
                    modelAndView.setViewName("forgotPassword.jsp");
                    //                    RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp?emailExists=getNewPassword");
//                    rd.forward(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                //session.setAttribute("CurrentUser", user);
            }
            // if verification code is wrong
            else if (!verificationcode.equals(String.valueOf(session.getAttribute("VerificationCode")))) {
                //response.sendRedirect(request.getContextPath() + "/forgotPassword.jsp?emailExists=exists");
                try {
                    modelAndView.addObject("emailExists", "exists");
                    modelAndView.setViewName("forgotPassword.jsp");

//                    RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp?emailExists=exists");
//                    rd.forward(request, response);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
//        }
        }
        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView userChangePassword(@ModelAttribute("userModelAttribute") User user,
                                  @RequestParam("newpassword") String newPassword, @RequestParam("pageIdentification") String pageIdentification) {

        ModelAndView modelAndView = new ModelAndView();

        try {
            if (pageIdentification.equals("sendingNewPassword")) {
                /*getting the new password from frontend*/
                user.setUserPassword(newPassword);
                userServiceObject.updatePassword(user);

                modelAndView.addObject("emailExists", "passwordChangedSuccessfully");
                modelAndView.setViewName("forgotPassword.jsp");
//                RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp?emailExists=passwordChangedSuccessfully");
//                rd.forward(request, response);
                session.invalidate();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return modelAndView;
    }
}
