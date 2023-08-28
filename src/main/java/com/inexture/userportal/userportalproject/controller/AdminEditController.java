package com.inexture.userportal.userportalproject.controller;

import com.inexture.userportal.userportalproject.model.Address;
import com.inexture.userportal.userportalproject.model.User;
import com.inexture.userportal.userportalproject.services.AddressService;
import com.inexture.userportal.userportalproject.services.AddressServiceImp;
import com.inexture.userportal.userportalproject.services.UserService;
import com.inexture.userportal.userportalproject.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller("/AdminEdit")
public class AdminEditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminEditController() {
        super();
    }

    HttpServletResponse response;
    HttpServletRequest request;
    @Autowired
    UserService service;
    @Autowired
    AddressService addService;


    @RequestMapping(method = RequestMethod.GET)
    public void doGet(@ModelAttribute("modelAttributeObject") User modelAttributeUser) {
        doPost(modelAttributeUser);

    }


    @RequestMapping(method = RequestMethod.POST)
    public void doPost(@ModelAttribute("modelAttributeObject") User modelAttributeUser) {
        HttpSession session = request.getSession();
        // String uName = (String) session.getAttribute("userName");
        try {

//            String userId = request.getParameter("userId");
            String userId = String.valueOf(modelAttributeUser.getUserId());

            // to get details of a particular user
            List<User> userData = service.getUserDetails(userId);
            User user = userData.get(0); //getting the details of the user in the object
            session.setAttribute("CurrentUser", user); // setting the object into the session
            List<Address> listAddress = addService.getAllAddress(Integer.parseInt(userId));
            session.setAttribute("AddressList", listAddress);
            RequestDispatcher req = request.getRequestDispatcher("registration.jsp?user=adminEdit");
            req.forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}

