<%--
  Created by IntelliJ IDEA.
  User: ND
  Date: 01/09/2023
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Test Form</title>
</head>
<body>

<form:form method="post" action="registration-test" modelAttribute="userTest">

    <form:input path="name" type="text" id="name" placeholder="Enter Name" />
    <form:input path="email" type="text" id="email" placeholder="Enter Email" />
    <form:input path="password" type="text" id="password" placeholder="Enter Password" />

    <button type="submit">Add User</button>
</form:form>


</body>
</html>
