package com.inexture.userportal.userportalproject.controller;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.inexture.userportal.userportalproject.utility.PasswordEncryption;
import com.inexture.userportal.userportalproject.utility.ValidateOnServerSide;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.inexture.userportal.userportalproject.model.Address;
import com.inexture.userportal.userportalproject.model.User;
import com.inexture.userportal.userportalproject.services.AddressService;
import com.inexture.userportal.userportalproject.services.AddressServiceImp;
import com.inexture.userportal.userportalproject.services.UserService;
import com.inexture.userportal.userportalproject.services.UserServiceImp;
import com.inexture.userportal.userportalproject.utility.AgeCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@MultipartConfig
@Controller("/UserRegister")
public class UserRegisterController {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LogManager.getLogger("UserRegisterController");
    HttpServletRequest request;
    HttpServletResponse response;
    @Autowired
    UserService service;
    @Autowired
    AddressService addservice;

    public UserRegisterController() {
        super();
    }

//    transient Connection c; //made them transient as they were non-transient 'non-serializable instances' in a serializable class


    @RequestMapping(method = RequestMethod.POST)
    public void userLogin(@ModelAttribute("userModelAttribute") User user,
                          @RequestParam("hosueno[]") String[] houseno, @RequestParam("address[]") String[] street,
                          @RequestParam("landmark[]") String[] landmark, @RequestParam("zipcode[]") String[] zipcode,
                          @RequestParam("city[]") String[] city, @RequestParam("state[]") String[] state,
                          @RequestParam("country[]") String[] country, @RequestParam("postaladdress[]") String[] postaladdress) {

//        HttpSession sessionInvalidate = request.getSession();
//        sessionInvalidate.invalidate();

        int id = 0;
        try {
            service = new UserServiceImp();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
                //User user = new User(); //commenting this as I'm using ModelAttribute but the user entity's object.
        Map<String, String> messages = new HashMap<String, String>();
        request.setAttribute("messages", messages);
        List<String> validationErrorsArrayList; // to be shown on registration.jsp


        user.setUserAge(AgeCalculator.calculateAge(user.getUserDOB()));
        String EncryptedPassword = PasswordEncryption.encrypt(user.getUserPassword());
        user.setUserPassword(EncryptedPassword);
        String EncryptedConfirmPassword = PasswordEncryption.encrypt(user.getUserConfirmPassword());
        user.setUserConfirmPassword(EncryptedConfirmPassword);


        //call the validation method here
        validationErrorsArrayList = ValidateOnServerSide.validateUser(user);
        //Checking if the Email-ID is unique, it's a part of the Validation On Server Side
        if (service.checkEmail(user.getUserEmailID())) {
            validationErrorsArrayList.add("Email ID already exists. Use a different Email-ID.");
        }

        //printing the user details that i've got
        logger.info(user.toString());

        // Check if there are validation errors
        if (validationErrorsArrayList.isEmpty()) {
            // If there are NO errors, save the user-details in the database
            id = service.userRegister(user); /*this line submits the code into the database, and returns the userID*/
        }


        /* saving every single address into the database */
        int count = 0;
        int initialaddID = 0;
        while (count < street.length) {
            Address addobj = new Address();
            /*validating while setting values*/
            /*house no*/
            addobj.setAddId(String.valueOf(++initialaddID));
            addobj.setAddHouseNo(houseno[count]);
            addobj.setAddStreet(street[count]);
            addobj.setAddLandmark(landmark[count]);
            addobj.setAddCity(city[count]);
            addobj.setAddState(state[count]);
            addobj.setAddZipcode(zipcode[count]);
            addobj.setAddCountry(country[count]);
            addobj.setAddPostalAdd(postaladdress[count]);
            logger.info("validationErrorsArrayList BEFORE going to validateAddress : " + validationErrorsArrayList);

            //adding the address ERRORS in the List
            validationErrorsArrayList.addAll(ValidateOnServerSide.validateAddress(addobj));

            // Check if there are validation errors
            if (!validationErrorsArrayList.isEmpty()) {
                // If there are errors, send them back to the registration page
                request.setAttribute("errors", validationErrorsArrayList);
                logger.info("validationErrorsArrayList AFTER going to validateAddress : " + validationErrorsArrayList);
                try {
                    request.getRequestDispatcher("/registration.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
                validationErrorsArrayList.clear();
                return;
            } else {
//                addobj.setAddUserId_User(user);
                addobj.setAddUserObject(user);
                addservice.addAddress(id, addobj);
                // save the address in the database
                ++count;
                /*I AM YET TO ADD THE ROLLBACK FEATURE FOR IF 5 OUTTA 10 ADDRS ARE GOOD, AND THE 6TH IS BAD, DONT SAVE THE FIVE*/
            }

        }

        //printing the address(es) that i've got
        Address addModel = new Address();
        logger.info(addModel.toString());

        HttpSession session = request.getSession();
        String uName = (String) session.getAttribute("userFirstname");
        logger.info("uName" + uName);
        //after registration is done, redirect back to the login page
        try {
            response.sendRedirect("login.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}