<%-- 
    Document   : search.jsp
    Created on : Oct 10, 2020, 12:38:08 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SEARCH PAGE</title>
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
                        <c:if test="${user.roleID ne 1}">
                            <li class="nav-item">
                                <a class="nav-link" href="tracking.jsp">Track your order</a>
                            </li>
                        </c:if>   
                    </c:if>
                    <c:if test="${user.roleID eq 1}">
                        <li class="nav-item">
                            <a class="nav-link" href="createcake.jsp">Create Cake</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="loadUpdateList">Update Cake</a>
                        </li>
                    </c:if>
                    <li class="nav-item">
                        <a class="nav-link" href="search.jsp">Search</a>
                    </li>
                    <c:if test="${user.roleID ne 1}">
                        <li class="nav-item">
                            <a class="nav-link" href="viewcart.jsp">Your Cart</a>
                        </li>
                    </c:if>

                </ul>
            </div>
        </nav>

        <c:if test="${not empty user}">
            <font color="blue">Welcome ${user.name}</font>
        </c:if>

        <div class="form-group" >
            <form action="search" method="POST" style=" display: flex; justify-content: center;margin-top: 20px">
                <input class="form-control col-2" type="search" placeholder="Name" aria-label="Name" 
                       name="txtSearch" value="${param.txtSearch}">

                <div class="input-group col-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01">Price</label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect01" name="price">
                        <option value="1000:200000" selected>From 1000 to 200.000</option>
                        <option value="201000:500000">From 201.000 to 500.000</option>
                        <option value="501000:900000">From 501.000 to 900.000</option>
                        <option value="901000:-1">901.000 and up</option>
                        <option value="0:-1">all price</option>
                    </select>
                </div>

                <div class="input-group col-2">
                    <div class="input-group-prepend">
                        <label class="input-group-text" for="inputGroupSelect01">Category</label>
                    </div>
                    <select class="custom-select" id="inputGroupSelect01" name="categoryID">
                        <c:forEach var="category" items="${requestScope.CATEGORY}">
                            <option value="${category.categoryID}">${category.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
        <c:if test="${not empty requestScope.LIST_PRODUCT}">
            <div class="row" style="margin-left: 20px">
                <c:forEach var="product" items="${requestScope.LIST_PRODUCT}" >
                    <div class="col-sm-3" style="margin-top: 20px" >
                        <form action="addToCart" method="POST" style="width: 90%">                    
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}" />
                            <input type="hidden" name="price" value="${param.price}" />
                            <input type="hidden" name="categoryID" value="${param.categoryID}" />

                            <div class="card">
                                <input type="hidden" name="txtProductID" value="${product.productID}" />
                                <img class="card-img-top" src="${product.image}">
                                <div class="card-body">
                                    <h5 class="card-title">${product.name}</h5>
                                    <p class="card-text">${product.description}</p>
                                    <p class="card-text">${product.price} vnd</p>
                                    <c:if test="${user.roleID ne 1}" >
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
                        <a href="?currentPage=${counter.count}&price=${param.price}&categoryID=${param.categoryID}&txtSearch=${param.txtSearch}" 
                           class="btn btn-primary" role="button">${counter.count}</a>
                    </c:forEach>
                </div>
            </div>
        </c:if>



    </body>
</html>
