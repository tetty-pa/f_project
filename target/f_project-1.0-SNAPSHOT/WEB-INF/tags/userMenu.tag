<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css">
--%>
<!-- JavaScript Bundle with Popper -->

<div class="user_menu">

    <ul>
        <c:if test="${sessionScope.user != null}">
            <c:choose>
                <%--for user--%>
                <c:when test="${sessionScope.user.roleId==1}">
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showUsersRequests">My
                            orders</a>
                    </li>
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showCruises&currentPage=1">Cruises</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showAllCruises">Cruises</a>
                    </li>
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showAllRequests">Requests</a>
                    </li>
                    <li>
                        <a class="link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showAllUsers">Users</a>
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
                <a class="nav__link" href="${pageContext.request.contextPath}/jsp/common/login.jsp">Login</a>
            </c:when>
            <c:otherwise>
                <a class="nav__link" <%--href="#!"--%> data-bs-toggle="modal" data-bs-target="#profile-modal">
                        ${sessionScope.user.name} ${sessionScope.user.surname}</a>
                <a class="nav__link" href="${pageContext.request.contextPath}/controller/?command=logout">Logout</a>
            </c:otherwise>
        </c:choose>
    </div>

</nav>
<%--<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
    Launch demo modal
</button>--%>

