<html>
<head>

    <%--	<link
                href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
                rel="stylesheet" id="bootstrap-css">--%>
    <%--<script
            src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>--%>
    <!------ Include the above in your HEAD tag ---------->

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
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
                        <div class="title">Enter OTP</div>
                        <%
                            if (request.getAttribute("message") != null) {
                                out.print("<p class='text-danger ml-1'>" + request.getAttribute("message") + "</p>");
                            }
                        %>

                        <div class="panel-body">

                            <form id="enter-otp-form" action="${pageContext.request.contextPath}/controller/" role="form"
                                  autocomplete="off"
                                  class="form" method="post">
                                <%--
                                                                <input type="hidden" name="command" value="validateOtp">
                                --%>
                                <input type="hidden" name="command" value="${requestScope.command}">

                                <div class="form-group">
                                    <div class="input-group">
											<span class="input-group-addon"><i
                                                    class="glyphicon glyphicon-envelope color-red"></i></span> <input
                                            id="opt" name="otp" placeholder="Enter OTP"
                                            class="form-control border-danger" type="text" required="required">
                                    </div>
                                </div>
                                <hr>
                                <div class="form-group">
                                    <input name="recover-submit"
                                           class="btn btn-secondary btn-block"
                                           value="Confirm" type="submit">
                                </div>

                                <%--<input type="hidden" class="hide" name="token" id="token"
                                    value="">--%>
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