<%-- 
    Document   : login
    Created on : Oct 4, 2020, 4:15:41 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="google-signin-client_id" content="818021598235-722vkhmm5fhpr2odeg47gdp1mhtsurja.apps.googleusercontent.com">
        <title>LOGIN PAGE</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="homePage">Yellow Moon</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="loginPage">Login</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="search.jsp">Search</a>
                    </li>
                </ul>
            </div>
        </nav>
        <br/>
        <form action="login" method="POST" style="margin: 7% 10%; width: 75%">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Username</span>
                </div>
                <input type="text" class="form-control" placeholder="Username" aria-label="Username" aria-describedby="basic-addon1"
                       name="txtUserID" value="">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Password</span>
                </div>
                <input type="password" class="form-control" name="txtPassword" value="">
            </div>
            <input class="btn btn-primary" type="submit" value="Submit" />
            <div class="g-signin2" data-onsuccess="onSignIn"></div>
        </form>
        
        <font color="red">${requestScope.ERROR} </font>

        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <script>
            function onSignIn(googleUser) {
                var id_token = googleUser.getAuthResponse().id_token;
                var xhr = new XMLHttpRequest();
                xhr.open('POST', 'google', false);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onload = function () {
                   window.location(xhr.responseText);
                };
                googleUser.disconnect();
                xhr.send('idtoken=' + id_token);               
            }
            
        </script>
    </body>
</html>
