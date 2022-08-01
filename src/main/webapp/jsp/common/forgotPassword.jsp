<!doctype html>
<html>
<head>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title></title>

    <%--<link
        href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
        rel='stylesheet'>
    <link href='' rel='stylesheet'>
    <script type='text/javascript'
        src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>--%>
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
<body <%--oncontextmenu='return false' class='snippet-body'--%>>
<div class="container padding-bottom-3x mb-3 mt-5 ">
    <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10">
            <div class="forgot">
                <div class="title m-3">Forgot your password?</div>
                <p>Change your password in three easy steps. This will help you
                    to secure your password!</p>
                <ol class="list-styled">
                    <li><span class="text-primary text-medium">
                    </span>Enter your email address below.
                    </li>
                    <br>
                    <li><span class="text-primary text-medium">
                    </span>Our system will send you an OTP to your email
                    </li>
                    <br>
                    <li><span class="text-primary text-medium">
                    </span>Enter the OTP on the next page
                    </li>
                </ol>
            </div>
            <form class="card mt-4" action="${pageContext.request.contextPath}/controller/" method="POST">
                <input type="hidden" name="command" value="forgotPassword">
                <div class="card-body">
                    <div class="form-group">
                        <label for="email-for-pass">Enter your email address</label>
                        <input
                            class="form-control border-danger" type="text" name="email" id="email-for-pass" required=""><small
                            class="form-text text-muted">Enter the registered email address . Then we'll
                        email a OTP to this address.</small>
                    </div>
                </div>
                <div class="card-footer">
                    <button class="btn btn-outline-secondary" type="submit">Get New
                        Password
                    </button>
                    <button class="btn btn-secondary" type="submit">Back to
                        Login
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>