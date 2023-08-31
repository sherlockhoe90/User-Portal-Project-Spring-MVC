package com.inexture.userportal.userportalproject.controller;

import com.inexture.userportal.userportalproject.dao.UserDAOImp;
import com.inexture.userportal.userportalproject.model.Address;
import com.inexture.userportal.userportalproject.model.User;
import com.inexture.userportal.userportalproject.services.AddressService;
import com.inexture.userportal.userportalproject.services.AddressServiceImp;
import com.inexture.userportal.userportalproject.services.UserService;
import com.inexture.userportal.userportalproject.services.UserServiceImp;
import com.inexture.userportal.userportalproject.utility.AgeCalculator;
import com.inexture.userportal.userportalproject.utility.ValidateOnServerSide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/UpdateProfile")
@MultipartConfig
@Controller
public class UpdateProfileController {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = LogManager.getLogger(UserDAOImp.class.getName());
    @Autowired
    UserService service;
    @Autowired
    AddressService addservice;
    HttpServletRequest request;
    HttpServletResponse response;
    public UpdateProfileController() {
        super();
    }

    @RequestMapping(method = RequestMethod.GET)
    public void doGet(@ModelAttribute("modelAttributeObject") User user,
                      @RequestParam("houseno[]") String[] houseno, @RequestParam("address[]") String[] street,
                      @RequestParam("landmark[]") String[] landmark, @RequestParam("zipcode[]") String[] zipcode,
                      @RequestParam("city[]") String[] city, @RequestParam("state[]") String[] state,
                      @RequestParam("country[]") String[] country, @RequestParam("postaladdress[]") String[] postaladdress) {
        updateMethod(user, houseno, street, landmark, zipcode, city, state, country, postaladdress);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void updateMethod(@ModelAttribute("modelAttributeObject") User modelAttributeUser,
                             @RequestParam("houseno[]") String[] houseno, @RequestParam("address[]") String[] street,
                             @RequestParam("landmark[]") String[] landmark, @RequestParam("zipcode[]") String[] zipcode,
                             @RequestParam("city[]") String[] city, @RequestParam("state[]") String[] state,
                             @RequestParam("country[]") String[] country, @RequestParam("postaladdress[]") String[] postaladdress) {

        try {
            HttpSession session = request.getSession();
            // to get address list
            List<Address> addressesInSession = (List<Address>) session.getAttribute("AddressList");
            // User user = new User();
            User user = (User) session.getAttribute("CurrentUser");
            Address address = new Address();
            List<String> validationErrorsArrayList; // to be shown on registration.jsp
            int id = 0;

//            Part file = request.getPart("img");
//            if (file.getSize() == 0) {
//
//                String base64Image = request.getParameter("oldImage");
//                InputStream targetStream = new ByteArrayInputStream(base64Image.getBytes());
//                InputStream is = Base64.getDecoder().wrap(targetStream);
//                user.setUserProfile(is);
//            } else {
//                InputStream imgContent = file.getInputStream();
//                user.setUserProfile(imgContent);
//            }

            /* to set the updated values */
            /* user part */
            user.setUserFirstname(modelAttributeUser.getUserFirstname());
            user.setUserMiddlename(modelAttributeUser.getUserMiddlename());
            user.setUserLastname(modelAttributeUser.getUserLastname());
            user.setUserEmailID(modelAttributeUser.getUserEmailID());
            user.setUserUsername(modelAttributeUser.getUserUsername());
            user.setUserDOB(modelAttributeUser.getUserDOB());
            user.setUserAge(AgeCalculator.calculateAge(modelAttributeUser.getUserDOB()));
            user.setUserHobbies(modelAttributeUser.getUserHobbies());

            validationErrorsArrayList = ValidateOnServerSide.validateUser(user);
            //removing the below error from the list as there's no Confirm-Password input on the Edit Page in the first place
            validationErrorsArrayList.remove("Confirm password is either empty, or the passwords do not match.");
            if (validationErrorsArrayList.isEmpty()){
                id = service.updateProfile(user);
            } else {
                User tempUser = new User();
                tempUser.setUserEmailID(user.getUserEmailID());
                id = service.updateProfile(tempUser);
            }

            /* address part */
            // addressIdFromDatabase contains the addresses existing in the database
            String addressIdFromDatabase[] = new String[addressesInSession.size()];
            for (int i = 0; i < addressesInSession.size(); i++) {
                addressIdFromDatabase[i] = addressesInSession.get(i).getAddId();
            }
            // addressId contains the newly gotten addresses IDs from the frontend
            String[] addressId = request.getParameterValues("addressId[]");
            List<String> addressIdListFromFrontend = Arrays.asList(addressId);
            // takes the addresses from the frontend, and checks if it consists of the addresses present in the database,
            //if not, it removes those addresses from the database, that are not found in the frontend
            StringBuilder removeBuilder = new StringBuilder(); //using a String builder as suggested by SpotBugs, cuz String is immutable and hence quadruples the cost with each for loop
            for (int i = 0; i < addressIdFromDatabase.length; i++) {
                if (!addressIdListFromFrontend.contains(addressIdFromDatabase[i])) {
                    removeBuilder.append(addressIdFromDatabase[i]).append(" ");
                }
            }
            String remove = removeBuilder.toString();

            // deleted the commented code for adding the new addresses that were added along with editing and removing addresses
            // from registration.jsp?adminEdit or userEdit, etc


            // to get the updated values
            //got the values from ModelAttribute

            int count = 0;
            while (count < street.length) {

                // to set the updated values
                address.setAddId(addressId[count]);
                address.setAddHouseNo(houseno[count]);
                address.setAddStreet(street[count]);
                address.setAddLandmark(landmark[count]);
                address.setAddCity(city[count]);
                address.setAddState(state[count]);
                address.setAddZipcode(zipcode[count]);
                address.setAddCountry(country[count]);
                address.setAddPostalAdd(postaladdress[count]);
                address.setRemoveAddressId(remove);

                validationErrorsArrayList.addAll(ValidateOnServerSide.validateAddress(address));
                if (validationErrorsArrayList.isEmpty()){
                    /* update address function called */
                    addservice.updateAddress(address, id);
                    logger.info("address values inside update servlet" + address);
                }
                count++;
            }
            /* end of updating existing addresses (included: deleting the removed addresses from the database.) */


            /* Add new addresses */
            String[] newHouseno = houseno;
            String[] newStreet = street;
            String[] newLandmark = landmark;
            String[] newZipcode = zipcode;
            String[] newCity = city;
            String[] newState = state;
            String[] newCountry = country;
            String[] newPostaladdress = postaladdress;

            int lastNonEmptyId = 0;
//            for (int i = 0; i < existingAddressIds.length; i++) {
            for (int i = 0; i < addressId.length; i++) {
                if (!addressId[i].equals("")) {
                    int newAddressId = Integer.parseInt(addressId[i]);
                    if (newAddressId > lastNonEmptyId) {
                        lastNonEmptyId = newAddressId;
                    }
                }
            }

            /* getting the new addresses, and giving them the ID after the last non-empty addressID */
            for (int i = 0; i < newHouseno.length; i++) {
                if (addressId[i].equals("")) {
                    Address newAddress = new Address();
                    lastNonEmptyId++;
                    newAddress.setAddId(String.valueOf(lastNonEmptyId));
                    newAddress.setAddHouseNo(newHouseno[i]);
                    newAddress.setAddStreet(newStreet[i]);
                    newAddress.setAddLandmark(newLandmark[i]);
                    newAddress.setAddCity(newCity[i]);
                    newAddress.setAddState(newState[i]);
                    newAddress.setAddZipcode(newZipcode[i]);
                    newAddress.setAddCountry(newCountry[i]);
                    newAddress.setAddPostalAdd(newPostaladdress[i]);

                    validationErrorsArrayList.addAll(ValidateOnServerSide.validateAddress(newAddress));
                    if (validationErrorsArrayList.isEmpty()){
                        /* update address function called */
                        addservice.addAddress(id, newAddress);
                        logger.info("address values inside update servlet" + address);
                    }
                }
            }
            /* end of adding new addresses */

            String uName = (String) session.getAttribute("userName"); //gets this from the JS code on top of registration.jsp
            if (uName.equals("adminEdit")) {
                if (!validationErrorsArrayList.isEmpty()){
                    request.setAttribute("errors", validationErrorsArrayList);
                    request.getRequestDispatcher("/registration.jsp?user=adminEdit").forward(request, response);
                } else {
                    response.sendRedirect("adminHomePage.jsp");
                }
            } else if (uName.equals("userEdit")) {
                User userList = service.displaySpecificUser(user);
                session.setAttribute("specificUserData", userList);
                List<Address> listAddress = addservice.getAllAddress(id);
                session.setAttribute("AddressList", listAddress);
                // session.setAttribute("CurrentUser", user);
                // response.sendRedirect("UserHomePage.jsp");
                if (!validationErrorsArrayList.isEmpty()){
                    request.setAttribute("errors", validationErrorsArrayList);
                    request.getRequestDispatcher("/registration.jsp?user=userEdit").forward(request, response);
                } else {
                    RequestDispatcher req = request.getRequestDispatcher("userHomePage.jsp");
                    req.include(request, response);
                }
            } else if (uName.equals("admin")) {
                List<User> adminList = service.displayAdmin(user);
                session.setAttribute("adminList", adminList);
                List<Address> listAddress = addservice.getAllAddress(id);
                session.setAttribute("AddressList", listAddress);
                if (!validationErrorsArrayList.isEmpty()){
                    request.setAttribute("errors", validationErrorsArrayList);
                    request.getRequestDispatcher("/registration.jsp?user=admin").forward(request, response);
                    /*doubtful about putting userEdit on this link, check it again. 26/07/2023 today */
                } else {
                    response.sendRedirect("adminHomePage.jsp");

                }
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
