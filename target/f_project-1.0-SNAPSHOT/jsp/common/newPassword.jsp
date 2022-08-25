<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>
<!doctype html>

<html>
<head>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title>Snippet - BBBootstrap</title>

    <jsp:include page="/jsp/header.jsp"/>

    <style>

        .snippet-body {
            padding-top: 100px;
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
    <title>New Password</title>
</head>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
<body <%--oncontextmenu='return false'--%> class='snippet-body '>
<div class="container">
    <div class="container">
        <div class="row justify-content-center ">
            <div class="col-12 col-md-9 col-lg-7 col-xl-6 mt-0 m-3 ">
                <div class="container bg-white  mt-2 mb-2 px-0 m-5 ">
                    <div class="panel panel-default ">
                        <div class="row justify-content-center align-items-center pt-3 ">
                            <div class="title">
                                <fmt:message key="newPassword.title"/>
                            </div>

                        </div>
                        <div class="pt-3 pb-3">
                            <form id="new-pass-form" class="form-horizontal"
                                  action="${pageContext.request.contextPath}/controller/"
                                  method="POST">
                                <input type="hidden" name="command" value="newPassword">
                                <div class="form-group row justify-content-center px-3">
                                    <div class="col-9 px-0">
                                        <input type="text" name="password" placeholder="&#xf084; &nbsp; <fmt:message key="newPassword.newPassword"/>"
                                               class="form-control border-danger place icon ">
                                    </div>
                                </div>
                                <div class="form-group row justify-content-center px-3">
                                    <div class="col-9 px-0">
                                        <input type="password" name="confPassword"
                                               placeholder="&#xf084; &nbsp; <fmt:message key="newPassword..confirm_new_password"/>"
                                               class="form-control border-danger place icon">
                                    </div>
                                </div>

                                <div class="form-group row justify-content-center">
                                    <div class="col-3 px-3 mt-3">
                                        <input type="submit" value="<fmt:message key="newPassword.button_reset"/>"
                                               class="btn btn-dark">
                                    </div>
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


<script>
    $(document).ready(function () {
        $('#new-pass-form').on('submit', function (event) {
            event.preventDefault();
            let form = new FormData(this);
            $.ajax({
                url: "${pageContext.request.contextPath}/controller?command=newPassword",
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
                        swal(data+
                        'Invalid password',
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