<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>
<html>
<head>
    <title>Registration Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/file.css"></head>
<body>

<div class="custom__container">
    <div class="registration">
        <a class="logo" href="${pageContext.request.contextPath}/index.jsp">
            <img class="logo"
                 src="${pageContext.request.contextPath}/img/logo.png"
                 alt=""></a>

        <div class="title"><fmt:message key="registration.title"/> </div>
        <%
            if(request.getAttribute("message")!=null)
            {
                out.print("<p class='text-danger ml-1'>"+request.getAttribute("message")+"</p>");
            }
        %>
        <form id="reg-form" method="POST" action="${pageContext.request.contextPath}/controller/">
            <input type="hidden" name="command" value="registration"/>
            <div class="input__field">
                <input type="text" name="name" value="" required/>
                <label><fmt:message key="label.name"/></label>
            </div>
            <div class="input__field">
                <input type="text" name="surname" value="" required/>
                <label><fmt:message key="label.surname"/></label>
            </div>
            <div class="input__field">
                <input type="text" name="email" value="" required/>
                <label><fmt:message key="label.email"/></label>
            </div>
            <div class="input__field">
                <input type="password" name="password" value="" required/>
                <label><fmt:message key="label.password"/></label>
            </div>
            <br>
            <button type="submit" id="submit-reg" class="button_1"><fmt:message key="button.sign_up"/></button>

            <div class="style__text">
                <a href="${pageContext.request.contextPath}/jsp/common/login.jsp"> <fmt:message key="button.sign_in"/> </a>
            </div>

        </form>
    </div>
</div>


<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>

<%--
<script>
    $(document).ready(function () {
        $('#reg-form').on('submit', function (event) {
            event.preventDefault();
            let form = new FormData(this);
            $.ajax({
                url: "${pageContext.request.contextPath}/controller?command=registration",
                type: 'POST',
                data: form,
                success: function (data, textStatus, jqXHR) {
                    console.log(data)
                    if (data.trim() === 'done') {
                        swal(
                            'You registered successfully!',
                            'going to login page',
                            'success'

                        ).then((value) => {
                            window.location = "login.jsp"
                        });

                    } else {
                        swal(data);
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
