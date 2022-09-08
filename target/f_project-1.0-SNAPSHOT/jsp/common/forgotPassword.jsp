<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title></title>
   <jsp:include page="/jsp/header.jsp"/>

    <style>
        body {
            background-position: center;
            background-color: #eee;
            background-size: cover;
            color: #505050;
        }

        .forgot {
            background-color: #fff;
            padding: 12px;
            border: 1px solid #dfdfdf;
            height: 250px;
            margin-top: 100px;
        }

        .padding-bottom-3x {
            padding-bottom: 72px !important
        }

        .card-footer {
            background-color: #fff
        }
    </style>
</head>
<body style="height: 100%;">
<div class="container padding-bottom-3x mb-3 mt-5 ">
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10">
            <div class="forgot">
                <div class="title m-3"><fmt:message key="forgot_password.title"/> </div>
                <p><fmt:message key="forgot_password.description"/></p>
                <ol class="list-styled">
                    <li><span class="text-primary text-medium">
                    </span><fmt:message key="forgot_password.step1"/>
                    </li>
                    <br>
                    <li><span class="text-primary text-medium">
                    </span><fmt:message key="forgot_password.step2"/>
                    </li>
                    <br>
                    <li><span class="text-primary text-medium">
                    </span><fmt:message key="forgot_password.step3"/>
                    </li>
                </ol>
            </div>
            <form class="card mt-4" action="${pageContext.request.contextPath}/controller/" method="POST">
                <input type="hidden" name="command" value="forgotPassword">
                <div class="card-body">
                    <div class="form-group">
                        <label for="email-for-pass"><fmt:message key="forgot_password.enter_email"/></label>
                        <input
                            class="form-control border-danger" type="text" name="email" id="email-for-pass" required="" value="${sessionScope.user.login}"><small
                            class="form-text text-muted"><fmt:message key="forgot_password.enter_email_sub"/></small>
                    </div>
                </div>
                <div class="card-footer">
                    <button class="btn btn-outline-secondary" type="submit"><fmt:message key="forgot_password.button_get_new_pass"/>
                    </button>
                    <button class="btn btn-secondary" type="submit">
                        <fmt:message key="forgot_password.button_back_to_login"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/jsp/footer.jsp"/>

</body>
</html>