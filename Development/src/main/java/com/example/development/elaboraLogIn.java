package com.example.development;

import com.example.development.model.GenericDAO;
import com.example.development.model.Utente;
import com.example.development.model.UtenteDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "elaboraLogIn", value = "/elaboraLogIn")
public class elaboraLogIn extends HttpServlet {

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

        String username = (String) request.getAttribute("username");
        String password = (String) request.getAttribute("password");
        Utente user = utenteDAO.getUtente(username);

        // controlla se password letta dal DB e password ricevuta dal client non corrispondono
        if(!user.getPassword().equals( password )){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // status code 401 Unauthorized (ovvero credenziali sbagliate)
            return;
        }

        // imposta il cookie di login
        Cookie utenteCookie = new Cookie("username", username);
        response.addCookie(utenteCookie);

        // redirect alla pagina privata dell'utente in base al suo tipo

        String tipo = user.getTipo();
        RequestDispatcher dispatcher;
        if(tipo.equals("aderente")){
             dispatcher = request.getRequestDispatcher("homepage.jsp");
        }
        else if(tipo.equals("simpatizzante")){
            dispatcher = request.getRequestDispatcher("homepage.jsp");
        }
        else if(tipo.equals("admin")){
            dispatcher = request.getRequestDispatcher("homepage.jsp");
        }
        dispatcher.include(request, response);



    }

    @Override
    public void destroy(){
        GenericDAO.closeConnection(conn);
    }
}
