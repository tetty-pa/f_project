<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Cruises</title>
    <jsp:include page="/jsp/header.jsp"/>
</head>


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

                    <button id="edit-cruise-button" type="submit" class="btn btn-primary"
                            data-bs-toggle="modal" data-bs-target="#cruise-modal" >
                        Edit
                    </button>

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

<div class="modal fade" id="cruise-modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true"
     style="display: none">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header ui-icon-background">
                <h5 class="title" id="exampleModalLabel">Edit cruise</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${pageContext.request.contextPath}/controller/" method="post">
                <input type="hidden" name="command" value="updateCruise">
                <div class="modal-body">
                    <table class="table" id="cruise-details">
                        <tr>
                            <th scope="row">Name</th>
                            <td><input type="text" value="${pageScope.cruiseName}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Description</th>
                            <td><input type="text" value="${requestScope.description}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Number of ports</th>
                            <td><input type="number" value="${requestScope.numberOfPorts}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Start date</th>
                            <td><input type="date" value="${requestScope.startDate}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">End date</th>
                            <td><input type="date" value="${requestScope.endDate}"/></td>
                        </tr>
                        <tr>
                            <th scope="row">Price</th>
                            <td><input type="number" value="${requestScope.price}"/></td>
                        </tr>

                    </table>


                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <div class="container">
                            <button type="submit" class="btn btn-outline-primary">Save</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>

    </div>
</div>
</html>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        let editStatus = false;
        $('#edit-cruise-button').click(function () {
            if (editStatus === false) {
                $("#cruises-details").hide()
                $("#cruise-edit").show();
                editStatus = true;
                $(this).text("Back")
            } else {
                $("#cruises-details").show()
                $("#cruise-edit").hide();
                editStatus = false;
                $(this).text("Edit")
            }
        })
    });
</script>
