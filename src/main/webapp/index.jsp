<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tetiana Pavlyshyn
  Date: 7/15/2022
  Time: 3:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<html>

<head>
    <jsp:include page="/jsp/header.jsp"/>
    <title>Main</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/file.css"/>

</head>

<%--Intro--%>
<div class="intro">
    <div class="container">
        <div class="intro__inner">
            <h1 class="intro__greeting">Welcome</h1>
            <h1 class="intro__greeting">to our</h1>
            <h1 class="intro__title">CompUs Company</h1>
            <h2 class="intro__subtitle">we aspire to deliver unmatched joyful vacations for our guests,
                always exceeding their expectations and in doing so driving outstanding shareholder value
            </h2>
            <%--
            <h1 class="intro__title">*Cruise Company Name*</h1>
--%>
        </div>
    </div>
</div>

<%--Features--%>
<div class="container">
    <div class="title">how we deliver</div>
    <div class="features">
        <div class="row">
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/1.png" alt="">
                <h3 class="feature__text">Ensure safe, responsible and secure operations</h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/2.png" alt="">
                <h3 class="feature__text">Warmly welcome our guests and team members to our home, making them feel a
                    part of
                    the CompUs family</h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/3.png" alt="">
                <h3 class="feature__text">Embrace our diversity and be inclusive</h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/4.png" alt="">
                <h3 class="feature__text">Anticipate needs, respond rapidly & own issues until they are resolved</h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/5.png" alt="">
                <h3 class="feature__text">Live & share a positive attitude</h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/6.png" alt="">
                <h3 class="feature__text">Show pride in our jobs and our company</h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/7.png" alt="">
                <h3 class="feature__text">Show trust, care and respect for each other, our ships and the
                    environment</h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/8.png" alt="">
                <h3 class="feature__text">Include fun in everything we do!</h3>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/jsp/common/showCruises.jsp"/>

<br>
<br>
<br>
<br>
<br>
<br>


<div class="form">
    <form id="comm" method="GET" action="controller/">
        <input type="hidden" name="command" value="showCruises"/>
        <input type="submit" value="Cruises" name="Cruises"/>
    </form>
</div>
<a href="${pageContext.request.contextPath}/jsp/admin/addCruise.jsp">
    <button>AddCruise</button>
</a>
<br>
<br>
<a href="${pageContext.request.contextPath}/jsp/common/login.jsp">
    <button>Login</button>
</a>

<a href="${pageContext.request.contextPath}/jsp/common/registration.jsp">
    <button>Registration</button>
</a>
<br>
`

<form id="comm2" method="GET" action="${pageContext.request.contextPath}/controller/">
    <input type="hidden" name="command" value="showUsersRequests"/>
    <input type="submit" value="show users requests" name="showUsersRequests"/>
</form>

<form id="comm3" method="GET" action="${pageContext.request.contextPath}/controller/">
    <input type="hidden" name="command" value="showAllRequests"/>
    <input type="submit" value="show all requests" name="showAllRequests"/>
</form>

<form id="comm3" method="GET" action="${pageContext.request.contextPath}/controller/">
    <input type="hidden" name="command" value="showAllUsers"/>
    <input type="submit" value="show all users"/>
</form>

<form id="comm3" method="GET" action="${pageContext.request.contextPath}/controller/">
    <input type="hidden" name="command" value="showAllCruises"/>
    <input type="submit" value="show all cruises"/>
</form>




<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
    Launch demo modal
</button>

<tag:editProfile/>
</body>
</html>

