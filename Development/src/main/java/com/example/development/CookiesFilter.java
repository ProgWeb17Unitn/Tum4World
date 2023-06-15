package com.example.development;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "CookiesFilter")
public class CookiesFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        // l'attributo false impedisce di creare la session se non esiste
        //System.out.println("Filtering");
        if (session == null) {
            request.setAttribute("formNeeded",true);
            // il parametro formNeeded fa si che venga inclusa nella risposta anche il CookieBanner
        }

        chain.doFilter(request, response);
    }
}
