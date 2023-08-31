<%--
  Created by IntelliJ IDEA.
  User: ND
  Date: 02/07/2023
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%
    String userRole = (String) session.getAttribute("userRole");
    /*the code is implemented below further inside this code file, all of it is written in scriptlets '< % % >'*/
    /* when userRole is null, only the plain old registration fields will be shown */
    /* when the userRole has a role logged in, it'll rely on the Filter in order to authorize access */
%>
<%
    // Set cache control directives to prevent caching of the page
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies
%>

<%@ page import="com.inexture.userportal.userportalproject.controller.UserRegisterController" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ include file="./WEB-INF/views/header.jsp" %>

<%@ page import="com.inexture.userportal.userportalproject.model.User" %>
<%@ page import="com.inexture.userportal.userportalproject.model.Address" %>
<c:set var="profile" scope="request" value='<%=request.getParameter("user")%>'/>
<%
    User user = (User) session.getAttribute("CurrentUser");
    String userName = request.getParameter("user"); //only this and the next line is useful
    session.setAttribute("userName", userName); // this one. this is used when Updating values from UpdateProfileController
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Registration Form</title>
    <link rel="icon" href="assets/images/inexture-favicon-purple.png" type="image/x-icon">

    <link href="./assets/fonts/Poppins-Regular.ttf" rel="script"/>
    <link href="./assets/fonts/Roboto.css" rel="script"/>
    <link href="./assets/css/CDN/jquery_app_jqueryscripttop.css" rel="stylesheet" type="text/css">
    <link href="./assets/css/CDN/font_awesome_free.css" rel="stylesheet">
    <link href="./assets/css/CDN/font_awesome_pro.css" rel="stylesheet">
    <link rel="stylesheet" href="./assets/css/CDN/bootswatch_MINTY_bootstrap.min.css" type="text/css">
    <link rel="stylesheet" type="text/css"
          href="./assets/css/CDN/bootstrap-datepicker_1.6.4.min.css">
    <link rel="stylesheet" type="text/css" href="./assets/css/CDN/select2_3.3.2.min.css">
    <link rel="stylesheet" type="text/css"
          href="./assets/css/CDN/bootstrap-select_1.13.18.css">
    <%--font awesome--%>
    <link rel="stylesheet"
          href="./assets/css/CDN/http_cdnjs.cloudflare.com_ajax_libs_font-awesome_4.7.0_css_font-awesome.css">
    <!-- Bootstrap -->
    <link rel="stylesheet" href="./assets/css/CDN/bootstrap_4.0.0.css">
    <link href="./assets/css/CDN/bootstrap_5.1.3.css" rel="stylesheet">
    <link rel="stylesheet" href="./assets/css/CDN/bootstrap_3.3.7.css">
    <link href="assets/css/header_and_footer.css" rel="stylesheet" type="text/css"/>
    <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>

    <%--PREVENTS USER FROM COMING BACK AFTER HE/SHE HAS LOGGED OUT--%>
    <script>
        // Check if the page is loaded from the cache
        const navigationEntries = window.performance.getEntriesByType("navigation");
        if (navigationEntries.length && navigationEntries[0].type === "back_forward") {
            // Page is loaded from the cache, redirect to the login page
            window.location.href = "login.jsp";
        }
    </script>


</head>

<body class="bg-info bg_custom_color">
<!-- Display validation errors, if any -->
<c:if test="${not empty errors}">
    <ul>
        <c:forEach items="${errors}" var="error">
            <li class="backendValidationError">${error}</li>
        </c:forEach>
    </ul>
    <%-- Remove the 'errors' attribute from the request scope --%>
    <% request.removeAttribute("errors"); %>
</c:if>

<%--check whether the user is admin or normal user
and whether he/she wants to edit/add information --%>
<c:choose>
    <c:when test="${(profile == 'admin') || (profile == 'adminEdit')}">
        <c:set var="user" scope="session" value="${sessionScope.CurrentUser}"/>
        <%--        <c:set var="address" scope="session" value="${sessionScope.AddressList}"/>--%>
    </c:when>
    <c:when test="${(profile == 'userEdit')}">
        <c:set var="user" scope="session" value="${sessionScope.specificUserData}"/>
    </c:when>
</c:choose>


