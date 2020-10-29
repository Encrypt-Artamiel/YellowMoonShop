<%-- 
    Document   : viewcart
    Created on : Oct 10, 2020, 10:36:39 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>YOUR CART</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
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

        <c:set var="cart" value="${sessionScope.CART}"/>
        <c:if test="${not empty cart.items}">
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
                                    <c:forEach var="item" items="${cart.items}" >
                                    <form action="updateCart" method="POST">
                                        <tr>
                                            <td><img src="${item.value.image}" width="50px"/> </td>
                                            <td>${item.value.name}</td>
                                            <td><input class="form-control" type="text" name="txtQuantity" value="${item.value.quantity}"/>
                                                <br/>
                                                <c:if test="${requestScope.ID_ERROR eq item.key}">
                                                    <font color="red">${requestScope.ERROR}</font> <br/>
                                                </c:if>
                                                <c:if test="${not empty requestScope.QUANTITY_IN_STOCK}">
                                                    <font color="red">There is only ${requestScope.QUANTITY_IN_STOCK} cake left</font>
                                                </c:if>
                                            </td>
                                            <td class="text-right">${item.value.price} vnd</td>
                                            <td class="text-right"><a class="btn btn-sm btn-danger" onclick="return confirmDelete()"
                                                                      href="remove?txtProductID=${item.value.productID}" 
                                                                      role="button"><i class="fa fa-trash"></i></a>
                                                <input type="hidden" name="txtProductID" value="${item.value.productID}" />                          
                                                <button class="btn btn-sm btn-success" type="submit"><i class="fa fa-sync-alt"></i></button>                         
                                            </td>
                                        </tr>
                                    </form> 
                                </c:forEach>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><strong>Total</strong></td>
                                    <td class="text-right"><strong>${cart.total} VND</strong></td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                    </div>
                    <font color="red">${requestScope.ERR}</font>
                    <div class="col mb-2">
                        <div class="row">
                            <div class="col-sm-12  col-md-6">
                                <a class="btn btn-lg btn-block btn-primary text-uppercase" href="homePage" role="button">Continue Shopping</a>
                            </div>
                            <div class="col-sm-12 col-md-6 text-right">
                                <button class="btn btn-lg btn-block btn-success text-uppercase" 
                                        id="formButton">Checkout</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${empty cart or empty cart.items}">
            <h3>Nothing in your cart</h3>
        </c:if>
        <div class="container mb-4">
            <form method="POST" id="checkOut" style="display: none" action="checkOut">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label >Name</label>
                        <input type="text" class="form-control"  placeholder="Name" name="txtName" value="${user.name}">
                    </div>
                    <div class="form-group col-md-6">
                        <label >Phone Number</label>
                        <input type="text" class="form-control" name="txtPhoneNum" value="${user.phone}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputAddress">Address</label>
                    <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" 
                           name="txtAddress" value="${user.address}">
                </div>

                <c:forEach var="payment" items="${requestScope.PAYMENT}" varStatus="counter">
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="paymentID" id="exampleRadios${counter.count}"  
                               value="${payment.paymentID}">
                        <label class="form-check-label" for="exampleRadios${counter.count}">
                            ${payment.paymentName}
                        </label>
                    </div>
                </c:forEach>

                <button type="submit" class="btn btn-success" style="float: right">Confirm</button>
            </form>
        </div>

        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script>
                                                function confirmDelete() {
                                                    if (confirm("Are you sure you want to continue ?")) {
                                                        return true;
                                                    } else {
                                                        return false;
                                                    }
                                                }
                                                $(document).ready(function () {
                                                    $("#formButton").click(function () {
                                                        $("#checkOut").toggle();
                                                    });
                                                });
        </script>
    </body>
</html>
