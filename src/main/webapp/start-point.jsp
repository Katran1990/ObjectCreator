<%--
  Created by IntelliJ IDEA.
  User: Boris
  Date: 24.05.2016
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Start of creating</title>
</head>
<body>
<form action="web-object-creator" , method="post">
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
    <select name="type-of-object">
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
</form><br><br>
Created object is: ${createdObject}
</body>
</html>
