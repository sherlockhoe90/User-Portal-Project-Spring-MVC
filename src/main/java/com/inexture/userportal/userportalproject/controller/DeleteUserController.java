package com.inexture.userportal.userportalproject.controller;

import com.inexture.userportal.userportalproject.model.User;
import com.inexture.userportal.userportalproject.services.UserService;
import com.inexture.userportal.userportalproject.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@RequestMapping("DeleteUserController")
@RestController
public class DeleteUserController {

    private static final long serialVersionUID = 1L;
    HttpServletRequest request;
    HttpServletResponse response;
    @Autowired
    UserService serviceobj;


    public DeleteUserController() {
        super();
    }


    @RequestMapping(method = RequestMethod.GET)
    public void doGet(@ModelAttribute("modelAttributeObject") User user) {
        try {
            response.getWriter().append("Served at: ").append(request.getContextPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(method = RequestMethod.POST)
    public void doPost(@ModelAttribute("modelAttributeObject") User user) {

        String userId = request.getParameter("userId");
//        doGet(request, response);
        try {
            serviceobj = new UserServiceImp();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            // to delete the user
            serviceobj.deleteUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
