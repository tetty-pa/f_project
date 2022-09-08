<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tlds/currencyTag.tld" %>
<%@ taglib prefix="dtg" uri="/WEB-INF/tlds/dateTag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>

<html>
<head>

    <jsp:include page="/jsp/header.jsp"/>

    <title>Cruises</title>
</head>
<body >
<div class="container">
    <div class="title"><fmt:message key="showCruises.title"/></div>
    <form id="comm1" method="GET" action="${pageContext.request.contextPath}/controller/">
        <input type="hidden" name="command" value="showCruises">
        <div class="col-md-6 d-inline-flex">
            <div class=" col-md-2 text-uppercase"><fmt:message key="showCruises.duration"/></div>
            <select class="form-control" name="duration">
                <option><fmt:message key="showCruises.duration.any"/></option>
                <option value="2-5"><fmt:message key="showCruises.duration.2-5days"/></option>
                <option value="6-9"><fmt:message key="showCruises.duration6-9days"/></option>
                <option value="10"><fmt:message key="showCruises.duration.10+days"/></option>
            </select>
            <div class="col-md-2 text-uppercase"><fmt:message key="showCruises.month"/></div>
            <input class="form-control" type="month" value="month" name="month">
            <input type="hidden" name="currentPage" value="1">

            <button class="btn btn-secondary" role="button"><fmt:message key="showCruises.button_apply"/></button>

        </div>
    </form>
</div>

<div class="table__cruises">
    <div class="container">
        <c:forEach var="cruise" items="${requestScope.cruises}">
            <div class="cruise__photo" style="background-color: #505050">
                <img src="${pageContext.request.contextPath}${cruise.cruisePhoto}" alt="cruisePhoto" width="1200px"
                     height="350px">

                <div class="cruise__container">

                    <div class="table__cruise_item">
                        <div class="container__name_description">
                            <div class="cruise__name">
                                <a href="${pageContext.request.contextPath}/controller/?command=showCruiseInfo&cruiseId=${cruise.id}">${cruise.cruiseName}</a>
                            </div>
                            <div class="cruise__description">
                                    ${cruise.description}
                            </div>
                        </div>
                        <div class="container__date_price_port">
                            <div class="title" style="size: 14px; margin-top: 10px">
                                <ctg:currencyTag locale="${sessionScope.locale}" priceInDollars="${cruise.price}"/>
                            </div>
                            <div class="cruise__date"><fmt:message key="label.start_date"/> <dtg:dateTag date="${cruise.startDate}" locale="${sessionScope.locale}"/></div>
                            <div class="cruise__date"><fmt:message key="label.end_date"/> <dtg:dateTag date="${cruise.endDate}" locale="${sessionScope.locale}"/></div>
                            <div class="cruise__port">${cruise.numberOfPorts} <fmt:message
                                    key="showCruises.ports_visited"/></div>

                        </div>
                        <form method="GET" action="${pageContext.request.contextPath}/controller/">
                            <input type="hidden" name="command" value="showCruiseInfo"/>
                            <input type="hidden" name="cruiseId" value="${cruise.id}"/>
                            <button class="button_2" role="button"><fmt:message
                                    key="showCruises.cruise_detailes"/></button>
                        </form>
                    </div>
                </div>

            </div>
        </c:forEach>
        <nav>
            <div class="pagination ">
                <c:if test="${requestScope.numberOfPages gt 1}">

                    <c:if test="${requestScope.currentPage != 1}">
                        <li class="">
                            <a class="page-link link-dark"
                               href="${pageContext.request.contextPath}/controller/?command=showCruises&month=${pageContext.request.getParameter("month")}&duration=${pageContext.request.getParameter("duration")}&currentPage=${requestScope.currentPage-1}"><fmt:message
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
                                       href="${pageContext.request.contextPath}/controller/?command=showCruises&month=${pageContext.request.getParameter("month")}&duration=${pageContext.request.getParameter("duration")}&currentPage=${i}">${i}</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
                        <li class="">
                            <a class="page-link link-dark"
                               href="${pageContext.request.contextPath}/controller/?command=showCruises&month=${pageContext.request.getParameter("month")}&duration=${pageContext.request.getParameter("duration")}&currentPage=${requestScope.currentPage+1}"><fmt:message
                                    key="showCruises.pagination.next"/> </a>
                        </li>
                    </c:if>
                </c:if>
            </div>
        </nav>
    </div>

</div>
</body>
<jsp:include page="/jsp/footer.jsp"/>

</html>
