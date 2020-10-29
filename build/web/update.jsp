<%-- 
    Document   : update
    Created on : Oct 7, 2020, 10:37:10 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LIST CAKE UPDATE</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="adminPage">Yellow Moon</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="createcake.jsp">Create Cake</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="loadUpdateList">Update Cake</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="search.jsp">Search</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="row" style="margin-left: 20px">
            <c:forEach var="product" items="${requestScope.LIST}" >
                <div class="col-sm-3" style="margin-top: 20px" >
                    <form action="viewCake" method="POST" style="width: 90%">
                        <div class="card">
                            <input type="hidden" name="txtProductID" value="${product.productID}" />
                            <img class="card-img-top" src="${product.image}">
                            <div class="card-body">
                                <h5 class="card-title">${product.name}</h5>
                                <p class="card-text">${product.description}</p>
                                <input class="btn btn-primary" type="submit" value="Update" />
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
                    <a href="loadUpdateList?currentPage=${counter.count}" class="btn btn-primary" role="button">${counter.count} </a>
                </c:forEach>
            </div>
        </div>

    </body>
</html>
