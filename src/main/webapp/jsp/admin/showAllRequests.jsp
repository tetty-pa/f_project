<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
    <jsp:include page="/jsp/header.jsp"/>
</head>
<body>

<div class="table__container">
    <div class="title">Requests</div>

    <table class="table table-bordered table-light">

        <thead>
        <tr>
            <th>User Name</th>
            <th>User Surname</th>
            <th>Cruise</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Amount</th>
            <th>Request status</th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.requests}" var="requestU">

            <tr>
                <td>${requestU.user.name}</td>
                <td>${requestU.user.surname}</td>
                <td>
                        <%--
                                        <a class="link-info" href="${pageContext.request.contextPath}/controller?command=showCruiseInfo&cruiseId=${requestU.cruise.id}">${requestU.cruise.cruiseName}</a>
                        --%>
                        ${requestU.cruise.cruiseName}
                </td>
                <td>${requestU.cruise.startDate}</td>
                <td>${requestU.cruise.endDate}</td>
                <td>${requestU.amount}</td>
                <td>${requestU.status}</td>
                <td><c:if test="${requestU.status == 'OPENED'}">
                    <form id="comm3" method="GET" action="controller/">
                        <input type="hidden" name="command" value="submitRequest"/>
                        <input type="hidden" name="requestId" value="${requestU.id}"/>
                        <input type="submit" class="btn btn-primary" value="submit request" name="submitRequest"/>
                    </form>
                </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
