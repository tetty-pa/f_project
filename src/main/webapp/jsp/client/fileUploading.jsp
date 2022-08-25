<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File Uploading</title>
</head>
<body>

<fmt:message key="label.select_file"/> <br/>

<form id="comm1" method="POST" action="${pageContext.request.contextPath}/controller/"
      enctype="multipart/form-data">
    <input type="hidden" name="command" value="uploadDocuments"/>
    <input class="input_file" type="file" name="file"/>
    <p><fmt:message key="label.drag_files"/></p>

    <br>
    <button class="button_red" type="submit"><fmt:message key="button.upload_file"/></button>

</form>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>

<script>
    $(function () {
        $(".input_file").change(function () {
            $(".form_file p").text(this.files.length + " file(s) selected");
        });
    });
</script>


</body>
</html>