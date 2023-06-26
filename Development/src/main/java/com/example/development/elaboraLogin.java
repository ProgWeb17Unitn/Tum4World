package com.example.development;

import com.example.development.model.GenericDAO;
import com.example.development.model.RecordNotFoundException;
import com.example.development.model.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "elaboraLogin", value = "/elaboraLogin")
public class elaboraLogin extends HttpServlet {

    Connection conn;
    UtenteDAO utenteDAO;

    @Override
    public void init(){
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

        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");

        boolean credenzialiValide = utenteDAO.checkLogin(username, password);

        if(!credenzialiValide){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // status code 401 Unauthorized (ovvero credenziali sbagliate)
            return;
        }
        else{
            // imposta il cookie di login
            Cookie utenteCookie = new Cookie("username", username);
            response.addCookie(utenteCookie);

            // redirect alla pagina privata dell'utente in base al suo tipo
            try{
                String tipo = utenteDAO.getUtente(username).getTipo();
                RequestDispatcher dispatcher;

                switch (tipo) {
                    case "aderente":
                        dispatcher = request.getRequestDispatcher("aderente.jsp");
                        break;
                    case "simpatizzante":
                        dispatcher = request.getRequestDispatcher("simpatizzante.jsp");
                        break;
                    case "admin":
                        dispatcher = request.getRequestDispatcher("admin.jsp");
                        break;
                    default:
                        // impossibile arrivare qui per i constraint del database (il tipo DEVE essere aderente, simpatizzante o admin)
                        System.out.println("Errore tipo non riconosciuto: utente " + username + " di tipo " + tipo);
                        return;
                }
                dispatcher.include(request, response);

            }catch(RecordNotFoundException e){ // impossibile arrivare in questa exception perchè le credenziali sono valide
                System.out.println("Errore: utente con credenziali valide non trovato nel database");
                e.printStackTrace();
            }

        }

    }

    @Override
    public void destroy(){
        GenericDAO.closeConnection(conn);
    }
}
