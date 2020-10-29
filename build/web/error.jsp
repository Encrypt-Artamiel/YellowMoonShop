<%-- 
    Document   : error
    Created on : Oct 15, 2020, 9:56:39 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR PAGE</title>
    </head>
    <body>
        <h1 style="color: red">SOMETHING WRONG, YOUR PAGE IS UNAVAILABLE</h1> <br/>

        <br/>
        <c:if test="${not empty sessionScope.USER}">
            <a href="homePage">Click here to return home page</a>
        </c:if>
        <br/>
        <c:if test="${empty sessionScope.USER}">
            <a href="login.jsp">Click here to login</a>
        </c:if>
    </body>
</html>
