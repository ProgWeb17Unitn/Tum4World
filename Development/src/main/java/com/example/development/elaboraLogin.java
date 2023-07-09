package com.example.development;

import com.example.development.model.GenericDAO;
import com.example.development.model.UserNotFoundException;
import com.example.development.model.Utente;
import com.example.development.model.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "elaboraLogin", value = "/elaboraLogin")
public class elaboraLogin extends HttpServlet {

    Connection conn;
    UtenteDAO utenteDAO;

    @Override
    public void init() {
        conn = GenericDAO.getConnection();
        utenteDAO = new UtenteDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // imposta tipo risposta a text. Questa servlet invia al client l'url a cui fare il redirect (in caso di successo)
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");


        boolean credenzialiValide = utenteDAO.checkLogin(username, password);

        if (!credenzialiValide) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // status code 401 Unauthorized (ovvero credenziali sbagliate)
            return;
        } else {

            try {
                Utente utente = utenteDAO.getUtente(username);

                // aggiunge alla sessione gli attributi di login
                if (session != null) {
                    session.setAttribute("username", username);
                    session.setAttribute("tipo", utente.getTipo());
                }


                // in base al tipo di utente invia al client un link per la sua pagina privata
                String tipo = utente.getTipo();
                PrintWriter writer = response.getWriter();
                switch (tipo) {
                    case "aderente":
                        writer.print(response.encodeRedirectURL(request.getContextPath() + "/Aderente"));
                        break;
                    case "simpatizzante":
                        writer.print(response.encodeRedirectURL(request.getContextPath() + "/Simpatizzante"));
                        break;
                    case "admin":
                        writer.print(response.encodeRedirectURL(request.getContextPath() + "/Amministratore"));
                        break;
                    default:
                        // impossibile arrivare qui per i constraint del database (il tipo DEVE essere aderente, simpatizzante o admin)
                        System.out.println("Errore tipo non riconosciuto: utente " + username + " di tipo " + tipo);
                        return;
                }
                writer.close();


            } catch (
                    UserNotFoundException e) { // impossibile arrivare in questa exception perchè le credenziali sono sempre valide
                System.out.println("Errore DB: utente con credenziali valide non trovato nel database");
                e.printStackTrace();
            }

        }

    }

    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}
