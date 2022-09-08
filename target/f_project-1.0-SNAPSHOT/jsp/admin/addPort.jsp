<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddPort</title>
    <jsp:include page="/jsp/header.jsp"/>

</head>
<body>


<div class="container" style="margin-top: 70px">

    <div class="table__container">
        <div class="title" style=" margin-top: 0"><fmt:message key="showAllCruises.ports"/></div>
        <c:if test="${fn:length(sessionScope.ports)!=0}">
            <c:forEach var="port" items="${sessionScope.ports}">
                ${port.sequenceNumber}) <fmt:message key="label.port"/> №${port.portId}, ${port.arrivalTime}
                <c:if test="${fn:length(sessionScope.ports)==port.sequenceNumber}">
                    <form method="POST" action="${pageContext.request.contextPath}/controller/">
                        <input type="hidden" name="command" value="removePortFromCruise"/>
                        <input type="submit" class="btn btn-outline-danger btn-sm" value="<fmt:message key="button.removePort"/>"/>
                    </form>
                </c:if>
                <br>
            </c:forEach>
        </c:if>
        <br>

        <label><fmt:message key="addPort.quick_search"/>: <input id="search" type="text"></label>
        <br>
        <fmt:message key="addPort.finish_label"/>
        <form method="POST" action="${pageContext.request.contextPath}/controller/">
            <input type="hidden" name="command" value="addCruise"/>
            <input type="submit" class="btn btn-secondary" value="<fmt:message key="button.finish"/>"/>
        </form>
        <table class="table table-bordered table-light" id="cruises-details">
            <p> <fmt:message key="addPort.sequence_number"/>: ${fn:length(sessionScope.ports)+1}</p>
            <c:forEach items="${requestScope.ports}" var="port">
                <tr>
                    <td class="data">${port.id}</td>
                    <td class="data">${port.city}</td>
                    <td class="data">${port.country}</td>
                    <td>
                        <form method="GET" action="${pageContext.request.contextPath}/controller/">
                            <input type="hidden" name="command" value="addPortToCruise"/>
                            <input type="hidden" name="cruiseId" value="${requestScope.cruiseId}">
                            <input type="hidden" name="portId" value="${port.id}">
                            <input type="datetime-local" value="arrival time" name="time" required>
                            <input type="submit" class="btn btn-outline-secondary" value="add to cruise"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <nav>
        <div class="pagination ">
            <c:if test="${requestScope.numberOfPages gt 1}">

                <c:if test="${requestScope.currentPage != 1}">
                    <li class="">
                        <a class="page-link link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showAllPorts&currentPage=${requestScope.currentPage-1}"><fmt:message
                                key="showCruises.pagination.previous"/> </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                    <c:choose>
                        <c:when test="${requestScope.currentPage eq i}">
                            <li class=""><a class="page-link link-dark active" style="background-color: #FF4742">
                                    ${i} <span class="sr-only"></span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link link-dark"
                                   href="${pageContext.request.contextPath}/controller/?command=showAllPorts&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
                    <li class="">
                        <a class="page-link link-dark"
                           href="${pageContext.request.contextPath}/controller/?command=showAllPorts&currentPage=${requestScope.currentPage+1}"><fmt:message
                                key="showCruises.pagination.next"/> </a>
                    </li>
                </c:if>
            </c:if>
        </div>
    </nav>
</div>
<jsp:include page="/jsp/footer.jsp"/>

</body>
</html>
<script>
    $(document).ready(function () {
        const $rows = $("td");

        $("#search").keyup(function () {
            const val = $.trim(this.value).toUpperCase();
            if (val === "")
                $rows.parent().show();
            else {
                $rows.parent().hide();
                $rows.filter(function () {
                    return -1 !== $(this).text().toUpperCase().indexOf(val);
                }).parent().show();
            }
        });
    });
    let clicks = 0;


</script>