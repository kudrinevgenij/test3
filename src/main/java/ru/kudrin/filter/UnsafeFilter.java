package ru.kudrin.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.kudrin.dto.UserDto;

import java.io.IOException;

@WebFilter("/admin")
public class UnsafeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UserDto user = (UserDto)((HttpServletRequest)servletRequest).getSession().getAttribute("user");
        if(user != null) {

        } else {
            ((HttpServletResponse)servletResponse).sendRedirect("registration");
        }
    }
}
