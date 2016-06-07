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

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
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
        .jumbotron {
            padding-top: 0px;
            padding-bottom: 0px;
            margin-bottom: 0px;
            color: inherit;
            background-color: #337ab7;
        }
    </style>

    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
</head>
<body>
<div class="jumbotron">
    <div class="starter-template">
    <h1>WEB OBJECT CREATOR</h1>
        </div>
</div>
<div class="container">
    <div class="starter-template">
        <form class="form-inline" action="web-object-creator" method="post">
            <div class="form-group">
                Quality of material:
                <select class="form-control" name="quality-of-material">
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
                <select class="form-control" name="type-of-material">
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
            </div>
            <!--!> <br><br>
             <div class="form-group">
                 Quality of material:
                 <select class="form-control" name="quality-of-material">
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
                 <select class="form-control" name="type-of-material">
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
             </div><!-->
            <br><br>
            <div class="form-group">
                <button type="submit" class="btn btn-primary active">Create</button>
                <button type="reset" class="btn btn-default active">Clear</button>
            </div>
        </form>
        <br><br>
    </div>
    <div class="container">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">Created object is:</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${createdObject}</p>
                </div>
            </div>
        </form>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
