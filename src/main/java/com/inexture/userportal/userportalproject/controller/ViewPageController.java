package com.inexture.userportal.userportalproject.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

@Controller("/viewuserdetails")
public class ViewPageController {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger("ViewPageController");

    HttpServletRequest req;
    HttpServletResponse res;

    @RequestMapping
    public void service() {
        try {
            RequestDispatcher rd = req.getRequestDispatcher("/viewUsers.jsp");
            rd.forward(req, res);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
        logger.info("Forwarded the flow to the 'viewUsers.jsp' page.");
    }
}
