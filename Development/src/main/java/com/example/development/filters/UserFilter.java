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
        String tipo = session == null || session.getAttribute("tipo") == null ? "none" : (String) session.getAttribute("tipo");

        if (tipo.compareTo("none") == 0
                || (resource.compareTo("/user/Admin") == 0 && !tipo.equals("admin"))
                || (resource.compareTo("/user/Aderente") == 0 && !tipo.equals("aderente"))
                || (resource.compareTo("/user/Simpatizzante") == 0 && !tipo.equals("simpatizzante")))
            httpResponse.sendRedirect((httpResponse.encodeRedirectURL("../notAuthorized")));

        chain.doFilter(request, response);
    }
}