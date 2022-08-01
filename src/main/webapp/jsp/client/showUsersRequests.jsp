<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Tetiana Pavlyshyn
  Date: 6/10/2022
  Time: 7:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My orders</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <jsp:include page="/jsp/header.jsp"/>

</head>
<body>
<div class="table__container">
    <div class="title">My orders</div>

    <table class="table table-bordered table-light">

        <thead>
        <%--    <thead>
            <tr>
                <td>Name</td>
                <td>Start Date</td>
                <td>End Date</td>
                <td>Amount</td>
            </tr>
            </thead>
            <c:forEach var="cruise" items="${requestScope.cruises}">
                <tr>
                    <td><c:out value="${cruise.name}"/></td>
                    <td><c:out value="${cruise.startDate}"/></td>
                    <td><c:out value="${cruise.endDate}"/></td>
                </tr>
            </c:forEach>
            <c:forEach var="request" items="${requestScope.requests}">
                <tr>
                    <td><c:out value="${request.amount}"/></td>
                </tr>
            </c:forEach>--%>
        <thead>
        <tr>
            <td><B>Name</B></td>
            <td><B>Start Date</B></td>
            <td><B>End Date</B></td>
            <td><b>Amount</b></td>
            <td></td>

        </tr>
        </thead>
        <c:forEach items="${requestScope.requests}" var="requestU">

            <tr>
                <td>${requestU.cruise.cruiseName}</td>
                <td>${requestU.cruise.startDate}</td>
                <td>${requestU.cruise.endDate}</td>
                <td>${requestU.amount}</td>
                <td><c:if test="${requestU.status == 'CONFIRMED'}">
                    <form id="comm3" method="GET" action="${pageContext.request.contextPath}/controller/">
                        <input type="hidden" name="command" value="calculateTotalPrice"/>
                        <input type="hidden" name="requestId" value="${requestU.id}"/>
                        <input type="submit" value="pay/uploadDoc"/>
                    </form>
                    <%--<a href="${pageContext.request.contextPath}/jsp/client/pay.jsp?requestId=${requestU.id}">pay</a>--%>
                    <%--<a href="${pageContext.request.contextPath}/jsp/client/pay.jsp">upload file</a>--%>
                </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
