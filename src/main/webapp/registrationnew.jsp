<%--
  Created by IntelliJ IDEA.
  User: ND
  Date: 31/08/2023
  Time: 17:34
  To change this template use File | Settings | File Templates.
--%>
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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
<body>
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


<div>

    <%--@elvariable id="userForm" type=""--%>
    <form:form action="UserRegister" method="post" modelAttribute="command" id="registration_form">


        <h3 style="text-align: center; font-family: 'Poppins Regular'" class="margin_top_file header_tag">Registration
            Happens Here!</h3>
        <c:choose>
            <c:when test="${(profile == 'admin') || (profile == 'userEdit') || (profile == 'adminEdit')}">
                <%
                    if (userRole != null) {

                %>

                <label for="userFirstname">Firstname:</label> <form:input type="text" path="userFirstname"
                                                                          cssClass="form-control text-width firstname"
                                                                          id="userFirstname"
                                                                          name="userFirstname"
                                                                          placeholder="Enter your firstname"
                                                                          value="${user.firstName}"/>
                <span class="firstname_error" id="firstname_error"></span>

                <br><br>

                <label>Lastname:</label> <form:input type="text" path="userLastname"
                                                     cssClass="form-control lastname" id="userLastname"
                                                     placeholder="Enter Lastname"
                                                     name="userLastname" value="${user.userLastname}"/>
                <span
                        class="lastname_error" id="lastname_error"></span>

                <br><br>

                <label for="userMiddlename">Middlename:</label> <form:input type="text" path="userMiddlename"
                                                                            cssClass="form-control text-width middlename"
                                                                            id="userMiddlename"
                                                                            name="userMiddlename"
                                                                            placeholder="Enter your middlename"
                                                                            value="${user.userMiddlename}"/><span
                    class="middlename_error" id="middlename_error"></span>

                <br><br>

                <label>Username:</label> <form:input type="text" path="userUsername"
                                                     cssClass="form-control username" id="userUsername"
                                                     placeholder="Enter Username" name="userUsername"
                                                     value="${user.userUsername}"/>
                <span
                        class="username_error" id="username_error"></span>

                <br><br>

                <label for="userEmailID">Email:</label> <form:input type="email" path="userEmailID"
                                                                    cssClass="form-control text-width emailid"
                                                                    id="userEmailID" value="${user.userEmailID}"
                                                                    placeholder="Email-id" name="userEmailID"/>

                <span class="email_error"
                      id="email_error"></span><br>

                <br><br>

                <label for="userPassword">Password:</label> <form:input type="password" path="userPassword"
                                                                        cssClass="form-control text-width pwd"
                                                                        id="userPassword"
                                                                        placeholder="Password" name="userPassword"
                                                                        value="${user.userPassword}"/> <span
                    class="password_error" id="password_error"></span>

                <br><br>

                <label for="userHobbies">Hobby:</label> <form:input type="text" path="userHobbies"
                                                                    cssClass="form-control text-width hobbies"
                                                                    id="userHobbies"
                                                                    placeholder="Hobbies" name="userHobbies"
                                                                    value="${user.userHobbies}"/> <span
                    class="hobbies_error" id="hobbies_error"></span>

                <br><br>

                <label for="userDOB">Date Of Birth:</label> <form:input type="date" path="userDOB"
                                                                        cssClass="form-control text-width dob"
                                                                        id="userDOB"
                                                                        name="userDOB" value="${user.userDOB}"/>
                <span class="dob_error" id="dob_error"></span>

                <br><br>

                <%
                    }
                %>
            </c:when>
            <c:otherwise>
                <%
                    if (userRole == null || userRole != null) {
                %>

                <spring:bind path="userForm.userFirstname">
                    <label for="userFirstname"> Firstname:</label>
                    <form:input type="text" cssClass="form-control text-width firstname" path="userFirstname"
                                id="userFirstname"
                                name="userFirstname"
                                placeholder="Enter your firstname"/>
                    <span class="firstname_error" id="firstname_error"></span>
                </spring:bind>

                <br><br>

                <spring:bind path="userForm.userLastname">
                    <label> Lastname:</label> <form:input type="text" path="userLastname"
                                                          cssClass="form-control lastname" id="userLastname"
                                                          placeholder="Lastname"
                                                          name="userLastname"/>
                    <span class="lastname_error" id="lastname_error"></span>
                </spring:bind>
                <br><br>

                <spring:bind path="userForm.userMiddlename">
                    <label for="userMiddlename"> Middlename:</label>
                    <form:input type="text" path="userMiddlename"
                                cssClass="form-control text-width middlename"
                                id="userMiddlename"
                                name="userMiddlename"
                                placeholder="Enter your middlename"/>
                    <span class="middlename" id="middlename_error"></span>
                </spring:bind>
                <br><br>

                <spring:bind path="userForm.userUsername">
                    <label> Username:</label> <form:input type="text" path="userUsername"
                                                          cssClass="form-control username" id="userUsername"
                                                          placeholder="Enter Username"
                                                          name="userUsername"/>
                    <span class="username_error" id="username_error"></span>
                </spring:bind>

                <br><br>

                <spring:bind path="userForm.userEmailID">
                    <label for="userEmailID"> Email:</label>
                    <form:input type="email" path="userEmailID"
                                cssClass="form-control text-width emailid"
                                id="userEmailID"
                                placeholder="Email-id"
                                name="userEmailID"/>
                </spring:bind>
                <br><br>

                <spring:bind path="userForm.userPassword">
                <label for="userPassword"> Password:</label>
                <form:input type="password" path="userPassword"
                            cssClass="form-control text-width pwd"
                            id="userPassword"
                            placeholder="Password"
                            name="userPassword"/>
                <span class="password_error" id="password_error"></span>
            </spring:bind>
                <br><br>

                <spring:bind path="userForm.userPassword">
                <label for="userConfirmPassword"> Confirm Password:</label>
                <form:input path="userConfirmPassword"
                            type="password" cssClass="form-control text-width cpwd"
                            id="userConfirmPassword"
                            name="userConfirmPassword"
                            placeholder="Confirm-Password"/>
                <span class="confirm_password_error" id="confirm_password_error"></span>
            </spring:bind>

                <br><br>

                <spring:bind path="userForm.userHobbies">
                <label for="userHobbies"> Hobby:</label>
                <form:input type="text" path="userHobbies"
                            cssClass="form-control text-width hobbies"
                            id="userHobbies"
                            placeholder="Hobbies"
                            name="userHobbies"/>
                <span class="hobbies_error" id="hobbies_error"></span>
            </spring:bind>
                <br><br>

                <spring:bind path="userForm.userDOB">
                <label for="userDOB"> Date Of Birth:</label>
                <form:input type="date" path="userDOB"
                            cssClass="form-control text-width dob"
                            id="userDOB"
                            name="userDOB"/> <span
                    class="dob_error" id="dob_error"></span>
            </spring:bind>

                <%
                    }
                %>
                <%--                    -----------------------------done------------------------------------------------------------------------------------------------%>
            </c:otherwise>
        </c:choose>

        <label class="address_label_margin">Address:</label>

        <c:choose>
            <c:when test="${(profile == 'admin') || (profile == 'userEdit') || (profile == 'adminEdit')}">
                <%
                    if (userRole != null) {
                %>
                <div id="main-container">

                    <c:forEach items="${AddressList}" var="address" varStatus="count">

                        <input type="hidden" name="addressId[]"
                               value="${address.addId}">

                        <label class="control-label" for="address_line_one_0">
                            House No.
                        </label>
                        <form:input type="text" id="houseno" path="houseno[0]"
                                    cssClass="form-control houseno"
                                    name="houseno[0]" maxlength="10"
                                    value="${address.addHouseNo}"/>
                        <span class="houseno_error"
                              id="houseno_error"></span>
                        <br><br>
                        <label class="control-label"
                               for="address_line_one_0">Street</label>
                        <form:input type="text" id="address" path="address[0]"
                                    cssClass="form-control address"
                                    name="address[0]" maxlength="20"
                                    value="${address.addStreet}"/>
                        <span class="street_error"
                              id="street_error"></span>
                        <br><br>

                        <label class="control-label"
                               for="address_line_two_0">Landmark</label>
                        <form:input type="text" id="landmark" path="landmark[0]"
                                    cssClass="form-control landmark"
                                    name="landmark[0]" maxlength="50"
                                    value="${address.addLandmark}"/>
                        <span class="landmark_error"
                              id="landmark_error"></span>

                        <br><br>

                        <label class="control-label"
                               for="address_line_two_0">Zipcode</label>
                        <form:input type="text" id="pincode" path="zipcode[0]"
                                    cssClass="form-control pincode"
                                    name="zipcode[0]" maxlength="10"
                                    value="${address.addZipcode}"
                        />
                        <span
                                class="pincode_error"
                                id="pincode_error"></span>

                        <br><br>

                        <c:forEach var="item" items="${address.addCity}">

                            <label class="control-label"
                                   for="city_0">City</label>
                            <form:select path="city[0]"
                                         cssClass="form-control city"
                                         name="city[0]"
                                         id="city"
                                         style="height: auto;">
                                <form:option value="0">Select City</form:option>
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

                        </c:forEach>
                        <br><br>
                        <c:forEach var="item" items="${address.addState}">

                            <label class="control-label"
                                   for="state_0">State</label>
                            <form:select cssClass="form-control state" path="state[0]"
                                         name="state[]"
                                         id="state"
                                         style="height: auto;">
                                <form:option value="0">Select State
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

                        </c:forEach>

                        <br><br>

                        <label class="control-label" for="country">Country</label>
                        <form:input type="text" id="country" path="country[0]"
                                    cssClass="form-control country"
                                    name="country[]" maxlength="255"
                                    value="${address.addCountry}"
                                    pattern="^[a-zA-Z0-9\s]*$"/>
                        <span class="country_error"
                              id="country_error"></span>

                        <br><br>

                        <label class="control-label"
                               for="postaladdress">Postal
                            Address</label>
                        <form:input type="text" id="postaladdress" path="postaladdress[0]"
                                    cssClass="form-control postaladdress"
                                    name="postaladdress[]"
                                    maxlength="250"
                                    value="${address.addPostalAdd}"/>
                        <span class="postaladdress_error"
                              id="postaladdress_error"></span>

                        <br><br><br>

                        <div>
                            <a href="javascript:void(0)"
                               class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
                        </div>
                    </c:forEach>
                </div>

                <br>

                <div>
                    <a class="btn btn-success btn-sm" id="add-more"
                       href="javascript:;" role="button"><i class="fa fa-plus"></i>
                        Add address</a>
                </div>

                <%
                    }
                %>
            </c:when>
            <c:otherwise>
                <%
                    if (userRole == null || userRole != null) {
                %>
                <spring:bind path="address.addHouseNo">
                    <label class="control-label"
                           for="address_line_one_0">House
                        No.</label>
                    <form:input type="text" id="houseno" path="addHouseNo"
                                cssClass="form-control houseno"
                                name="houseno[]" maxlength="10"/>
                    <span class="houseno_error"
                          id="houseno_error"></span>
                </spring:bind>

                <br><br>

                <spring:bind path="address.addStreet">
                    <label class="control-label"
                           for="address_line_one_0">Street</label>
                    <form:input type="text" id="street" path="address[0]"
                                cssClass="form-control street"
                                name="address[]" maxlength="20"/>
                    <span class="street_error"
                          id="street_error"></span>
                </spring:bind>
                <br><br>

                <spring:bind path="address.addLandmark">
                    <label class="control-label"
                           for="address_line_two_0">Landmark</label>
                    <form:input type="text" id="landmark" path="landmark[0]"
                                cssClass="form-control landmark"
                                name="landmark[]" maxlength="50"/> <span
                        class="landmark_error"
                        id="landmark_error"></span>
                </spring:bind>
                <br><br>

                <spring:bind path="address.addZipcode">
                    <label class="control-label"
                           for="pincode">Zipcode</label>
                    <form:input type="text" id="pincode" path="zipcode[0]"
                                cssClass="form-control pincode"
                                name="zipcode[]" maxlength="10"
                                pattern="^[a-zA-Z0-9]*$"/> <span
                        class="pincode_error"
                        id="pincode_error"></span>
                </spring:bind>
                <br><br>

                <spring:bind path="address.addCity">
                    <label class="control-label"
                           for="city">City</label>
                    <form:select addClass="form-control city" name="city[]" path="city[0]"
                                 id="city"
                                 style="height: auto;">
                        <form:option value="0">Select City</form:option>
                        <form:option value="Ahmedabad">Ahmedabad</form:option>
                        <form:option value="Junagadh">Junagadh</form:option>
                        <form:option value="Mumbai">Mumbai</form:option>
                        <form:option value="Surat">Surat</form:option>
                        <form:option value="Vadodara">Vadodara</form:option>
                    </form:select>
                    <span class="city_error"
                          id="city_error"></span>
                </spring:bind>
                <br><br>

                <spring:bind path="address.addState">
                    <label class="control-label"
                           for="state">State</label>
                    <form:select
                            addClass="form-control state" name="state[]" path="state[0]"
                            id="state"
                            style="height: auto;">
                        <form:option value="0">Select State</form:option>
                        <form:option value="Gujarat">Gujarat</form:option>
                        <form:option value="Maharashtra">Maharashtra</form:option>
                        <form:option value="Goa">Goa</form:option>
                        <form:option value="Punjab">Punjab</form:option>
                        <form:option value="Assam">Assam</form:option>
                    </form:select>
                    <span class="state_error"
                          id="state_error"></span>
                </spring:bind>

                <br><br>

                <spring:bind path="address.addHouseNo">
                    <label class="control-label" for="country">Country</label>
                    <form:input type="text" id="country" path="country[0]"
                                cssClass="form-control country"
                                name="country[]" maxlength="255"
                    />
                    <span class="country_error"
                          id="country_error"></span>
                </spring:bind>
                <br><br>

                <spring:bind path="address.addPostalAddress">
                    <label class="control-label"
                           for="postaladdress">Postal
                        Address</label>
                    <form:input type="text" id="postaladdress" path="postaladdress[0]"
                                cssClass="form-control postaladdress"
                                name="postaladdress[]"
                                maxlength="250"/>
                    <span class="postaladdress_error"
                          id="postaladdress_error"></span>
                </spring:bind>
                <br><br>

                <div>
                    <a href="javascript:void(0)"
                       class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>
                </div>
                <hr>

                <br><br>

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


        <c:choose>
            <c:when test="${profile == 'userEdit' || profile == 'adminEdit' || profile == 'admin' || profile == 'ADD'}">
                <%
                    if (userRole != null) {

                %>
                <label for="submit">Submit</label>
                <input type="submit" id="submit" class="btn btn-default btn-primary register_btn"
                       value="Submit">

                <br>

                <a href="" class="btn btn-default btn-primary cancel">Cancel</a>
                <%
                    }
                %>
            </c:when>
            <c:otherwise>
                <%
                    if (userRole == null || userRole != null) {

                %>
                <input type="submit"
                       class="btn btn-default btn-primary register_btn" value="Submit">
                <a href="./login.jsp"
                   class="btn btn-default btn-primary btn_hide">Back to login</a>

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
<jsp:include page="./WEB-INF/views/footer.jsp"/>