package com.inexture.userportal.userportalproject.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Deprecated
@Controller
public class AA_UserFrontController {
    private static final Logger logger = LogManager.getLogger("FrontController");

    @RequestMapping("/UserLogin") //DONE
    public ModelAndView redirectToLogin() {
        return new ModelAndView("redirect:LoginController");
    }

    @RequestMapping("/UserRegister") //DONE
    public ModelAndView redirectToRegister() {
        return new ModelAndView("redirect:RegistrationController");
    }

    @RequestMapping("/logout") //DONE
    public ModelAndView redirectToLogout() {
        return new ModelAndView("redirect:LogoutController");
    }

    // ViewPageController calls a servlet that merely redirects the user to viewUsers.jsp, which uses ajax to call viewUserDetailsController.java
    //TODO: ASK SIR ABOUT THE REQUESTMAPPING METHOD IN THIS CONTROLLER
    @RequestMapping("/view-page") //DONE
    public ModelAndView redirectToViewPage() {
        return new ModelAndView("redirect:ViewPage");
    }

    @RequestMapping("/viewuserdetails")
    public ModelAndView redirectToViewUserDetails() {
        return new ModelAndView("redirect:ViewUserDetailsController");
    }

    @RequestMapping("/UpdateProfile") //DONE
    public ModelAndView redirectToUpdateProfile() {
        return new ModelAndView("redirect:UpdateController");
    }

    @RequestMapping("/AdminEdit") // IN PROGRESS
    public ModelAndView redirectToAdmitEdit() {
        return new ModelAndView("redirect:adminEditController");
    }

    @RequestMapping("/DeleteUser") //DONE
    public ModelAndView redirectToDeleteUser() {
        return new ModelAndView("redirect:DeleteUserController");
    }

    @RequestMapping("/ForgotPassword") //DONE
    public ModelAndView redirectToForgotPassword() {
        return new ModelAndView("redirect:forgotPassword");
    }

}