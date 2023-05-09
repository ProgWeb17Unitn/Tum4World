package com.example.cookies2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SessionManager", value = "/SessionManager")
public class SessionManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Il Banner è stato inviato, 3 opzioni:
        -Disattivato (sia come scelta che da browser --> URL rewriting)
        -Tecnici (JSESSION ID salvato automaticamente dal browser, aggiungo alla session il parametro tecnici)
        -Tutti (JSESSION ID salvato automaticamente dal browser)
         */
        String scelta = request.getParameter("opzione");
        System.out.println(scelta);

        HttpSession session = request.getSession();
        request.removeAttribute("formNeeded");
        request.setAttribute("updated", true);
        String origin = request.getHeader("referer");
        String encodedURL = origin;

        if (scelta.equals("Disattivati")) {
            encodedURL = response.encodeURL(origin);
            /* in realtà questo funziona correttamente se i cookies sono disabilitati, se sono
            abilitati funziona come gli altri, si può togliere che tanto non era un requisito
             */
        } else if (scelta.equals("Tecnici")) {
            session.setAttribute("Tecnici", "true");
        }
        response.sendRedirect(encodedURL);
    }
}
