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
            // il parametro formNeeded fa si che nel file JSP venga aggiunto il cookie Banner
            //System.out.println("Session Creation");
        }
        else{
            /*
                Per capire meglio questa sezione guardare prima SessionManager.
                Con i cookies disattivati vi è il problema dell'avere il jsessionid nell'URL. A lezione
                abbiamo visto la soluzione per le servlet in cui utilizzavamo encodeURL e poi tramite il meccanismo di include/forward
                il jsession id "rimaneva" parte dell'URL. Se però il passaggio avviene "direttamente" come nella maggior parte dei casi
                cliccando su un anchor per raggiungere su un altra pagina allora il jsession id verrebbe perso.
                Perciò quando è necessario il filtro lo aggiunge "manualmente"
             */
            //String origin = httpRequest.getHeader("referer");
        }
        chain.doFilter(request, response);
    }
}
