<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>

<a href="hello">Hello Servlet</a>
<br>
<hr>
</body>
<br>
<v>Hello my dear friends!</v>
<c:forEach var="user" items="${requestScope.users}">
    <ul>
        <li>Логін: <c:out value="${user.login}"/></li>
        <li>Пароль: <c:out value="${user.password}"/></li>
        <li>Ім'я: <c:out value="${user.name}"/></li>
        <li>Прізвище: <c:out value="${user.surname}"/></li>
        <form method="post" action="delete">
            <input type="text" hidden name="login" value="${user.login}"/>
            <label><input type="submit" value="delete"> </label>
        </form>
        <form method="get" action="update">
            <label><input type="submit" value="update"> </label>
        </form>
    </ul>
    <hr>
</c:forEach>
<br>
<b>Create new client</b>
<form method="post" action="hello">
    <label><input type="email" name="login"></label><i>Логін</i><br>
    <label><input type="password" name="password"></label>Пароль<br>
    <label><input type="text" name="name"></label>Ім'я<br>
    <label><input type="text" name="surname"></label>Прізвище<br>
    <input type="submit" value="submit" name="ok"><br>

</form>
<div class="form">
    <form id="registration_form" method="POST" action="controller/">
        <input type="hidden" name="command" value="login"/>
        <div class="field">
            <input type="text" name="name" value="" required/></label>Ім'я<br>
        </div>
        <div class="field">
            <input type="text" name="surname" value="" required/></label>Прізвище<br>
        </div>
        <div class="field">
            <input type="text" name="login" value="" required/></label>Логін<br>
        </div>
        <div class="field">
            <input type="password" name="password" value="" required/></label>Пароль<br>
        </div>
        <br>
        <div class="field">
            <input type="reset"/>
        </div>
        <br>
        <div class="field">
            <input type="submit"/>
        </div>
    </form>
</div>
<div class="form">
    <form id="comm" method="POST" action="controller/">
        <input type="hidden" name="command" value="allCruises"/>
        <div class="field">
            <input type="submit"/>
        </div>
    </form>
</div>

 <br>


<a href="${pageContext.request.contextPath}/jsp/common/login.jsp">
    <button>Login</button>
</a>
<br>

<h3>File Upload:</h3>
Select a file to upload: <br/>

<form id="comm4" method="POST" action="controller?command=uploadDocuments" enctype="multipart/form-data">
    <input type="file" name="file" size="50"/>
    <br/>
    <input type="submit" value="Upload File"/>
</form>


</html>
