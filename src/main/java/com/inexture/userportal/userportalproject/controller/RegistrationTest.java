package com.inexture.userportal.userportalproject.controller;

import com.inexture.userportal.userportalproject.model.User;
import com.inexture.userportal.userportalproject.model.UserTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationTest {

    private static final Logger logger = LogManager.getLogger("RegistrationTest");

    @RequestMapping(value = "/registration-test", method = RequestMethod.GET)
    public ModelAndView registerTest(){
        logger.debug("the page has been request.");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registrationTest.jsp");
        modelAndView.addObject(new UserTest());
        return modelAndView;
    }

    @RequestMapping(value = "/registration-test", method = RequestMethod.POST)
    public ModelAndView userRegisterTest(@ModelAttribute("userTest") UserTest user, BindingResult result, Model model)
    {
        logger.info("the modelAttribute object" + user);
        logger.info("the model object" + model);
        logger.info("the binding result object" + result);

        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", user);
        modelAndView.setViewName("login.jsp");
        return modelAndView;
    }

}
