<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Вход</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="email">Email:
        <input type="text" name="email" id="email" required>
    </label><br>
    <label for="password">Пароль:
        <input type="password" name="password" id="password" required>
    </label><br>
    <button type="submit">Логин</button>

    <c:if test="${param.error != null}">
        <div style="color: red">
            <span>Email or password is not correct</span>
        </div>
    </c:if>
</form>
</body>
</html>