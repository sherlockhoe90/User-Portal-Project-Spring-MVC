package com.inexture.userportal.userportalproject.controller;

import com.inexture.userportal.userportalproject.model.User;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping("LogoutController")
@Controller
public class LogoutController {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger("LogoutController");

    HttpServletRequest request;
    HttpServletResponse response;
    int callSequence = 0;
    public LogoutController() {
        super();
    }

    @RequestMapping(method = RequestMethod.GET)
    protected void doGet() {
        doPost();
        logger.info("LogoutController.java : doGet was called in Sequence " + ++callSequence);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void doPost() {

        logger.info("LogoutController.java : doPost was called in Sequence " + ++callSequence);

        // to invalidate the session
        HttpSession session = request.getSession(false); //get the existing session
        /* the following code doesnt really work in a servlet, but it does work when placed inside a JSP */
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");

        session.setAttribute("userRole", null); //login.jsp checks for this
        logger.info("value of userRole : " + session.getAttribute("userRole"));
        session.removeAttribute("userRole");
        logger.info("value of userRole : " + session.getAttribute("userRole"));
        session.invalidate(); //removes session from the registry

        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher req = request.getRequestDispatcher("login.jsp");
            req.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

