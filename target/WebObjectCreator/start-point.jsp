<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 24.05.2016
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Object assembler</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <style>
        body {
            padding-top: 50px;
        }

        .starter-template {
            padding: 40px 15px;
            text-align: center;
        }
    </style>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<form class="form-inline", action="web-object-creator", method="post">
    <div class="form-group">
    Quality of material:
    <select name="quality-of-material">
        <option disabled>Choose the value</option>
        <option selected value="awful">Awful</option>
        <option value="very-bad">Very bad</option>
        <option value="bad">Bad</option>
        <option value="not-bad">Not bad</option>
        <option value="standart">Standart</option>
        <option value="good">Good</option>
        <option value="very-good">Very good</option>
        <option value="best">Best</option>
        <option value="legendary">Legendary</option>
    </select>
    Type of material:
    <select name="type-of-material">
        <option disabled>Choose the value</option>
        <option selected value="woody">Wood</option>
        <option value="stone">Stone</option>
        <option value="glassy">Glass</option>
        <option value="plastic">Plastic</option>
        <option value="paper">Paper</option>
        <option value="woolen">Wool</option>
        <option value="meaty">Meet</option>
    </select>
    Type of object:
    <select class="form-control" name="type-of-object">
        <option disabled>Choose the value</option>
        <option selected value="cat">Cat</option>
        <option value="dog">Dog</option>
        <option value="man">Man</option>
        <option value="car">Car</option>
        <option value="computer">Computer</option>
        <option value="phone">Phone</option>
    </select>
    <button type="submit">Create</button>
    <button type="reset">Clear</button>
    </div>
</form><br><br>
Created object is: ${createdObject}
<!-- /.container -->

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
