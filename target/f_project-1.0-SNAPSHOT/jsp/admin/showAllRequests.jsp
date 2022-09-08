<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>

<html>
<head>
    <title>Requests</title>
    <jsp:include page="/jsp/header.jsp"/>
</head>
<body style="height: 100%">

<div class="table__container">
    <div class="title"><fmt:message key="showAllRequests.title"/></div>

    <table class="table table-bordered table-light">

        <thead>
        <tr>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.surname"/></th>
            <th><fmt:message key="label.cruise"/></th>
            <th><fmt:message key="label.start_date"/></th>
            <th><fmt:message key="label.end_date"/></th>
            <th><fmt:message key="label.amount"/></th>
            <th><fmt:message key="label.status"/></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${requestScope.requests}" var="requestU">

            <tr>
                <td>${requestU.user.name}</td>
                <td>${requestU.user.surname}</td>
                <td>
                    <a class="link-info"
                       href="${pageContext.request.contextPath}/controller/?command=showCruiseInfo&cruiseId=${requestU.cruise.id}">${requestU.cruise.cruiseName}</a>
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
                        <input type="submit" class="btn btn-primary" value="<fmt:message key="button.submit"/> "
                               name="submitRequest"/>
                    </form>
                </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
<jsp:include page="/jsp/footer.jsp"/>

</html>
