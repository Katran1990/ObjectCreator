<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 24.05.2016
  Time: 18:36
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
    <title>Object assembler</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <%--
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    --%>

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

        .jumbotron {
            padding-top: 0px;
            padding-bottom: 0px;
            margin-bottom: 0px;
            color: inherit;
            background-color: #337ab7;
        }
    </style>

    <%--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>--%>
    <%--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>--%>
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
                Type of component:
                <select class="form-control" name="component1">
                    <option disabled selected value>-- select an option --</option>
                    <option value="rnd">random value</option>
                    <c:forEach var="component" items="${components}">
                        <option>${component}</option>
                    </c:forEach>
                </select>
                Source of component:
                <select class="form-control" name="source1">
                    <option value="rnd">random value</option>
                    <c:forEach var="source" items="${sources}">
                        <option>${source}</option>
                    </c:forEach>
                </select>
            </div>
            <br>
            <br>
            <div class="form-group">
                Type of component:
                <select class="form-control" name="component2">
                    <option disabled selected value>-- select an option --</option>
                    <option value="rnd">random value</option>
                    <c:forEach var="component" items="${components}">
                        <option>${component}</option>
                    </c:forEach>
                </select>
                Source of component:
                <select class="form-control" name="source2">
                    <option value="rnd">random value</option>
                    <c:forEach var="source" items="${sources}">
                        <option>${source}</option>
                    </c:forEach>
                </select>
            </div>
            <br>
            <br>
            <div class="form-group">
                Type of component:
                <select class="form-control" name="component3">
                    <option disabled selected value>-- select an option --</option>
                    <option value="rnd">random value</option>
                    <c:forEach var="component" items="${components}">
                        <option>${component}</option>
                    </c:forEach>
                </select>
                Source of component:
                <select class="form-control" name="source3">
                    <option value="rnd">random value</option>
                    <c:forEach var="source" items="${sources}">
                        <option>${source}</option>
                    </c:forEach>
                </select>
            </div>
            <br>
            <br>
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


<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>--%>
<script src="js/bootstrap.min.js"></script>

</body>
</html>
