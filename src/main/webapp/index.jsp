<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<head>
    <title>Main</title>
    <jsp:include page="/jsp/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/file.css"/>
</head>

<body>
<%--Intro--%>
<div class="intro">
    <div class="container">
        <div class="intro__inner">
            <h1 class="intro__greeting"><fmt:message key="index.greeting1"/></h1>
            <h1 class="intro__greeting"><fmt:message key="index.greeting2"/></h1>
            <h1 class="intro__title"><fmt:message key="index.title"/></h1>
            <h2 class="intro__subtitle"><fmt:message key="index.subtitle"/></h2>
        </div>
    </div>
</div>
<div class="container">
    <%--Features--%>
    <div class="title"><fmt:message key="index.features.title"/></div>
    <div class="features">
        <div class="row">
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/1.png" alt="">
                <h3 class="feature__text"><fmt:message key="index.feature1"/></h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/2.png" alt="">
                <h3 class="feature__text"><fmt:message key="index.feature2"/></h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/3.png" alt="">
                <h3 class="feature__text"><fmt:message key="index.feature3"/></h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/4.png" alt="">
                <h3 class="feature__text"><fmt:message key="index.feature4"/></h3>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/5.png" alt="">
                <h3 class="feature__text"><fmt:message key="index.feature5"/></h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/6.png" alt="">
                <h3 class="feature__text"><fmt:message key="index.feature6"/></h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/7.png" alt="">
                <h3 class="feature__text"><fmt:message key="index.feature7"/></h3>
            </div>
            <div class="col-md-3">
                <img class="features__img" src="${pageContext.request.contextPath}/img/icons/8.png" alt="">
                <h3 class="feature__text"><fmt:message key="index.feature8"/></h3>
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

<a href="jsp/admin/addCruise.jsp">addcruise</a>

<tag:editProfile/>
</body>


