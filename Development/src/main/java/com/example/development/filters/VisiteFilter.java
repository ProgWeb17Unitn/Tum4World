package com.example.development.filters;

import com.example.development.model.GenericDAO;
import com.example.development.model.VisiteDAO;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebFilter(filterName = "VisiteFilter")
public class VisiteFilter implements Filter {
    Connection conn;
    VisiteDAO visiteDAO;

    public void init(FilterConfig config) throws ServletException {
        conn = GenericDAO.getConnection();
        visiteDAO = new VisiteDAO(conn);
    }

    public void destroy() {
        GenericDAO.closeConnection(conn);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if(session != null){
            // registra la vista alla pagina solo se tutti i cookie sono stati accettati
            String cookiePolicy = (String) session.getAttribute("cookiePolicy");
            if(cookiePolicy != null && cookiePolicy.equals("tutti")){

                String thisPage = httpRequest.getServletPath().substring(1); // URI pagina es: /homepage, substring rimuove lo slash /
                System.out.println("this page: " + thisPage);
                visiteDAO.incrementVisite(thisPage);
            }

        }
        else{
            System.out.println("VISITE FILTER: session e' null");
        }

        chain.doFilter(request, response);
    }
}
