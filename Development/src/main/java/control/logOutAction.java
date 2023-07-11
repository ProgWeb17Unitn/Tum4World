package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "logOutAction", value = "/logOutAction")
public class logOutAction extends HttpServlet {


    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
            Da Js è stata effettuata la richiesta di Logout. Rimuovo dalla session gli attributi tipo e username
            per effettuare il "logout", js si occuperà di reindirizzare l'utente.
         */

        HttpSession session = request.getSession(false);

        response.setContentType("application/json");

        if (session != null) {
            session.removeAttribute("tipo");
            session.removeAttribute("username");
            response.setStatus(200);
        }else{
            response.setStatus(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
