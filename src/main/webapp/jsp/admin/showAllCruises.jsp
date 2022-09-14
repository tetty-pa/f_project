<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tlds/currencyTag.tld" %>
<%@ taglib prefix="dtg" uri="/WEB-INF/tlds/dateTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title>Cruises</title>
    <jsp:include page="/jsp/header.jsp"/>
</head>

<body>
<div class="table__container">
    <div class="title"><fmt:message key="showAllCruises.title"/></div>
    <form method="GET" action="${pageContext.request.contextPath}/controller/">
        <input type="hidden" name="command" value="findAllLiners"/>
        <input type="submit" class="btn btn-dark" value="<fmt:message key="button.add_cruise"/>"/>
    </form>
    <table class="table table-bordered table-light">
        <tr>
            <th><fmt:message key="label.cruise"/></th>
            <th><fmt:message key="showAllCruises.description"/></th>
            <th><fmt:message key="showAllCruises.number_of_ports"/></th>
            <th><fmt:message key="label.start_date"/></th>
            <th><fmt:message key="label.end_date"/></th>
            <th><fmt:message key="label.price"/></th>
            <th><fmt:message key="showAllCruises.ports"/></th>
            <th><fmt:message key="showAllCruises.liner"/></th>
            <th><fmt:message key="showAllCruises.action"/></th>
            <th><fmt:message key="showAllCruises.users_who_booked_a_cruise"/></th>

        </tr>
        <div id="cruises-details">
            <c:forEach items="${requestScope.cruises}" var="cruise">
            <tr>
                <td class="data">${cruise.cruiseName}</td>
                <td class="data">${cruise.description}</td>
                <td class="data">${cruise.numberOfPorts}</td>
                <td class="data">${cruise.startDate}</td>
                <td class="data">${cruise.endDate}</td>
                <td class="data">${cruise.price}</td>
                <td class="data">${cruise.portList}</td>
                <td class="data">${cruise.liner.linerName}</td>
                <td>
                    <a class="delete"
                       href="${pageContext.request.contextPath}/controller/?command=deleteCruise&id=${cruise.id}">Delete</a>
                </td>
                <td><a class="link-info"
                       href="${pageContext.request.contextPath}/controller/?command=showAllUsersByCruise&cruiseId=${cruise.id}"><fmt:message
                        key="button.showUsers"/></a></td>
            </tr>


            </c:forEach>

    </table>

</div>
</body>
<jsp:include page="/jsp/footer.jsp"/>
