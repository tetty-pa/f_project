<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tetiana Pavlyshyn
  Date: 7/11/2022
  Time: 11:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <jsp:include page="/jsp/header.jsp"/>

</head>
<body>

<div class="table__container">
    <div class="title">Users</div>

    <table class="table table-bordered table-light">

        <thead>
        <tr>
            <th>User Login</th>
            <th>User Name</th>
            <th>User Surname</th>
            <th>UrlDocument</th>
            <th>Role</th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.users}" var="user">

            <tr>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.urlDocument}</td>
                <td>${user.roleId}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
