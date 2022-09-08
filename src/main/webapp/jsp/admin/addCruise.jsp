<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title>Add Cruise Page</title>
    <jsp:include page="/jsp/header.jsp"/>
</head>
<body style="height: 100%">

<div class="container">
    <div class="title"><fmt:message key="addCruise.title"/></div>

    <div style="width:50%; float: left; display: inline-block;">
        <%
            if(request.getAttribute("message")!=null)
            {
                out.print("<p class='text-danger ml-1'>"+request.getAttribute("message")+"</p>");
            }
        %>
        <form method="POST" action="${pageContext.request.contextPath}/controller/">
            <input type="hidden" name="command" value="putCruiseIntoSession"/>
            <input type="hidden" name="currentPage" value="1">

            <div>
                <fmt:message key="label.name"/><br>
                <label>UA<input type="text" name="nameUa" value="" required/></label>
                <label>EN<input type="text" name="nameEn" value="" required/></label>
            </div>
            <div>
                <fmt:message key="label.description"/><br>
                <label>UA<input type="text" name="descriptionUa" value="" required/></label>
                <label>EN<input type="text" name="descriptionEn" value="" required/></label>
            </div>
            <br>
            <div class="field"><input type="text" name="numberOfPorts" value="" required/>
                <fmt:message key="showAllCruises.number_of_ports"/><br></div>
            <br>
            <div class="field"><input type="text" name="price" value="" required/>
                <fmt:message key="label.price"/><br>
            </div>
            <br>
            <div class="field"><input type="date" name="startDate" value="" required/>
                <fmt:message key="label.start_date"/><br></div>
            <br>
            <div class="field"><input type="date" name="endDate" value="" required/>
                <fmt:message key="label.end_date"/><br></div>
            <br>
            <label>
                <fmt:message key="showAllCruises.liner"/>:
                <select name="linerId">
                    <c:forEach items="${requestScope.liners}" var="liner">
                        <option value="${liner.id}">${liner.linerName}</option>
                    </c:forEach>
                </select>
            </label>
            <br>
            <br>

            <div class="field">
                <input type="submit" class="btn btn-secondary" value="<fmt:message key="button.submit"/>"/>
            </div>
        </form>

    </div>

</div>
<jsp:include page="/jsp/footer.jsp"/>

</body>
</html>
