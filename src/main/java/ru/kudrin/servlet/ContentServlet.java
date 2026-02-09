package ru.kudrin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kudrin.service.FlightService;
import ru.kudrin.utils.JspHelper;

import java.io.IOException;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {
    FlightService service = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var flights = service.findAll();
        req.setAttribute("flights", flights);
        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req, resp);
    }
}
