package com.example.development.filters;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "UserFilter")
public class UserFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String resource = httpRequest.getServletPath();

        if (session == null || session.getAttribute("tipo") == null)
            httpResponse.sendRedirect("../notAuthorized");
        else if (resource.equals("/Admin") && !session.getAttribute("tipo").equals("admin"))
            httpResponse.sendRedirect("../notAuthorized");
        else if (resource.equals("/Aderente") && !session.getAttribute("tipo").equals("aderente"))
            httpResponse.sendRedirect("../notAuthorized");
        else if (resource.equals("/Simpatizzante") && !session.getAttribute("tipo").equals("simpatizzante"))
            httpResponse.sendRedirect("../notAuthorized");

        chain.doFilter(request, response);
    }
}