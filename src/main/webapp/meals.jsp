<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>

    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="element" items="${jspMeals}" >
        <c:choose>
            <c:when test="${element.excess =='false'}">
                <tr  bgcolor="#00FF66">
            </c:when>
            <c:otherwise>
                <tr  bgcolor="#FF00000">
            </c:otherwise>
        </c:choose>
        <td>${element.dateTime.toLocalDate()} ${element.dateTime.toLocalTime()} </td>
        <td>${element.description}</td>
        <td>${element.calories}
        <td>update delete</td>
    </tr>
</c:forEach>
<table/>

</body>
</html>