<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<div class="user_menu">

    <ul>
        <c:if test="${sessionScope.user != null}">
            <c:choose>
                <%--for user--%>
                <c:when test="${sessionScope.user.roleId==1}">
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showUsersRequests"><fmt:message key="header.my_orders"/></a>
                    </li>
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showCruises&currentPage=1"><fmt:message key="header.cruises"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showAllCruises"><fmt:message key="header.cruises"/></a>
                    </li>
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showAllRequests"><fmt:message key="showAllRequests.title"/></a>
                    </li>
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showAllUsers"><fmt:message key="header.users"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:if>
    </ul>
</div>

<nav class="header__inner">

    <div class="nav">

        <c:choose>
            <c:when test="${sessionScope.user==null}">
                <a class="nav__link" href="${pageContext.request.contextPath}/jsp/common/login.jsp"><fmt:message key="login.title"/></a>
            </c:when>
            <c:otherwise>
                <a class="nav__link" <%--href="#!"--%> data-bs-toggle="modal" data-bs-target="#profile-modal">
                        ${sessionScope.user.name} ${sessionScope.user.surname}</a>
                <a class="nav__link" href="${pageContext.request.contextPath}/controller/?command=logout"><fmt:message key="header.logout"/></a>
            </c:otherwise>
        </c:choose>
    </div>

</nav>

