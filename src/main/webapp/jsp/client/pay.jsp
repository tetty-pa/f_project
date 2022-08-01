<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tetiana Pavlyshyn
  Date: 6/18/2022
  Time: 12:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment page</title>
    <jsp:include page="/jsp/header.jsp"/>
</head>
<body>
<div class="container__file_uploading">

    <c:if test="${sessionScope.user.urlDocument == null}">
        <%--
                <a href="${pageContext.request.contextPath}/jsp/client/fileUploading.jsp">upload file</a>
        --%>
        <div class="title"> File Upload:</div>

        <jsp:include page="fileUploading.jsp"/>
    </c:if>
</div>

<div class="col-md-10 mx-auto">
    <c:if test="${requestScope.request.status != 'PAID'}">

    <div class="title"> Total Price ${requestScope.totalPrice}</div>

        <form id="comm3" method="GET" action="${pageContext.request.contextPath}/controller/">
            <input type="hidden" name="command" value="pay"/>
            <input class="button_red" type="submit" value="pay" name="pay"/>
            <input type="hidden" name="requestId" value="${requestScope.requestId}"/>
        </form>

    </c:if>
</div>
 <%--   <c:if test="${requestScope.request.status == 'PAID'}">
        <input class="button_red" type="submit" />
    </c:if>--%>

</body>
</html>
