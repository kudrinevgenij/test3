<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form action="/registration" method="post">
    <label for="name">Имя:
        <input type="text" name="name" id="name">
    </label><br>
    <label for="birthday">Дата рождение:
        <input type="date" name="birthday" id="birthday">
    </label><br>
    <label for="email">Email:
        <input type="text" name="email" id="email">
    </label><br>
    <label for="pwd">Пароль:
        <input type="password" name="pwd" id="pwd">
    </label><br>
    <select>
    <c:forEach var="role" items="${requestScope.roles}">
        <option label="${role}">${role}</option><br>
    </c:forEach>
    </select>
    <br>
    <c:forEach var="gender" items="${requestScope.genders}">
            <input type="radio" name="gender" value="${gender}">${gender}<br>
    </c:forEach>
    <input type="submit" value="Отправить">

    <c:if test="${not empty requestScope.errors}">
        <c:forEach var="error" items="${requestScope.errors}">
            <span>${error.message}</span>
            <br>
        </c:forEach>
    </c:if>
</form>
</body>
</html>