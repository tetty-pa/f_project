<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="page_content"/>

<html>
<head>
    <title>Payment page</title>
    <jsp:include page="/jsp/header.jsp"/>
</head>
<body>
<style>
    :root {
        --black: #525252;
        --orange: #FF4742;
        --white: #F7FBFC;
    }

    #page {
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #main {
        width: 41.875rem;
        height: 18.4375rem;
        background-color: var(--black);
        position: relative;
        z-index: 1;
        justify-content: left;
    }
    h2 {
        color: white;
        font-size: 18px;
        text-align: center;
        margin-top: .5rem;
    }

    img {
        width: 100%;
        height: auto;
        object-fit: cover;
    }

    .card {
        width: 17.5rem;
        height: 11rem;
        background-color: var(--white);
        border-radius: .7rem;
        padding: 1.3rem 1.6rem;
        position: absolute;
        top: -2.75rem;
        right: 4.4rem;
    }

    .card:after {
        content: '';
        display: block;
        width: 17.5rem;
        height: 11rem;
        background-color: #2A2A2A;
        border-radius: .7rem;
        position: absolute;
        top: 2.7rem;
        right: -1.5rem;
        transform: rotate(8deg);
        z-index: -1;
    }

    .chip {
        width: 3rem;
        height: 2.2rem;
        margin-bottom: .7rem;
    }

    form {
        display: flex;
        flex-wrap: wrap;
        position: relative;
    }

    form label {
        width: 100%;
        display: flex;
        flex-direction: column;
        font-size: .55rem;
        color: #C2C2C2;
        margin-top: .35rem;
    }

    form label[for=name] {
        width: 72%;
    }

    form label[for=date] {
        width: 22%;
        margin-left: 6%;
    }

    form label[for=cvc] {
        width: 15%;
        position: absolute;
        right: 7%;
        bottom: -3.9rem;
    }

    form label[for=remember] {
        width: auto;
        height: 2.8rem;
        font-size: .7rem;
        position: absolute;
        left: -1.5rem;
        bottom: -10rem;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: row-reverse;
    }

    form input {
        margin-right: .5rem;
        color: red;
        width: 100%;

    }

    form input {
        border: none;
        outline: none;
        background-color: transparent;
        font-size: 1.1rem;

    }

    form#cvc {
        color: white;
    }


    .price-container {
        position: absolute;
        bottom: .6rem;
        left: 3.4rem;
        display: inline-block;
    }

    .price-container strong {
        font-size: 2.2rem;
        color: white;
    }

    .price-container small {
        font-size: 0.6rem;

    }

    .container__file_uploading {
        margin: 0px 0px 0px 0px;
        height: 600px;
        width: 80%;
        position: absolute;
        display: inline-block;

    }
</style>


