<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.kudrin.service.TicketService" %>
<%@ page import="ru.kudrin.dto.TicketDto" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <span>CONTENT РУССКИЙ</span>
    <p>Size: ${requestScope.flights.size()}</p>
    <p>description: ${requestScope.flights.get(0).description()}</p>
    <p>id: ${requestScope.flights[1].id()}</p>
    <p>JSESSIONID: ${cookie.get("JSESSIONID")}</p>
    <p>PARAM ID: ${param.id}</p>
    <p>HEADER ID: ${header["cookie"]}</p>
    <p>NOT EMPTY: ${not empty flights}</p>
</div>

</body>
</html>