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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@MultipartConfig
public class UserRegisterController {

    private static final Logger logger = LogManager.getLogger("UserRegisterController");

    @Autowired
    UserService service;
    @Autowired
    AddressService addservice;

    @RequestMapping(value = "/UserRegister", method = RequestMethod.GET)
    public ModelAndView userRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration.jsp");
        modelAndView.addObject(new User());
        return modelAndView;
    }

    @RequestMapping(value = "/UserRegister", method = RequestMethod.POST)
    @ModelAttribute
    public ModelAndView userRegister(Model model,
                                     @ModelAttribute User user)
//                                     @RequestParam("houseno[]") String[] houseno,
//                                     @RequestParam("address[]") String[] street,
//                                     @RequestParam("landmark[]") String[] landmark,
//                                     @RequestParam("zipcode[]") String[] zipcode,
//                                     @RequestParam("city[]") String[] city,
//                                     @RequestParam("state[]") String[] state,
//                                     @RequestParam("country[]") String[] country,
//                                     @RequestParam("postaladdress[]") String[] postaladdress)
                                     {
//

        ModelAndView modelAndView = new ModelAndView();
                                         System.out.println("test" + user.toString());

//        model.addAttribute();
//        int id = 0;
//        List<String> validationErrorsArrayList; // to be shown on registration.jsp
//        Map<String, String> messages = new HashMap<>();
//        modelAndView.addObject("messages", messages);
//
////        user.setUserAge(AgeCalculator.calculateAge(user.getUserDOB()));
//        String encryptedPassword = PasswordEncryption.encrypt(user.getUserPassword());
//        user.setUserPassword(encryptedPassword);
//        String encryptedConfirmPassword = PasswordEncryption.encrypt(user.getUserConfirmPassword());
//        user.setUserConfirmPassword(encryptedConfirmPassword);
//
//        //call the validation method
//        validationErrorsArrayList = ValidateOnServerSide.validateUser(user);
//        //checking if the Email-ID is unique, it's a part of the Validation On Server Side
//        if (service.checkEmail(user.getUserEmailID())) {
//            validationErrorsArrayList.add("Email ID already exists. Use a different Email-ID.");
//        }
//
//        //just to check if things are being stored properly
//        logger.info(user.toString());
//
//        if (validationErrorsArrayList.isEmpty()) {
//            //the userRegister method returns the (generated) ID of the user post saving the user object into the database
//            id = service.userRegister(user);
//        }
//
//        // Saving every single address into the database
//        int count = 0;
//        int initialaddID = 0;
//        while (count < street.length) {
//            Address addressObject = new Address();
//            /*validating while setting values*/
//            addressObject.setAddId(String.valueOf(++initialaddID));
//            addressObject.setAddHouseNo(houseno[count]);
//            addressObject.setAddStreet(street[count]);
//            addressObject.setAddLandmark(landmark[count]);
//            addressObject.setAddCity(city[count]);
//            addressObject.setAddState(state[count]);
//            addressObject.setAddZipcode(zipcode[count]);
//            addressObject.setAddCountry(country[count]);
//            addressObject.setAddPostalAdd(postaladdress[count]);
//
//            logger.info("validationErrorsArrayList BEFORE going to validateAddress : " + validationErrorsArrayList);
//
//            //adding the address ERRORS in the List
//            validationErrorsArrayList.addAll(ValidateOnServerSide.validateAddress(addressObject));
//
//            logger.info("validationErrorsArrayList AFTER going to validateAddress : " + validationErrorsArrayList);
//
//            //checking if there are validation errors
//            if (!validationErrorsArrayList.isEmpty()) {
//                //if there are errors, send them back to the registration page
//                modelAndView.addObject("errors", validationErrorsArrayList);
//                modelAndView.setViewName("registration.jsp");
//                validationErrorsArrayList.clear();
//                return modelAndView;
//            } else {
//                addressObject.setAddUserObject(user);
//                addservice.addAddress(id, addressObject);
//                // save the address in the database
//                ++count;
//                /*I AM YET TO ADD THE ROLLBACK FEATURE FOR IF 5 OUTTA 10 ADDRS ARE GOOD, AND THE 6TH IS BAD, DONT SAVE THE FIVE*/
//            }
//        }
//
//        //redirect back to the login page after registration
//        modelAndView.setViewName("login.jsp");
        return modelAndView;
    }
}