<div class="container">

    <form:form action="UserRegister" method="post" modelAttribute="user" enctype="multipart/form-data"
               id="registration_form">


    <h3 style="text-align: center; font-family: 'Poppins Regular'" class="margin_top_file header_tag">Registration
        Happens Here!</h3>
    <c:choose>
    <c:when
            test="${(profile == 'admin') || (profile == 'userEdit') || (profile == 'adminEdit')}">
            <%
            if (userRole != null) {

        %>
    <div class="row form-row">
        <div class="col-md-6">

            <div class="form-group">
            </div>

        </div>
    </div>


    <div class="row form-row ">
        <div class="col-md-6">
            <!-- First Name -->
            <div class="form-group">
                <label for="firstname">Firstname:</label> <form:input type="text" path="userFirstname"
                                                                      cssClass="form-control text-width firstname"
                                                                      id="firstname"
                                                                      name="userFirstname"
                                                                      placeholder="Enter your firstname"
                                                                      value="${user.firstName}"
                                                                      pattern="^[a-zA-Z\s]*$" required="true"/>
                <span class="firstname_error" id="firstname_error"></span>
            </div>
        </div>
        <div class="col-md-6">

            <!-- Lastname -->
            <div class="form-group">
                <label>Lastname:</label> <form:input type="text" path="userLastname"
                                                     cssClass="form-control lastname" id="lastname"
                                                     placeholder="Enter Lastname"
                                                     name="userLastname" value="${user.userLastname}"
                                                     pattern="^[a-zA-Z\s]*$"
                                                     required="true"/>
                <span
                        class="lastname_error" id="lastname_error"></span>
            </div>

        </div>
    </div>

    <div class="row form-row ">
        <div class="col-md-6">
            <!-- Middle Name -->
            <div class="form-group">
                <label for="middlename">Middlename:</label> <form:input type="text" path="userMiddlename"
                                                                        cssClass="form-control text-width middlename"
                                                                        id="middlename"
                                                                        name="userMiddlename"
                                                                        placeholder="Enter your middlename"
                                                                        value="${user.userMiddlename}"
                                                                        pattern="^[a-zA-Z\s]*$"/><span
                    class="middlename_error" id="middlename_error"></span>
            </div>
        </div>
        <div class="col-md-6">

            <!-- Username -->
            <div class="form-group">
                <label>Username:</label> <form:input type="text" path="userUsername"
                                                     cssClass="form-control username" id="username"
                                                     placeholder="Enter Username" name="userUsername"
                                                     value="${user.userUsername}"
                                                     pattern="^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:<>.?~-\s]*$"
                                                     required="true"/>
                <span
                        class="username_error" id="username_error"></span>
            </div>

        </div>
    </div>

    <div class="row form-row">
        <div class="col-md-6">
            <!-- Email  -->
            <div class="form-group">
                <label for="emailid">Email:</label> <form:input type="email" path="userEmailID"
                                                                cssClass="form-control text-width emailid"
                                                                id="emailid" value="${user.userEmailID}"
                                                                placeholder="Email-id" name="userEmailID"
                                                                pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"
                                                                required="true"/>

                <span class="email_error"
                      id="email_error"></span><br>
                <span class="result" id="result"></span>
            </div>
        </div>
        <div class="col-md-6">
            <!-- password -->
            <div class="form-group">
                <label for="pwd">Password:</label> <form:input type="password" path="userPassword"
                                                               cssClass="form-control text-width pwd" id="pwd"
                                                               placeholder="Password" name="userPassword"
                                                               value="${user.userPassword}" required="true"/> <span
                    class="password_error" id="password_error"></span>
            </div>
        </div>
    </div>

    <div class="row form-row">


        <!-- confirm password -->
            <%--commenting cuz its read-only and hence causes errors when submitting after UPDATE/EDIT--%>
            <%--            <div class="col-md-6">--%>
            <%--                <div class="form-group">--%>
            <%--                    <label for="cpwd">Confirm Password:</label> <input--%>
            <%--                        type="password" class="form-control text-width" id="cpwd"--%>
            <%--                        name="cpwd" placeholder="Confirm-Password"/> <span--%>
            <%--                        id="confirm_password_error"></span>--%>
            <%--                </div>--%>
            <%--            </div>--%>

    </div>

    <div class="row form-row">
        <div class="">

            <!-- hobbies -->
            <div class="form-group">
                <label for="hobbies">Hobby:</label> <form:input type="text" path="userHobbies"
                                                                cssClass="form-control text-width hobbies" id="hobbies"
                                                                placeholder="Hobbies" name="userHobbies"
                                                                value="${user.userHobbies}" pattern="^[a-zA-Z0-9\s]*$"
                                                                required="true"/> <span
                    class="hobbies_error" id="hobbies_error"></span>
            </div>
            <div class="">
                <!-- image upload -->
                <div class="form-group">
                    <label for="dob">Date Of Birth:</label>
                        <form:input type="date" path="userDOB"
                                    class="form-control text-width dob" id="dob"
                                    name="userDOB" min="1900-01-01"
                                    max="2023-07-19" value="${user.userDOB}"
                                    required="true"/>

                    <span class="dob_error" id="dob_error"></span>
                        <%--                            <p style="display: none">${user.userDOB}</p> --%>
                        <%--printed this 'p' tag before i fixed the visible date format on the edit page, changing it from dd-mm-yyyy to yyyy-mm-dd. but as of after adding the datepicker, teh default input format is yyyy-mm-dd-%>
                    <span id="dob_error"></span>
                </div>
            </div>
        </div>
    </div>
    <%--            ---------------------------------------------done-----------------------------------------------------------------------------------------------------------------------%>
                            <%
}
%>
                    </c:when>
                    <c:otherwise>
                            <%
 if (userRole == null || userRole != null ) {

%>
                    <div class="row form-row">
                        <div class="col-md-6">

                            <div class="form-group">
                                <!-- <label for="img">Profile picture:</label><input type="file"
                                    id="img" name="img" /> <img
                                    src="custom/image/default_profile.jpg" name="default_img"
                                    style="width: 170px; height: 170px;" id="default_img"> <br />
                                <span id="image_error"></span> -->

                                <label for="img2">Profile picture:</label> <input type="file" id="img2" name="img"/>
                                <img
                                        src="./assets/images/default_profile.jpg"
                                        style="width: 170px; height: 170px;" id="default_img2"
                                        alt="default profile"><img
                                    src="data:image/jpg;base64"
                                    id="image_preview2" style="display: none"/> <br/> <span
                                    id="image_error2"></span>
                            </div>

                        </div>
                    </div>

                    <div class="row form-row ">
                        <div class="col-md-6">
                            <!-- First Name -->
                            <div class="form-group">
                                <label for="firstname">Firstname(Here):</label> <form:input type="text"
                                                                                            path="userFirstname"
                                                                                            cssClass="form-control text-width firstname"
                                                                                            id="firstname"
                                                                                            name="userFirstname"
                                                                                            placeholder="Enter your firstname"
                                                                                            pattern="^[a-zA-Z\s]*$"
                                                                                            required="true"/>
                                <span class="firstname_error" id="firstname_error"></span>
                            </div>
                        </div>
                        <div class="col-md-6">

                            <!-- Lastname -->
                            <div class="form-group">
                                <label>Lastname:</label> <form:input type="text" path="userLastname"
                                                                     cssClass="form-control lastname" id="lastname"
                                                                     placeholder="Lastname"
                                                                     name="userLastname" pattern="^[a-zA-Z\s]*$"
                                                                     required="true"/>
                                <span
                                        class="lastname_error" id="lastname_error"></span>
                            </div>

                        </div>
                    </div>

                    <div class="row form-row ">
                        <div class="col-md-6">
                            <!-- Middle Name -->
                            <div class="form-group">
                                <label for="middlename">Middlename:</label> <form:input type="text"
                                                                                        path="userMiddlename"
                                                                                        cssClass="form-control text-width middlename"
                                                                                        id="middlename"
                                                                                        name="userMiddlename"
                                                                                        placeholder="Enter your middlename"
                                                                                        pattern="^[a-zA-Z\s]*$"/><span
                                    class="middlename" id="middlename_error"></span>
                            </div>
                        </div>
                        <div class="col-md-6">

                            <!-- Username -->
                            <div class="form-group">
                                <label>Username:</label> <form:input type="text" path="userUsername"
                                                                     cssClass="form-control username" id="username"
                                                                     placeholder="Enter Username"
                                                                     name="userUsername"
                                                                     pattern="^[a-zA-Z0-9!@#$%^&*()_+{}\[\]:<>.?~-\s]*$"
                                                                     required="true"/> <span
                                    class="username_error" id="username_error"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row form-row">
                        <div class="col-md-6">
                            <!-- Email  -->
                            <div class="form-group">
                                <label for="emailid">Email:</label>
<%--                                <form:input type="text" path="userEmailID"--%>
<%--                                                                                cssClass="form-control text-width emailid"--%>
<%--                                                                                id="emailid"--%>
<%--                                                                                placeholder="Email-id"--%>
<%--                                                                                name="userEmailID"--%>
<%--                                                                                pattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$"--%>
<%--                                                                                required="true"/>--%>
                                <span class="email_error" id="email_error"></span><br>
                                <span class="result" id="result"></span>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <!-- password -->
                            <div class="form-group">
                                <label for="pwd">Password:</label>
                                <form:password
                                    cssClass="form-control text-width pwd"
                                    id="pwd" path="userPassword"
                                    placeholder="Password"
                                    name="userPassword"/>
                                <span
                                    class="password_error" id="password_error"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row form-row">


                        <!-- confirm password -->
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="cpwd">Confirm Password:</label> <form:password path="userConfirmPassword"
                                                                                           cssClass="form-control text-width cpwd"
                                                                                           id="cpwd"
                                                                                           name="userConfirmPassword"
                                                                                           placeholder="Confirm-Password"
                                                                                           required="true"/> <span
                                    class="confirm_password_error" id="confirm_password_error"></span>
                            </div>
                        </div>
                    </div>

                    <div class="row form-row">
                        <div class="col-md-6">

                            <!-- hobbies -->
                            <div class="form-group">
                                <label for="hobbies">Hobby:</label> <form:input type="text" path="userHobbies"
                                                                                cssClass="form-control text-width hobbies"
                                                                                id="hobbies"
                                                                                placeholder="Hobbies"
                                                                                name="userHobbies"
                                                                                pattern="^[a-zA-Z0-9\s]*$"
                                                                                required="true"/> <span
                                    class="hobbies_error" id="hobbies_error"></span>
                            </div>
                            <div class="col-md-6">
                                <!-- image upload -->
                                <div class="form-group">
                                    <label for="dob">Date Of Birth:</label> <form:input type="date" path="userDOB"
                                                                                        cssClass="form-control text-width dob"
                                                                                        id="dob"
                                                                                        name="userDOB" min="1900-01-01"
                                                                                        max="2023-07-19"
                                                                                        required="true"/> <span
                                        class="dob_error" id="dob_error"></span>
                                </div>
                            </div>
                        </div>
                        <%
                            }
                        %>
                            <%--                    -----------------------------done------------------------------------------------------------------------------------------------%>
                        </c:otherwise>
                        </c:choose>

                        <!-- jQuery plugin code -->
                        <label class="address_label_margin">Address:</label>

                        <div class="margin-t-md">
                            <div class="customer-form">
                                <div class="card"></div>
                                <c:choose>
                                    <c:when
                                            test="${(profile == 'admin') || (profile == 'userEdit') || (profile == 'adminEdit')}">
                                        <%
                                            if (userRole != null) {
                                        %>
                                        <div id="main-container">
                                            <c:forEach items="${AddressList}" var="address"
                                                       varStatus="count">
                                                <div class="panel card container-item">
                                                    <div class="panel-body">
                                                        <div class="panel-body">
                                                            <form:input type="hidden" name="addressId[0]"
                                                                        path="addressId[0]"
                                                                        value="${address.addId}" required="true"/>

                                                            <div class="row">
                                                                <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                    <div class="form-group">
                                                                        <label class="control-label"
                                                                               for="address_line_one_0">House
                                                                            No.</label>
                                                                        <form:input type="text" id="houseno"
                                                                                    path="houseno[0]"
                                                                                    cssClass="form-control houseno"
                                                                                    name="houseno[0]" maxlength="10"
                                                                                    value="${address.addHouseNo}"
                                                                                    required="true"/>
                                                                        <span class="houseno_error"
                                                                              id="houseno_error"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                    <div class="form-group">
                                                                        <label class="control-label"
                                                                               for="address_line_one_0">Street</label>
                                                                        <form:input type="text" id="address"
                                                                                    path="address[0]"
                                                                                    cssClass="form-control address"
                                                                                    name="address[0]" maxlength="20"
                                                                                    value="${address.addStreet}"
                                                                                    required="true"/>
                                                                        <span class="street_error"
                                                                              id="street_error"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                    <div class="form-group">
                                                                        <label class="control-label"
                                                                               for="address_line_two_0">Landmark</label>
                                                                        <form:input type="text" id="landmark"
                                                                                    path="landmark[0]"
                                                                                    cssClass="form-control landmark"
                                                                                    name="landmark[0]" maxlength="50"
                                                                                    value="${address.addLandmark}"
                                                                                    required="true"/> <span
                                                                            class="landmark_error"
                                                                            id="landmark_error"></span>
                                                                    </div>
                                                                </div>
                                                                <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                    <div class="form-group">
                                                                        <label class="control-label"
                                                                               for="address_line_two_0">Zipcode</label>
                                                                        <form:input type="text" id="pincode"
                                                                                    path="zipcode[0]"
                                                                                    cssClass="form-control pincode"
                                                                                    name="zipcode[0]" maxlength="10"
                                                                                    value="${address.addZipcode}"
                                                                                    pattern="^[a-zA-Z0-9]*$"
                                                                                    required="true"/>
                                                                        <span
                                                                                class="pincode_error"
                                                                                id="pincode_error"></span>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="row">
                                                                <c:forEach var="item" items="${address.addCity}">
                                                                    <div class="col-sm-6">
                                                                        <div class="form-group">
                                                                            <label class="control-label"
                                                                                   for="city_0">City</label>
                                                                            <form:select cssClass="form-control city"
                                                                                         path="city[0]"
                                                                                         style="height: auto;"
                                                                                         name="city[0]"
                                                                                         required="true">
                                                                                <form:option value="0">
                                                                                    Select City
                                                                                </form:option>
                                                                                <form:option value="Ahmedabad"
                                                                                             selected="${item eq 'Ahmedabad'}">
                                                                                    Ahmedabad
                                                                                </form:option>
                                                                                <form:option value="Junagadh"
                                                                                             selected="${item eq 'Junagadh'}">
                                                                                    Junagadh
                                                                                </form:option>
                                                                                <form:option value="Mumbai"
                                                                                             selected="${item eq 'Mumbai'}">
                                                                                    Mumbai
                                                                                </form:option>
                                                                                <form:option value="Surat"
                                                                                             selected="${item eq 'Surat'}">
                                                                                    Surat
                                                                                </form:option>
                                                                                <form:option value="Vadodara"
                                                                                             selected="${item eq 'Vadodara'}">
                                                                                    Vadodara
                                                                                </form:option>
                                                                            </form:select>
                                                                            <span class="city_error"
                                                                                  id="city_error"></span>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>

                                                                <c:forEach var="item" items="${address.addState}">

                                                                    <div class="col-sm-6">
                                                                        <div class="form-group">
                                                                            <label class="control-label"
                                                                                   for="state_0">State</label>
                                                                            <form:select cssClass="form-control state"
                                                                                         path="state[0]"
                                                                                         name="state[0]"
                                                                                         id="state"
                                                                                         style="height: auto;"
                                                                                         required="true">
                                                                                <form:option value="0">
                                                                                    Select State
                                                                                </form:option>
                                                                                <form:option value="Gujarat"
                                                                                             selected="${item eq 'Gujarat'}">
                                                                                    Gujarat
                                                                                </form:option>
                                                                                <form:option value="Maharashtra"
                                                                                             selected="${item eq 'Maharashtra'}">
                                                                                    Maharashtra
                                                                                </form:option>
                                                                                <form:option value="Goa"
                                                                                             selected="${item eq 'Goa'}">
                                                                                    Goa
                                                                                </form:option>
                                                                                <form:option value="Punjab"
                                                                                             selected="${item eq 'Punjab'}">
                                                                                    Punjab
                                                                                </form:option>
                                                                                <form:option value="Assam"
                                                                                             selected="${item eq 'Assam'}">
                                                                                    Assam
                                                                                </form:option>
                                                                            </form:select> <span class="state_error"
                                                                                                 id="state_error"></span>
                                                                        </div>
                                                                    </div>
                                                                </c:forEach>
                                                            </div>

                                                            <div class="row">
                                                                <div class="col-sm-6">
                                                                    <div class="form-group">
                                                                        <label class="control-label" for="country">Country</label>
                                                                        <form:input type="text" id="country"
                                                                                    path="country[0]"
                                                                                    cssClass="form-control country"
                                                                                    name="country[0]" maxlength="255"
                                                                                    value="${address.addCountry}"
                                                                                    pattern="^[a-zA-Z0-9\s]*$"
                                                                                    required="true"/>
                                                                        <span class="country_error"
                                                                              id="country_error"></span>
                                                                    </div>
                                                                    <div class="col-sm-6">
                                                                        <div class="form-group">
                                                                            <label class="control-label"
                                                                                   for="postaladdress">Postal
                                                                                Address</label>
                                                                            <form:input type="text" id="postaladdress"
                                                                                        path="postaladdress[0]"
                                                                                        cssClass="form-control postaladdress"
                                                                                        name="postaladdress[0]"
                                                                                        maxlength="250"
                                                                                        value="${address.addPostalAdd}"
                                                                                        required="true"/>
                                                                            <span class="postaladdress_error"
                                                                                  id="postaladdress_error"></span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="row">
                                                                <div class="col-sm-12">
                                                                    <div>
                                                                        <a href="javascript:void(0)"
                                                                           class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </div>

                                        <div>
                                            <a class="btn btn-success btn-sm" id="add-more"
                                               href="javascript:;" role="button"><i class="fa fa-plus"></i>
                                                Add address</a>
                                        </div>
                                        <%--          --------------------------------done-----------------------------------------------------------------------------------------------------------------------%>
                                        <%
                                            }
                                        %>
                                    </c:when>
                                    <c:otherwise>
                                        <%
                                            if (userRole == null || userRole != null) {
                                        %>
                                        <div id="main-container">
                                            <div class="panel card container-item">
                                                <div class="panel-body">
                                                    <div class="panel-body">

                                                        <div class="row">
                                                            <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="address_line_one_0">House
                                                                        No.</label>
                                                                    <form:input type="text" id="houseno"
                                                                                path="houseno[0]"
                                                                                cssClass="form-control houseno"
                                                                                name="houseno[0]" maxlength="10"
                                                                                required="true"/>
                                                                    <span class="houseno_error"
                                                                          id="houseno_error"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="address_line_one_0">Street</label>
                                                                    <form:input type="text" id="street"
                                                                                path="address[0]"
                                                                                cssClass="form-control street"
                                                                                name="address[0]" maxlength="20"
                                                                                required="true"/>
                                                                    <span class="street_error"
                                                                          id="street_error"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="address_line_two_0">Landmark</label>
                                                                    <form:input type="text" id="landmark"
                                                                                path="landmark[0]"
                                                                                cssClass="form-control landmark"
                                                                                name="landmark[0]" maxlength="50"/>
                                                                    <span
                                                                            class="landmark_error"
                                                                            id="landmark_error"></span>
                                                                </div>
                                                            </div>
                                                            <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="address_line_two_0">Zipcode</label>
                                                                    <form:input type="text" id="pincode"
                                                                                path="zipcode[0]"
                                                                                cssClass="form-control pincode"
                                                                                name="zipcode[0]" maxlength="10"
                                                                                pattern="^[a-zA-Z0-9]*$"
                                                                                required="true"/> <span
                                                                        class="pincode_error"
                                                                        id="pincode_error"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">

                                                            <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="city">City</label>
                                                                    <form:select path="city[0]"
                                                                                 cssClass="form-control city"
                                                                                 name="city[0]"
                                                                                 id="city"
                                                                                 style="height: auto;" required="true">
                                                                        <form:option value="0">
                                                                            Select City
                                                                        </form:option>
                                                                        <form:option value="Ahmedabad">
                                                                            Ahmedabad
                                                                        </form:option>
                                                                        <form:option value="Junagadh">
                                                                            Junagadh
                                                                        </form:option>
                                                                        <form:option value="Mumbai">
                                                                            Mumbai
                                                                        </form:option>
                                                                        <form:option value="Surat">
                                                                            Surat
                                                                        </form:option>
                                                                        <form:option value="Vadodara">
                                                                            Vadodara
                                                                        </form:option>
                                                                    </form:select>
                                                                    <span class="city_error"
                                                                          id="city_error"></span>
                                                                </div>
                                                            </div>


                                                            <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="state">State</label>
                                                                    <form:select path="state[0]"
                                                                                 cssClass="form-control state"
                                                                                 name="state[0]"
                                                                                 id="state"
                                                                                 style="height: auto;" required="true">
                                                                        <form:option value="0">
                                                                            Select State
                                                                        </form:option>
                                                                        <form:option value="Gujarat">
                                                                            Gujarat
                                                                        </form:option>
                                                                        <form:option value="Maharashtra">
                                                                            Maharashtra
                                                                        </form:option>
                                                                        <form:option value="Goa">
                                                                            Goa
                                                                        </form:option>
                                                                        <form:option value="Punjab">
                                                                            Punjab
                                                                        </form:option>
                                                                        <form:option value="Assam">
                                                                            Assam
                                                                        </form:option>
                                                                    </form:select>
                                                                    <span class="state_error"
                                                                          id="state_error"></span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                <div class="form-group">
                                                                    <label class="control-label"
                                                                           for="country">Country</label>
                                                                    <form:input type="text" id="country"
                                                                                path="country[0]"
                                                                                cssClass="form-control country"
                                                                                name="country[0]" maxlength="255"
                                                                                required="true"/>
                                                                    <span class="country_error"
                                                                          id="country_error"></span>
                                                                </div>
                                                                <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                    <div class="form-group">
                                                                        <label class="control-label"
                                                                               for="postaladdress">Postal
                                                                            Address</label>
                                                                        <form:input type="text" id="postaladdress"
                                                                                    path="postaladdress[0]"
                                                                                    cssClass="form-control postaladdress"
                                                                                    name="postaladdress[0]"
                                                                                    maxlength="250" required="true"/>
                                                                        <span class="postaladdress_error"
                                                                              id="postaladdress_error"></span>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>


                                                        <div class="row">
                                                            <div class="col-sm-12 col-md-4 cold-lg-2 col-xs-2">
                                                                <div>
                                                                    <a href="javascript:void(0)"
                                                                       class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div>
                                            <a class="btn btn-success btn-sm" id="add-more"
                                               href="javascript:;" role="button"><i class="fa fa-plus"></i>
                                                Add address</a>
                                        </div>
                                        <%
                                            }
                                        %>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>

                            <%--------------------------------------------------------done---------------------------------------------------------------------------------------------------------%>
                        <c:choose>
                            <c:when test="${profile == 'userEdit' || profile == 'adminEdit' || profile == 'admin' || profile == 'ADD'}">
                                <%
                                    if
                                    (
                                            userRole
                                                    !=
                                                    null
                                    ) {

                                %>
                                <div class="row btn-margin form-row">

                                    <div class="col-sm-1  submit_btn">
                                        <label for="submit">Submit</label>
                                        <form:button type="submit" id="submit"
                                                     cssClass="btn btn-default btn-primary register_btn"
                                                     value="Submit"/>

                                    </div>
                                    <div class="col-sm-2">
                                        <a href="" class="btn btn-default btn-primary cancel">Cancel</a>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </c:when>
                            <c:otherwise>
                                <%
                                    if
                                    (
                                            userRole
                                                    ==
                                                    null
                                                    ||
                                                    userRole
                                                            !=
                                                            null
                                    ) {

                                %>
                                <div class="row btn-margin form-row">

                                    <div class="col-sm-2  submit_btn">

                                        <form:button type="submit" id="submit"
                                                     cssClass="btn btn-default btn-primary register_btn"
                                                     value="Submit"/>

                                    </div>
                                    <div class="col-sm-2">

                                        <a href="./login.jsp"
                                           class="btn btn-default btn-primary btn_hide">Back to login</a>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </c:otherwise>
                        </c:choose>
                        </form:form>
                    </div>
                    <script src="./assets/js/CDNs/jquery_1.9.1.js"></script>
                    <script src="./assets/js/CDNs/code.jquery.com_jquery-3.4.1.js"></script>
                    <script src="./assets/js/CDNs/code.jquery.com_jquery-1.12.4.js"></script>
                    <script src="./assets/js/CDNs/http_code.jquery.com_jquery-3.6.0.js"></script>

                    <script src="./assets/js/CDNs/popper_1.14.7.js"></script>
                    <script src="./assets/js/CDNs/bootstrap_4.3.1.bundle.js"></script>
                    <script src="./assets/js/CDNs/bootstrap-datepicker_1.6.4.min.js"></script>
                    <script src="./assets/js/CDNs/select2_3.3.2.min.js"></script>
                    <script src="./assets/js/CDNs/bootstrap-select_1.13.18.js"></script>
                    <script src="./assets/js/CDNs/ckeditor.js"></script>
                    <script src="./assets/js/CDNs/ckeditorstyles.js"></script>
                    <script src="assets/js/plugin.js" type="text/javascript"></script>
                    <%--    Custom Validation JS --%>
                    <script src="./assets/js/customValidation.js"></script>

                    <script type="text/javascript">
                        var parsed = new URL(location);
                        var uname = parsed.searchParams.get("user");
                        if (uname === 'ADD') {
                            $(".btn_hide").hide();
                            // $(".cancel").attr("href", "adminHomePage.jsp")
                            $(".cancel").click(function () {
                                // window.history.back();
                                window.location.href = "adminHomePage.jsp";
                                return false; // Prevents the default link behavior, and doesn't navigate to the link's href attribute value.
                            });
                        } else if (uname === 'adminEdit') {
                            $(".btn_hide").hide();
                            $("#default_img").hide();
                            $(".header_tag").text("Update Profile");
                            $(".register_btn").prop("value", "Update");
                            $("#registration_form").attr("action", "UpdateProfile");
                            $("#emailid").attr("readonly", "readonly");
                            $("#pwd").attr("readonly", "readonly");
                            $("#cpwd").attr("readonly", "readonly");
                            $("#image_preview").css({
                                "display": "block",
                                "max-width": "170px",
                                "max-height": "170px"
                            });
                            // $(".cancel").attr("href", "adminHomePage.jsp")
                            $(".cancel").click(function () {
                                /*window.history.back();*/
                                window.location.href = "adminHomePage.jsp";
                                return false; // Prevents the default link behavior, and doesn't navigate to the link's href attribute value.
                            });
                        } else if (uname === 'userEdit') {
                            $(".btn_hide").hide();
                            $("#default_img").hide();
                            $(".header_tag").text("Update Profile");
                            $(".register_btn").prop("value", "Update");
                            $("#registration_form").attr("action", "UpdateProfile");
                            $("#emailid").attr("readonly", "readonly");
                            $("#pwd").attr("readonly", "true");
                            $("#cpwd").attr("readonly", "true");
                            $("#image_preview").css({
                                "display": "block",
                                "max-width": "170px",
                                "max-width": "170px"
                            });
                            // $(".cancel").attr("href", "userHomePage.jsp")
                            $(".cancel").click(function () {
                                // window.history.back();
                                window.location.href = "userHomePage.jsp";
                                return false; // Prevents the default link behavior, and doesn't navigate to the link's href attribute value.
                            });

                        } else if (uname === 'admin') {
                            $(".btn_hide").hide();
                            $("#default_img").hide();
                            $(".header_tag").text("Update Profile");
                            $(".register_btn").prop("value", "Update");
                            $("#registration_form").attr("action", "UpdateProfile");
                            $("#emailid").attr("readonly", "readonly");
                            $("#pwd").attr("readonly", "true");
                            $("#cpwd").attr("readonly", "true");
                            $("#image_preview").css({
                                "display": "block",
                                "max-width": "170px",
                                "max-width": "170px"
                            });

                            // $(".cancel").attr("href", "adminHomePage.jsp")
                            $(".cancel").click(function () {
                                // window.history.back();
                                window.location.href = "adminHomePage.jsp";
                                return false; // Prevents the default link behavior, and doesn't navigate to the link's href attribute value.
                            });

                        }

                    </script>
</body>
</html>
<%--the include tag for footer is at the end of the document--%>
<jsp:include page="./WEB-INF/views/footer.jsp"/>

