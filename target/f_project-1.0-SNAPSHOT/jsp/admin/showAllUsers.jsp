<%@ page import="com.tpavlyshyn.fp.entity.user.Role" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Users</title>
    <jsp:include page="/jsp/header.jsp"/>

</head>
<body>

<div class="table__container">
    <div class="title"><fmt:message key="showAllUsers.title"/></div>

    <table class="table table-bordered table-light">

        <thead>
        <tr>
            <th><fmt:message key="label.email"/></th>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.surname"/></th>
            <th><fmt:message key="label.urlDocuments"/></th>
            <th><fmt:message key="label.role"/></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.users}" var="user">

            <tr>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.urlDocument}</td>
                <td>${user.role}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
