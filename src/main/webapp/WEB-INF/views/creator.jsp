<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Home</title>
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet" type="text/css">
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

        form.form-inline {
            padding-top: 60px;
        }

        form.form-inline .form-group {
            margin-bottom: 15px;
        }

        .btn-primary.active {
            color: #fff;
            background-color: #222;
            border-color: #080808;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="https://github.com/Katran1990/ObjectCreator">Object creator</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="<c:url value="/"/>">About</a></li>
                <li class="active"><a href="creator">Create object</a></li>
                <li><a href="viewer">Created objects</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <div class="starter-template">
        <div class="container">
            <div class="starter-template">
                <form:form class="form-inline" action="creator" method="post" modelAttribute="componentWrapper">
                    <div class="form-group has-error">
                        <label class="control-label">${error}</label>
                    </div>
                    <br>
                    <c:forEach items="${componentWrapper.componentList}" varStatus="status">
                        <div class="form-group">
                            Type of component:
                            <form:select class="form-control" path="componentList[${status.index}].material">
                                <option disabled selected value>-- select an option --</option>
                                <option value="rnd">random value</option>
                                <c:forEach var="material" items="${materials}">
                                    <option>${material}</option>
                                </c:forEach>
                            </form:select>
                            Source of component:
                            <form:select class="form-control" path="componentList[${status.index}].quality">
                                <option value="rnd">random value</option>
                                <c:forEach var="quality" items="${qualities}">
                                    <option>${quality}</option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <br>
                        <br>
                    </c:forEach>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary active">Create</button>
                        <button type="reset" class="btn btn-default active">Clear</button>
                    </div>
                </form:form>
                <br><br>
            </div>
            <c:if test="${createdObject!=null}">
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
            </c:if>
        </div>
    </div>
</div>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"/>

</body>
</html>
