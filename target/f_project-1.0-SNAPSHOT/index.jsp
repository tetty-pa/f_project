<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>

<head>
    <title>Main</title>
    <jsp:include page="/jsp/header.jsp"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/file.css"/>
</head>

<body >
<%--Intro--%>
<div class="intro">
    <div class="container">
        <div class="intro__inner">
        <%--    <h1 class="intro__greeting"><fmt:message key="index.greeting1"/></h1>
            <h1 class="intro__greeting"><fmt:message key="index.greeting2"/></h1>--%>
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

<tag:editProfile/>
</body>


