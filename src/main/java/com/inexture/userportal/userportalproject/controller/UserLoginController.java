package com.inexture.userportal.userportalproject.controller;

import com.inexture.userportal.userportalproject.model.Address;
import com.inexture.userportal.userportalproject.model.User;
import com.inexture.userportal.userportalproject.services.AddressService;
import com.inexture.userportal.userportalproject.services.UserService;
import com.inexture.userportal.userportalproject.utility.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

//@RequestMapping("/UserLogin") mapped directly to the method below
@Controller
public class UserLoginController {
    private static final Logger logger = LogManager.getLogger(UserLoginController.class);

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;


    @RequestMapping(value = "/UserLogin", method = RequestMethod.POST)
    public ModelAndView userLogin(@ModelAttribute User user, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();


        modelAndView.setViewName("login.jsp"); //default view in case of authentication failure

        try {
            String encryptedPassword = PasswordEncryption.encrypt(user.getUserPassword());
            user.setUserPassword(encryptedPassword);
            boolean isValid = userService.compareUserLogin(user);

            if (isValid) {
                if (user.getUserStatus()) { //admin
                    List<User> adminList = userService.displayAdmin(user);
                    int id = user.getUserId();
                    List<Address> addressList = addressService.getAllAddress(id);

                    session.setAttribute("userRole", "admin");
                    session.setAttribute("adminList", adminList);
                    session.setAttribute("CurrentUser", user);
                    session.setAttribute("AddressList", addressList);

                    modelAndView.setViewName("adminHomePage.jsp");
                } else { //normal user
                    User specificUser = userService.displaySpecificUser(user);
                    int id = specificUser.getUserId();

                    session.setAttribute("userRole", "user");
                    session.setAttribute("CurrentUser", user);
                    session.setAttribute("specificUserData", specificUser);

                    List<Address> addressList = addressService.getAllAddress(id);
                    session.setAttribute("AddressList", addressList);

                    modelAndView.setViewName("userHomePage.jsp");
                }
            } else {
                modelAndView.setViewName("login.jsp?authenticationStatus=wrong");
//                modelAndView.addObject("authenticationStatus", "wrong");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            modelAndView.addObject("authenticationStatus", "error");
        }

        return modelAndView;
    }
}
