<%-- 
    Document   : createcake
    Created on : Oct 6, 2020, 11:58:44 PM
    Author     : DELL
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CREATE CAKE</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
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
        <c:set var="err" value="${requestScope.ERROR}"/>
        <form action="create" method="POST" style="margin: 5% 7%; width: 85%">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>Cake</label>
                    <input type="text" class="form-control" placeholder="Name" name="txtCakeName" value="${param.txtCakeName}"> 
                    <font color="red">${err.invalidName}</font>
                </div>
                <div class="form-group col-md-6">
                    <label >Description</label>
                    <input type="text" class="form-control" name="txtDescription" value="">
                    <font color="red">${err.invalidDescription}</font>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>Create date</label>
                    <input type="text" class="form-control" id="datepicker1" name="txtCreateDate">
                </div>
                <div class="form-group col-md-6">
                    <label>Expired date</label>
                    <input type="text" class="form-control" id="datepicker2" name="txtExpiredDate" > 
                </div>
                <font color="red">${err.invalidDate}</font>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label>Price</label>
                    <input type="number" class="form-control" name="txtPrice" value="">
                    <font color="red">${err.invalidPrice}</font>
                </div>
                <div class="form-group col-md-4">
                    <label >Quantity</label>
                    <input type="number" class="form-control" name="txtQuantity" value="">
                    <font color="red">${err.invalidQuantity}</font>
                </div>
                <div class="form-group col-md-4">
                    <label>Category</label>
                    <select name="categoryID" class="form-control">
                        <c:forEach var="category" items="${requestScope.CATEGORY}" >
                            <option value="${category.categoryID}"> 
                                ${category.categoryName} 
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="inputGroupFile01" name="locationImage" onchange="readURL(this);">
                <label class="custom-file-label" for="inputGroupFile01" >Choose your Image</label>
                <font color="red">${err.emptyImage}</font>
            </div>

            <img id="img" src="" class="rounded float-left" width="200px">
            <input type="submit" value="Create" class="btn btn-primary" style="margin-left: 95%"/>
            <font color="red">${err.invalidInput}</font> <br/>
        </form>


        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
                    $(function () {
                        $("#datepicker1").datepicker();
                        $("#datepicker2").datepicker();
                    });
                    function readURL(input) {
                        if (input.files && input.files[0]) {
                            var reader = new FileReader();

                            reader.onload = function (e) {
                                $('#img').attr('src', e.target.result);
                            };
                            reader.readAsDataURL(input.files[0]);
                        }
                    }
        </script>
    </body>
</html>
