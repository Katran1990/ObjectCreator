<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 22.06.2016
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 24.05.2016
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
            font-size: 14px;
            line-height: 1.42857143;
            color: #333;
            background-color: #d9edf7;
        }

        .starter-template {
            padding: 40px 15px;
            text-align: center;
            top: 0;
        }



    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Object creator</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="index.jsp">About</a></li>
                <li><a href="web-object-creator">Create object</a></li>
                <li class="active"><a href="created-objects.jsp">Created objects</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="starter-template">
        <h1>Object creator</h1>
        <p class="lead">Use this program as a way to quickly create any new object.<br> On the page "Create object" you can create some objects with chosen components and quality.
            <br> On the page "Created objects" you can see objects that has been created.</p>
    </div>
</div>
<script src="js/bootstrap.min.js"></script>
</body>
</html>