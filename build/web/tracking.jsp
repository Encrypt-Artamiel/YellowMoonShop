<%-- 
    Document   : tracking
    Created on : Oct 11, 2020, 3:27:46 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TRACKING ORDER</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    </head>
    <body>       
        <c:set var="user" value="${sessionScope.USER}" />
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="homePage">Yellow Moon</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" 
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <c:if test="${empty user}">
                        <li class="nav-item">
                            <a class="nav-link" href="loginPage">Login</a>
                        </li>
                    </c:if>
                    <c:if test="${not empty user}">
                        <li class="nav-item">
                            <a class="nav-link" href="logout">Logout</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="tracking.jsp">Track your order</a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="search.jsp">Search</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="viewcart.jsp">Your Cart</a>
                    </li>
                </ul>
            </div>
        </nav>
        <c:if test="${not empty user}">
            <font color="blue">Welcome ${user.name}</font>
        </c:if>
        <c:set var="obj" value="${requestScope.TRACKING_OBJECT}"/>
        <div class="container mb-4" style="margin-top: 20px">
            <form class="form-group" style="display: flex" action="tracking">
                <input class="form-control mr-sm-2" type="search" placeholder="OrderID" aria-label="OrderID"
                       name="txtOrderID" value="${requestScope.ORDER_ID}">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
        <c:if test="${not empty obj}">
            <div class="container mb-4" style="margin-top: 20px">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Name</label>
                        <input type="text" class="form-control"  placeholder="Name" value="${obj.name}" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label >Phone Number</label>
                        <input type="text" class="form-control" value="${obj.phone}" disabled>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="inputAddress">Shipping Address</label>
                        <input type="text" class="form-control" id="inputAddress" value="${obj.address}" disabled>
                    </div>
                    <div class="form-group col-md-6">
                        <label >Payment Method</label>
                        <input type="text" class="form-control" value="${obj.payment}" disabled>
                    </div>
                </div>
            </div>

            <div class="container mb-4">
                <div class="row">
                    <div class="col-12">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col"> </th>
                                        <th scope="col">Product</th>
                                        <th scope="col" class="text-center">Quantity</th>
                                        <th scope="col" class="text-right">Price</th>
                                        <th> </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="item" items="${obj.listProduct}" >
                                        <tr>
                                            <td><img src="${item.image}" width="50px"/> </td>
                                            <td>${item.name}</td>
                                            <td><input class="form-control" type="text" value="${item.quantity}" disabled/></td>
                                            <td class="text-right">${item.price} vnd</td>
                                            <td></td>
                                        </tr>
                                    </c:forEach>
                                    <tr>
                                        <td>${obj.orderDate}</td>
                                        <td></td>
                                        <td></td>
                                        <td><strong>Total</strong></td>
                                        <td class="text-right"><strong>${obj.totalPrice} VND</strong></td>
                                    </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
