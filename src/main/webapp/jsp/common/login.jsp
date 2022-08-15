<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css">
</head>
<body>
<div class="custom__container">
    <div class="login">
        <a class="logo" href="${pageContext.request.contextPath}/index.jsp"><img class="logo"
                                                                                 src="${pageContext.request.contextPath}/img/logo.png"
                                                                                 alt=""></a>

        <div class="title"><fmt:message key="login.title"/> </div>
        <div class="text-danger">
            <%
                if (request.getAttribute("message") != null) {
                    out.print("<p class='text-danger ml-1'>" + request.getAttribute("message") + "</p>");
                }
            %>
        </>
        <form method="POST" action="${pageContext.request.contextPath}/controller/">
            <input type="hidden" name="command" value="login"/>
            <div class="input__field">
                <input type="text" name="login" required>
                <label><fmt:message key="label.email"/></label>
            </div>
            <div class="input__field">
                <input type="password" name="password" required>
                <label><fmt:message key="label.password"/></label>
            </div>
            <a class="nav__link" style="font-size: 14px"
               href="${pageContext.request.contextPath}/jsp/common/forgotPassword.jsp"><fmt:message key="login.forgot_password"/></a>
            <br>
            <br>
            <button type="submit" class="button_1"><fmt:message key="button.sign_in"/></button>
            <br>
            <br>
            <div class="page-link link-dark text-center d-inline">
                <fmt:message key="login.message"/>
                <a class="page-link link-light text-center d-inline"
                   href="${pageContext.request.contextPath}/jsp/common/registration.jsp"> <fmt:message key="button.sign_up"/> </a>
            </div>

        </form>
    </div>
</div>

</body>
</html>
