<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tlds/currencyTag.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>

    <jsp:include page="/jsp/header.jsp"/>

    <title>Cruises</title>
</head>

<div class="container">
    <div class="title">Find a cruise</div>
    <form id="comm1" method="GET" action="${pageContext.request.contextPath}/controller/">
        <input type="hidden" name="command" value="showCruises">
        <div class="col-md-6 d-inline-flex">
            <div class=" col-md-2 text-uppercase"> duration</div>
            <select class="form-control" name="duration">
                <option>Any</option>
                <option value="2-5">2-5 Days</option>
                <option value="6-9">6-9 Days</option>
                <option value="10">10+ Days</option>
            </select>
            <div class="col-md-2 text-uppercase"> month</div>
            <input class="form-control" type="month" value="month" name="month">
            <input type="hidden" name="currentPage" value="1">

            <button class="btn btn-secondary" role="button">Apply</button>


        </div>
    </form>
</div>

<div class="table__cruises">
    <form <%--method="GET" action="${pageContext.request.contextPath}/controller/"--%>>
        <%--
                <input type="hidden" name="command" value="allCruises"/>
        --%>
        <div class="container">
            <c:forEach var="cruise" items="${requestScope.cruises}">
                <div class="cruise__photo">
                    <img src="${pageContext.request.contextPath}${cruise.cruisePhoto}" alt="cruisePhoto">
                    <div class="cruise__container">

                        <div class="table__cruise_item">
                            <div class="container__name_description">
                                <div class="cruise__name">
                                    <a href="${pageContext.request.contextPath}/controller?command=showCruiseInfo&cruiseId=${cruise.id}">${cruise.cruiseName}</a>
                                </div>
                                <div class="cruise__description">
                                        ${cruise.description}
                                </div>
                            </div>
                            <div class="container__date_price_port">
                                    <%-- i should uncomment this line when i have done localization :/
                                                                    <ctg:currencyTag locale="EN" priceInDollars="${cruise.price}"/>
                                    --%>
                                <div class="cruise__price"> ${cruise.price}</div>
                                <div class="cruise__date">Start Date ${cruise.startDate}</div>
                                <div class="cruise__date">End Date ${cruise.endDate}</div>
                                <div class="cruise__port">${cruise.numberOfPorts} visited ports</div>

                            </div>
                            <form method="GET" action="${pageContext.request.contextPath}/controller/">
                                <input type="hidden" name="command" value="showCruiseInfo"/>
                                <input type="hidden" name="cruiseId" value="${cruise.id}"/>
                                <button class="button_2" role="button">Cruise details</button>

                            </form>
                        </div>
                    </div>

                </div>
            </c:forEach>
            <nav>
                <div class="pagination">
                    <c:if test="${requestScope.numberOfPages gt 1}">

                        <c:if test="${requestScope.currentPage != 1}">
                            <li class="page-item">
                                <a class="page-link "
                                   href="${pageContext.request.contextPath}/controller/?command=showCruises&month=${pageContext.request.getParameter("month")}&duration=${pageContext.request.getParameter("duration")}&currentPage=${requestScope.currentPage-1}">Previous</a>
                            </li>
                        </c:if>

                        <c:forEach begin="1" end="${requestScope.numberOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <li class="page-item "><a class="page-link active">
                                            ${i} <span class="sr-only"></span></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="${pageContext.request.contextPath}/controller/?command=showCruises&month=${pageContext.request.getParameter("month")}&duration=${pageContext.request.getParameter("duration")}&currentPage=${i}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                        <c:if test="${requestScope.currentPage lt requestScope.numberOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/controller/?command=showCruises&month=${pageContext.request.getParameter("month")}&duration=${pageContext.request.getParameter("duration")}&currentPage=${requestScope.currentPage+1}">Next</a>

                            </li>
                        </c:if>
                    </c:if>
                </div>
            </nav>
        </div>
    </form>
</div>

</html>
