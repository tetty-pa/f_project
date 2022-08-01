<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tetiana Pavlyshyn
  Date: 6/9/2022
  Time: 9:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Cruise</title>
    <jsp:include page="/jsp/header.jsp"/>

</head>
<body>
<div class="container">
    <%--
        <div class="title"> Title</div>
    --%>
    <div class="container__detailed">
        <div class="container__cruise_info">
            <div class="cruise__name">${requestScope.cruise.cruiseName}</div>
            <div class="cruise__description">${requestScope.cruise.description}</div>
            <div class="cruise__price">Price: ${requestScope.cruise.price}$</div>
            <div class="text-black">
                ${requestScope.cruise.numberOfPorts} ports visited,
                dates: ${requestScope.cruise.startDate}-
                ${requestScope.cruise.endDate}
                <div class="text-center text-uppercase">Route: </div>
                    <div class="">
                        <c:forEach var="port" items="${requestScope.cruise.portList}">
                            <c:set var="k" value="${k+1}"/>
                            <c:if test="${k!=requestScope.portList.length}">
                                ${port.city}, ${port.country} -
                            </c:if>
                            <c:if test="${k==requestScope.portList.length}">
                                ${port.city}, ${port.country}
                            </c:if>
                        </c:forEach>
                    </div>
            </div>
        </div>
        <img src="${pageContext.request.contextPath}${requestScope.cruise.liner.linerPhoto}">
        <div class="text-black-50 offset-sm-1">
            ${requestScope.cruise.liner.linerName}, ${requestScope.cruise.liner.passengerCapacity} passengers
        </div>

        <form id="comm" method="POST" action="${pageContext.request.contextPath}/controller/">
            <input type="hidden" name="command" value="makeRequest"/>
            <input type="hidden" name="cruiseId" value="${requestScope.cruise.id}"/>
            <div class="field">
                <br>
                <hr>
                <div class=" col-md-3 "> have many places do you need ?</div>
                <label>
                    <input type="number" value="1" min="1" name="amount" required/>
                </label>amount<br>
                <hr>
            </div>
            <button class="button_red" role="button">Book now</button>

        </form>


    </div>
</div>

</body>
</html>
