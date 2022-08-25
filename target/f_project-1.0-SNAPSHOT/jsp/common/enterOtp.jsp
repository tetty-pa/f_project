<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>

    <jsp:include page="/jsp/header.jsp"/>
    <style>
        .form-gap {
            padding-top: 100px;
            background-color: #eee;
        }

        .title {
            margin-top: 10px;
        }

        body {
            background-color: #eee;
            color: #505050;

            font-size: 14px;
            font-weight: normal;
            line-height: 1.5;
            text-transform: none
        }
    </style>
</head>

<body>
<div class="form-gap"></div>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-4 col-md-offset-4 bg-white">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="text-center">
                        <h3>
                            <i class="fa fa-lock fa-4x"></i>
                        </h3>
                        <div class="title"><fmt:message key="enterOtp.title"/></div>
                        <%
                            if (request.getAttribute("message") != null) {
                                out.print("<p class='text-danger ml-1'>" + request.getAttribute("message") + "</p>");
                            }
                        %>

                        <div class="panel-body">

                            <form id="enter-otp-form" action="${pageContext.request.contextPath}/controller/"
                                  role="form"
                                  autocomplete="off"
                                  class="form" method="post">

                                <input type="hidden" name="command" value="${requestScope.command}">

                                <div class="form-group">
                                    <div class="input-group">
											<span class="input-group-addon">
                                                <i class="glyphicon glyphicon-envelope color-red"></i></span> <input
                                            id="opt" name="otp" placeholder="<fmt:message key="enterOtp.title"/>"
                                            class="form-control border-danger" type="text" required="required">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <input name="recover-submit"
                                           class="btn btn-secondary btn-block"
                                           value="<fmt:message key="enterOtp.button_confirm"/>" type="submit">
                                </div>

                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>

<%--
<script>
    $(document).ready(function () {
        $('#enter-otp-form').on('submit', function (event) {
            event.preventDefault();
            let form = new FormData(this);
            $.ajax({
                url: "${pageContext.request.contextPath}/controller?command=${requestScope.command}",
                type: 'POST',
                data: form,
                success: function (data, textStatus, jqXHR) {
                    console.log(data)
                    if (data.trim() === 'done') {
                        swal(
                            'Successfully!',
                            'going to login page',
                            'success'
                        ).then((value) => {
                            window.location = "${pageContext.request.contextPath}/jsp/common/login.jsp"
                        });

                    } else {
                        window.location = "${pageContext.request.contextPath}/jsp/common/newPassword.jsp"
                    }

                },
                processData: false,
                contentType: false
            });

        });
    });
</script>
--%>
</body>
</html>