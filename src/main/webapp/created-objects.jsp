<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 22.06.2016
  Time: 17:22
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
            padding: 70px 15px;
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
                <li><a href="simple-creator">Create object</a></li>
                <li class="active"><a href="simple-viewer">Created objects</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="starter-template">
        <table class="table table-hover" action="simpleViewer" method="get">
            <thead style="display: table-header-group">
            <tr>
                <td>ID</td>
                <td>Name of object</td>
            </tr>
            </thead>
            <tbody style="display: table-row-group">
            <c:forEach begin="0" step="1" var="object" items="${objects}">
                <tr>
                    <td>${object.id}</td>
                    <td>${object.quality} ${object.material} ${object.subject}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="js/bootstrap.min.js"></script>
</body>
</html>