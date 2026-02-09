<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.kudrin.service.FlightService" %>
<%@ page import="ru.kudrin.dto.FlightDto" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Перелеты</title>
</head>
<body>
    <h1>Список перелетов</h1>
    <ul>
    <c:if test="${not empty requestScope.flights}">
        <c:forEach var="flight" items="${requestScope.flights}">
                <li><a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id()}">${flight.description()}</a></li>
        </c:forEach>
    </c:if>
    </ul>
</body>
</html>