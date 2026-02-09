<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.kudrin.service.TicketService" %>
<%@ page import="ru.kudrin.dto.TicketDto" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Купленные билеты</h1>
    <ul>
        <c:forEach var="ticket" items="${requestScope.tickets}">
                <li>${ticket.seatNo()}</li>
        </c:forEach>
    </ul>
</body>
</html>