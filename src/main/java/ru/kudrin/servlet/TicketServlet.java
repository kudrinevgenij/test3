package ru.kudrin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kudrin.service.TicketService;
import ru.kudrin.utils.JspHelper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
    private static final TicketService service = TicketService.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Long flightId = Long.valueOf(req.getParameter("flightId"));

        req.setAttribute("tickets", service.findAllByFlightId(flightId));
        req.getRequestDispatcher(JspHelper.getPath("tickets")).forward(req, resp);






//        try(var writer = resp.getWriter()) {
//            writer.write("<h1>Купленные билеты</h1>");
//            writer.write("<ul>");
//            service.findAllByFlightId(flightId).stream().forEach(ticketDto ->
//                    writer.write("""
//                            <li>%s</li>
//                            """.formatted(ticketDto.seatNo())));
//            writer.write("</ul>");
//        }
    }
}
