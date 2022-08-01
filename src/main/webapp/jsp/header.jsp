<%--
  Created by IntelliJ IDEA.
  User: Tetiana Pavlyshyn
  Date: 7/5/2022
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css">

    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body>
<%--Header--%>

<%--<header class="header">
    <div class="container">
        <div class="header__inner">

            <a class="link-dark"  href="${pageContext.request.contextPath}/controller/?command=changeLang&lang=en">EN</a>
            <a class="link-dark"  href="${pageContext.request.contextPath}/controller/?command=changeLang&lang=ua">UA</a>
            <a class="logo" href="${pageContext.request.contextPath}/index.jsp"><img class="logo"
                                                                                     src="${pageContext.request.contextPath}/img/logo.png"
                                                                                     alt=""></a>
            <tag:userMenu/>
        </div>
    </div>
</header>--%>

<div class="header">
    <nav class="navbar navbar-expand-lg">
        <div class="container">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp">
                <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo" style="height: 60px;">
            </a>
            <div class="collapse navbar-collapse" id="mynavbar">
                <ul class="navbar-nav">
                    <c:if test="${sessionScope.user != null}">
                        <c:choose>
                            <c:when test="${sessionScope.user.roleId==1}">
                                <li class="nav-item">
                                    <a class="nav-link link-light"
                                       href="${pageContext.request.contextPath}/controller/?command=showUsersRequests">My
                                        orders</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link link-light"
                                       href="${pageContext.request.contextPath}/controller/?command=showCruises&currentPage=1">Cruises</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item">
                                    <a class="nav-link link-light"
                                       href="${pageContext.request.contextPath}/controller/?command=showAllCruises">Cruises</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link link-light"
                                       href="${pageContext.request.contextPath}/controller/?command=showAllRequests">Requests</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link link-light"
                                       href="${pageContext.request.contextPath}/controller/?command=showAllUsers">Users</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </ul>
            </div>
            <ul class="navbar-nav">
                <c:choose>
                    <c:when test="${sessionScope.user==null}">
                        <li class="nav-item">
                            <a class="nav-link link-light"
                               href="${pageContext.request.contextPath}/jsp/common/login.jsp">Login</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link link-light" data-bs-toggle="modal"
                               data-bs-target="#profile-modal">
                                Profile</a>
                        </li>
                        <li class="nav-item">
                            <a class=" nav-link link-light"
                               href="${pageContext.request.contextPath}/controller/?command=logout">Logout</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li class="nav-item nav-item">
                    <a class="nav-link link-dark"
                       href="${pageContext.request.contextPath}/controller/?command=changeLang&lang=en">EN</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link  link-dark"
                       href="${pageContext.request.contextPath}/controller/?command=changeLang&lang=ua">UA</a>
                </li>

            </ul>

        </div>
    </nav>
</div>

<tag:editProfile/>


</body>
</html>