<div class="container">
    <div class="title"><fmt:message key="pay.title"/></div>
    <div id="page">
        <div id="main">
            <div class="card">
                <div class="chip">
                    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 230 384.4 300.4" width="38" height="70">
                        <path d="M377.2 266.8c0 27.2-22.4 49.6-49.6 49.6H56.4c-27.2 0-49.6-22.4-49.6-49.6V107.6C6.8 80.4 29.2 58 56.4 58H328c27.2 0 49.6 22.4 49.6 49.6v159.2h-.4z"
                              data-original="#FFD66E" data-old_color="#00FF0C" fill="rgb(237,237,237)"></path>
                        <path d="M327.6 51.2H56.4C25.2 51.2 0 76.8 0 107.6v158.8c0 31.2 25.2 56.8 56.4 56.8H328c31.2 0 56.4-25.2 56.4-56.4V107.6c-.4-30.8-25.6-56.4-56.8-56.4zm-104 86.8c.4 1.2.4 2 .8 2.4 0 0 0 .4.4.4.4.8.8 1.2 1.6 1.6 14 10.8 22.4 27.2 22.4 44.8s-8 34-22.4 44.8l-.4.4-1.2 1.2c0 .4-.4.4-.4.8-.4.4-.4.8-.8 1.6v74h-62.8v-73.2-.8c0-.8-.4-1.2-.4-1.6 0 0 0-.4-.4-.4-.4-.8-.8-1.2-1.6-1.6-14-10.8-22.4-27.2-22.4-44.8s8-34 22.4-44.8l1.6-1.6s0-.4.4-.4c.4-.4.4-1.2.4-1.6V64.8h62.8v72.4c-.4 0 0 .4 0 .8zm147.2 77.6H255.6c4-8.8 6-18.4 6-28.4 0-9.6-2-18.8-5.6-27.2h114.4v55.6h.4zM13.2 160H128c-3.6 8.4-5.6 17.6-5.6 27.2 0 10 2 19.6 6 28.4H13.2V160zm43.2-95.2h90.8V134c-4.4 4-8.4 8-12 12.8h-122V108c0-24 19.2-43.2 43.2-43.2zm-43.2 202v-37.6H136c3.2 4 6.8 8 10.8 11.6V310H56.4c-24-.4-43.2-19.6-43.2-43.2zm314.4 42.8h-90.8v-69.2c4-3.6 7.6-7.2 10.8-11.6h122.8v37.6c.4 24-18.8 43.2-42.8 43.2zm43.2-162.8h-122c-3.2-4.8-7.2-8.8-12-12.8V64.8h90.8c23.6 0 42.8 19.2 42.8 42.8v39.2h.4z"
                              data-original="#005F75" class="active-path" data-old_color="#005F75"
                              fill="rgba(0,0,0,.4)"></path>
                    </svg>
                </div>
                <form action="#">
                    <label for="number"><fmt:message key="pay.card_number"/>
                        <input type="text" id="number" placeholder="0000 - 0000 - 0000 - 0000">
                    </label>
                    <label for="name"><fmt:message key="label.name"/>
                        <input type="text" id="name" placeholder="Jhon Doe">
                    </label>
                    <label for="date"><fmt:message key="pay.valid_thru"/>
                        <input type="text" id="date" placeholder="00/00">
                    </label>
                    <label for="cvc">cvc
                        <input type="text" id="cvc" placeholder="000">
                    </label>
                    <label for="remember"><fmt:message key="pay.save_info"/>
                        <input type="checkbox" checked="checked" id="remember">
                    </label>
                </form>
            </div>
            <div class="price-container">
                <strong><fmt:message key="pay.total_price"/>: ${requestScope.totalPrice}</strong>
                <small><fmt:message key="pay.inc_shipping"/></small>
                <form method="GET" action="${pageContext.request.contextPath}/controller/">
                    <input type="hidden" name="command" value="pay"/>
                    <input class="button_red" style="width: 250px" type="submit" value="<fmt:message key="button.pay_now"/>" name="pay"/>
                    <input type="hidden" name="requestId" value="${requestScope.requestId}"/>
                </form>
            </div>
        </div>
    </div>
    <div class="container__file_uploading">
        <div class="title"><fmt:message key="pay.file_title"/></div>
       <div style="margin-left: 400px"><fmt:message key="pay.select_file"/> <br/>
       </div>
        <form class="form_file" id="comm1" method="POST" action="${pageContext.request.contextPath}/controller/"
              enctype="multipart/form-data">
            <input type="hidden" name="command" value="uploadDocuments"/>
            <input class="input_file" type="file" name="file"/>
            <p><fmt:message key="pay.drag_file"/></p>
            <button class="button_red" style="margin-left: 150px" type="submit">Upload File</button>
        </form>
    </div>
</div>

</body>
</html>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<jsp:include page="/jsp/footer.jsp"/>

<script>
    $(function () {
        $(".input_file").change(function () {
            $(".form_file p").text(this.files.length + " file(s) selected");
        });
    });
</script>