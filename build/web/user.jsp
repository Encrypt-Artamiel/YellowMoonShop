<%-- 
    Document   : search
    Created on : Oct 4, 2020, 10:19:54 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HOME PAGE</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    </head>

    <body>
        <c:set var="user" value="${sessionScope.USER}" />
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="homePage">Yellow Moon</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <c:if test="${empty user}">
                        <li class="nav-item">
                            <a class="nav-link" href="loginPage">Login</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="viewcart.jsp">Your Cart</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty user}">
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Logout</a>
                        </li>
                        <c:if test="${user.roleID ne 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="tracking.jsp">Track your order</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="viewcart.jsp">Your Cart</a>
                            </li>
                        </c:if>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="search.jsp">Search</a>
                    </li>
                    <c:if test="${user.roleID eq 1}">
                        <li class="nav-item">
                            <a class="nav-link" href="createcake.jsp">Create Cake</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="loadUpdateList">Update Cake</a>
                        </li>
                    </c:if>


                </ul>
            </div>
        </nav>
        <c:if test="${not empty user}">
            <font color="blue">Welcome ${user.name}</font>
        </c:if>

        <div class="row" style="margin-left: 20px">
            <c:forEach var="product" items="${requestScope.LIST_PRODUCT}" >
                <div class="col-sm-3" style="margin-top: 20px" >
                    <form action="addToCart" method="POST" style="width: 90%">
                        <div class="card">
                            <input type="hidden" name="txtProductID" value="${product.productID}" />
                            <img class="card-img-top" src="${product.image}">
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text">${product.description}</p>
                                <p class="card-text">${product.price} vnd</p>
                                <c:if test="${user.roleID ne 1}">
                                    <input class="btn btn-primary" type="submit" value="Add to cart" />
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
            </c:forEach>
        </div>

        <br/>

        <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups" style ="justify-content: center!important">
            <div class="btn-group mr-2" role="group" aria-label="First group">
                <c:forEach begin="1" end="${requestScope.TOTAL_PAGE}" varStatus="counter">
                    <a href="homePage?currentPage=${counter.count}" class="btn btn-primary" role="button">${counter.count}</a>
                </c:forEach>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"/>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"/>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"/>
    </body>
</html>
