<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<%@ taglib prefix="ctg" uri="/WEB-INF/tlds/currencyTag.tld" %>
<%@ taglib prefix="dtg" uri="/WEB-INF/tlds/dateTag.tld" %>
<html>
<head>
    <title>Cruise</title>
    <jsp:include page="/jsp/header.jsp"/>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

</head>

<body style="height: 100%">
<div class="container">
    <div class="container__detailed">
        <div class="container__cruise_info">
            <div class="cruise__name">${requestScope.cruise.cruiseName}</div>
            <div class="cruise__description">${requestScope.cruise.description}</div>
            <div class="cruise__price"><fmt:message key="label.price"/>: ${requestScope.cruise.price}$</div>
            <div class="text-black">
                ${requestScope.cruise.numberOfPorts} <fmt:message key="showCruises.ports_visited"/>
                    <br>
                    <div class="text-primary">
                <fmt:message key="showCruiseInfo.dates"/>  <dtg:dateTag date="${requestScope.cruise.startDate}" locale="${sessionScope.locale}"/> -
                    <dtg:dateTag date="${requestScope.cruise.endDate}" locale="${sessionScope.locale}"/>

                    </div>
                    <br>
                    <br>
                <div class="text text-uppercase"><fmt:message key="showCruiseInfo.route"/></div>
                <div class="">
                    <c:forEach var="port" items="${requestScope.cruise.portList}">
                        <c:set var="k" value="${k+1}"/>
                        <c:if test="${k!=fn:length(requestScope.cruise.portList)}">
                            ${port.city}, ${port.country} -
                        </c:if>
                        <c:if test="${k==fn:length(requestScope.cruise.portList)}">
                            ${port.city}, ${port.country}
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
        <img src="${pageContext.request.contextPath}${requestScope.cruise.liner.linerPhoto}">
        <div class="text-black-50 offset-sm-1">
            ${requestScope.cruise.liner.linerName}, ${requestScope.cruise.liner.passengerCapacity} <fmt:message
                key="liner.passengers"/>
        </div>

        <form id="form1" method="POST" action="${pageContext.request.contextPath}/controller/">
            <input type="hidden" name="command" value="makeRequest"/>
            <input type="hidden" name="cruiseId" value="${requestScope.cruise.id}"/>
            <div>
                <br>
                <hr>
                <div class=" col-md-3 " ><fmt:message key="showCruiseInfo.have_many_places"/></div>
                <label>
                    <input type="number" value="1" min="1" name="amount" required/>
                </label><fmt:message key="label.amount"/><br>
                <hr>
            </div>
            <button class="button_red" role="button"><fmt:message key="button.book_now"/></button>

        </form>


    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>


<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>

<script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>


<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<script>
    $(document).ready(function () {
        $('#form1').on('submit', function (event) {
            event.preventDefault();
            let form = new FormData(this);
            $.ajax({
                url: "${pageContext.request.contextPath}/controller?command=makeRequest",
                type: 'POST',
                data: form,
                success: function (data, textStatus, jqXHR) {
                    console.log(data)
                    if (data.trim() === 'done') {
                        swal(
                            'Successfully!',
                            'success'
                        ).then((value) => {
                            window.location = "login.jsp"
                        });

                    } else {
                        swal(data +
                            'Error',
                            'error'
                        );
                    }

                },
                processData: false,
                contentType: false
            });

        });
    });
</script>

</body>
</html>
