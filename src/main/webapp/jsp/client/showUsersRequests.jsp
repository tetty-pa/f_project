<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title>My orders</title>
    <jsp:include page="/jsp/header.jsp"/>

</head>
<body style="height: 100%">
<div class="table__container">
    <div class="title"><fmt:message key="showUsersOrders.title"/></div>

    <table class="table table-bordered table-light">
        <c:if test="${not empty requestScope.requests}">
        <tr>
            <td><B><fmt:message key="label.name"/></B></td>
            <td><B><fmt:message key="label.start_date"/></B></td>
            <td><B><fmt:message key="label.end_date"/></B></td>
            <td><b><fmt:message key="label.amount"/></b></td>
            <td></td>

        </tr>

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
                        <button class="btn btn-secondary" type="submit"><fmt:message
                                key="button.pay_upload_doc"/></button>
                    </form>
                </c:if>
                    <c:if test="${requestU.status == 'PAID'}">
                        <form method="GET" action="${pageContext.request.contextPath}/controller/">
                            <input type="hidden" name="command" value="pdf"/>
                            <input type="hidden" name="requestId" value="${requestU.id}"/>
                            <input type="hidden" name="cruiseName" value="${requestU.cruise.cruiseName}"/>
                            <input type="hidden" name="startDate" value="${requestU.cruise.startDate}"/>
                            <input type="hidden" name="endDate" value="${requestU.cruise.endDate}"/>
                            <input type="hidden" name="amount" value="${requestU.amount}"/>
                            <button class="btn btn-secondary" type="submit"><fmt:message key="label.report"/></button>
                        </form>
                    </c:if>
                </td>

            </tr>
        </c:forEach>
        </c:if>
    </table>

    <c:if test="${ empty requestScope.requests}">
        <fmt:message key="label.haveNotOrders"/>
    </c:if>
</div>


</body>
<jsp:include page="/jsp/footer.jsp"/>

</html>

