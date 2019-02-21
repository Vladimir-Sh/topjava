<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Users</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>di</th>
        <th>Name</th>
        <th>email</th>
        <th>password</th>
        <th>role</th>
        <th>registred</th>
        <th>caloriesPerDay</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <jsp:useBean id="user" scope="page" type="ru.javawebinar.topjava.model.User"/>
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <th>${email}</th>
            <th>${password}</th>
            <th>${role}</th>
            <th>${registred}</th>
            <th>${caloriesPerDay}</th>
            <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>