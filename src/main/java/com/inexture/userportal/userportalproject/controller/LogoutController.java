package com.inexture.userportal.userportalproject.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/logout")
@Controller
public class LogoutController {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger("LogoutController");

    int callSequence = 0;

    public LogoutController() {
        super();
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ModelAndView doGet(HttpServletRequest request) {
        doPost(request);
        logger.info("LogoutController.java : doGet was called in Sequence " + ++callSequence);

        // to invalidate the session
        HttpSession session = request.getSession(false); // get the existing session

        assert session != null; //to avoid a null-pointer exception
        session.setAttribute("userRole", null); //login.jsp checks for this
        logger.info("value of userRole : " + session.getAttribute("userRole"));
        session.removeAttribute("userRole");
        logger.info("value of userRole : " + session.getAttribute("userRole"));
        session.invalidate(); //removes session from the registry

        ModelAndView modelAndView = new ModelAndView("login.jsp");
        modelAndView.addObject("userRole", null);

        //setting the values of response object and setHeader methods into the modelAndView.addObject() methodsy
        modelAndView.addObject("Cache-Control", "no-cache");
        modelAndView.addObject("Cache-Control", "no-store");
        modelAndView.addObject("Cache-Control", "no-cache, no-store, must-revalidate");
        modelAndView.addObject("Expires", 0);
        modelAndView.addObject("Pragma", "no-cache");

        return modelAndView;    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doPost(HttpServletRequest request) {

        logger.info("LogoutController.java : doPost was called in Sequence " + ++callSequence);

        // to invalidate the session
        HttpSession session = request.getSession(false); // get the existing session

        assert session != null; //to avoid a null-pointer exception
        session.setAttribute("userRole", null); //login.jsp checks for this
        logger.info("value of userRole : " + session.getAttribute("userRole"));
        session.removeAttribute("userRole");
        logger.info("value of userRole : " + session.getAttribute("userRole"));
        session.invalidate(); //removes session from the registry

        ModelAndView modelAndView = new ModelAndView("login.jsp");
        modelAndView.addObject("userRole", null);

        //setting the values of response object and setHeader methods into the modelAndView.addObject() methodsy
        modelAndView.addObject("Cache-Control", "no-cache");
        modelAndView.addObject("Cache-Control", "no-store");
        modelAndView.addObject("Cache-Control", "no-cache, no-store, must-revalidate");
        modelAndView.addObject("Expires", 0);
        modelAndView.addObject("Pragma", "no-cache");

        return modelAndView;
    }
}
