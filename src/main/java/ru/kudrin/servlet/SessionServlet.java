package ru.kudrin.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kudrin.dto.UserDto;

import java.io.IOException;

@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {
    private static final String USER = "user";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var user  =  session.getAttribute(USER);
        System.out.println(user);
        if(user == null) {
            user = UserDto.builder()
                    .id(5L)
                    .email("aaa@aa.com")
                    .build();
            session.setAttribute(USER, user);
            System.out.println(user);
        }
    }
}
