package com.example.cookies2;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

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
        String scelta= (Arrays.toString(request.getParameterValues("opzione")));

        HttpSession session = request.getSession();
        request.removeAttribute("formNeeded");
        request.setAttribute("updated",true);
        String origin = request.getHeader("referer");
        String encodedURL=origin;

        if(scelta.equals("Disattivati")){
            encodedURL=response.encodeURL(origin);
            /* in realtà questo funziona correttamente se i cookies sono disabilitati, se sono
            abilitati funziona come gli altri, si può togliere che tanto non era un requisito

             */
        }
        else{
            if (scelta.equals("Tecnici")){
                session.setAttribute("Tecnici", "true");
            }
        }
        //System.out.println(encodedURL);
        response.sendRedirect(encodedURL);
    }
}
