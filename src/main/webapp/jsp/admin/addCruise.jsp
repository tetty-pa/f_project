<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Cruise Page</title>
    <jsp:include page="/jsp/header.jsp"/>
</head>
<body>

<div class="container">
    <div class="title">Add Cruise Page</div>
    <div style="width:50%; float: left; display: inline-block;">

    <form method="POST" action="${pageContext.request.contextPath}/controller/">
        <input type="hidden" name="command" value="addCruise"/>

        <div >
            name<br>
            <label>
                UA<input type="text" name="nameUa" value="" required/>
            </label>
            <label>
                EN<input type="text" name="nameEn" value="" required/>
            </label>
        </div>
        <div>
            description<br>
            <label>
                UA<input type="text" name="descriptionUa" value="" required/>
            </label>
            <label>
                En<input type="text" name="descriptionEn" value="" required/>
            </label>
        </div>
        <div class="field"><input type="text" name="numberOfPorts" value="" required/>numberOfPorts<br>
        </div>
        <div class="field">
            <input type="text" name="price" value="" required/>price<br>
        </div>

        <div class="field">
            <input type="date" name="startDate" value="" required/>startDate<br>
        </div>
        <div class="field">
            <input type="date" name="endDate" value="" required/>endDate<br>
        </div>
        <div class="field">
            <input type="number" name="linerId"/>LinerId
        </div>
        <br>
        <br>
        <div class="field">
            <input type="submit" value="submit"/>
        </div>

    </form>
    </div>


    <div style="width:50%; float: right; display: inline-block;">
        <%--<div class="table__container">--%>
            <div class="title" style=" margin-top: 0">Ports</div>
            <label>Quick Search: <input id="search" type="text"></label>

            <table class="table table-bordered table-light" id="cruises-details">
                <%--<tr>
                    <th>city</th>
                    <th>country</th>
                    <th></th>
                </tr>--%>
                    <p> port sequence number: <a id="clicks">1</a></p>
                <c:forEach items="${requestScope.ports}" var="port">
                    <tr>
                        <td class="data">${port.city}</td>
                        <td class="data">${port.country}</td>
                        <td>
                            <form method="GET" action="${pageContext.request.contextPath}/controller/">
                                <input type="hidden" name="command" value="addPortToCruise"/>
                                <input type="hidden" name="cruiseId" value="${requestScope.cruiseId}">
                                <input type="hidden" name="portId" value="${port.id}">
                                <input type="hidden" name="sequenceNumber" value=clicks>
                                <input type="datetime-local" value="arrival time" name="time" required>
                                <input type="submit" onclick="onClick()" class="btn btn-outline-secondary" value="add to cruise"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
<%--
        </div>
--%>
        <script>
            $(document).ready(function () {
                const $rows = $("td");

                $("#search").keyup(function () {
                    const val = $.trim(this.value).toUpperCase();
                    if (val === "")
                        $rows.parent().show();
                    else {
                        $rows.parent().hide();
                        $rows.filter(function() {
                            return -1 !== $(this).text().toUpperCase().indexOf(val);
                        }).parent().show();
                    }
                });
            });
            let clicks = 0;

            function onClick() {
                clicks += 1;
                document.getElementById("clicks").innerHTML = clicks;
            };
        </script>
    </div>

</div>
</body>
</html>
