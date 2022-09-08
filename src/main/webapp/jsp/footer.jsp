<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<jsp:include page="/jsp/header.jsp"/>

<style>

    #page-content {
        flex: 1 0 auto;
    }


    .footer > * {
        flex: 1 100%;
    }
</style>


<body class="d-flex flex-column ">
<div id="page-content">

</div>
<footer id="sticky-footer" class="flex-shrink-0 py-4 text-white-50" style="background: rgb(101, 100, 91, 0.5);">
    <div class=" container text-center">
        <small>
            &copy; Copyright 2022. All rights reserved.
            <p>Made with ♥ by CompUs</p>
        </small>
    </div>


</footer>
</body>
</html>
