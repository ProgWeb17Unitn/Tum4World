package com.example.cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.System.out;

@WebServlet(name="SessionManager", value="/SessionManager")
public class SessionManager extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process_request(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process_request(request, response);
    }

    protected void process_request(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Può essere chiamata da Index da Form o da Index

        String opzione = request.getParameter("opzione");
        System.out.println("opzione" +opzione);

        if (opzione != null) {
            // chiamato da form--> non c'è la sessione, ho ricevuto i dati dal form
            request.getSession();
            request.setAttribute("CookiesVerified", "true");
            request.getRequestDispatcher("/index").forward(request, response);
        } else {
            HttpSession session = request.getSession(false);
            // provenfo da INDEX, controllo se la session esiste
            if (session == null) {
                request.getSession();
                System.out.println("SESSIONNUL");
            } else {
                request.setAttribute("CookiesVerified", "true");
                request.getRequestDispatcher("/index").forward(request, response);
            }
        }
    }
}



