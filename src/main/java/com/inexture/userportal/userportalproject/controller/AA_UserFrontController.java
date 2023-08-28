package com.inexture.userportal.userportalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AA_UserFrontController {

    @RequestMapping("/UserLogin") //DONE
    public ModelAndView redirectToLogin() {
        return new ModelAndView("redirect:/UserLogin");
    }

    @RequestMapping("/UserRegister") //DONE
    public ModelAndView redirectToRegister() {
        return new ModelAndView("redirect:/UserRegister");
    }

    @RequestMapping("/logout") //DONE
    public ModelAndView redirectToLogout() {
        return new ModelAndView("redirect:/logout");
    }

    // ViewPageController calls a servlet that merely redirects the user to viewUsers.jsp, which uses ajax to call viewUserDetailsController.java
    //TODO: ASK SIR ABOUT THE REQUESTMAPPING METHOD IN THIS CONTROLLER
    @RequestMapping("/viewPageController") //DONE
    public ModelAndView redirectToViewPage() {
        return new ModelAndView("redirect:/viewPageController");
    }

    @RequestMapping("/viewuserdetails")
    public ModelAndView redirectToViewUserDetails() {
        return new ModelAndView("redirect:/viewuserdetails");
    }

    @RequestMapping("/UpdateProfile") //DONE
    public ModelAndView redirectToUpdateProfile() {
        return new ModelAndView("redirect:/UpdateProfile");
    }

    @RequestMapping("/AdminEdit") // IN PROGRESS
    public ModelAndView redirectToAdmitEdit() {
        return new ModelAndView("redirect:/AdminEdit");
    }

    @RequestMapping("/DeleteUser") //DONE
    public ModelAndView redirectToDeleteUser() {
        return new ModelAndView("redirect:/DeleteUser");
    }

    @RequestMapping("/ForgotPassword") //DONE
    public ModelAndView redirectToForgotPassword() {
        return new ModelAndView("redirect:/ForgotPassword");
    }

}