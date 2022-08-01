<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Tetiana Pavlyshyn
  Date: 6/20/2022
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

Select a file to upload: <br/>

<%--<form id="comm1" method="POST" action="${pageContext.request.contextPath}/controller/"  enctype = "multipart/form-data">
    <input type="hidden" name="command" value="uploadDocuments"/>
    <input type = "file" name = "file" size = "50" />
    <br>
    <input class="btn btn-secondary" type = "submit" value = "Upload File" />

</form>--%>
<form class="form_file" id="comm1" method="POST" action="${pageContext.request.contextPath}/controller/"
      enctype="multipart/form-data">
    <input type="hidden" name="command" value="uploadDocuments"/>
    <input class="input_file" type="file" name="file"/>
    <p>Drag your files here or click in this area.</p>

    <br>
    <button class="button_red" type="submit">Upload File</button>

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
