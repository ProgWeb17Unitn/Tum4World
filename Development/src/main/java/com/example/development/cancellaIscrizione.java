package com.example.development;

import com.example.development.model.GenericDAO;
import com.example.development.model.Utente;
import com.example.development.model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "cancellaIscrizione", value = "/cancellaIscrizione")
public class cancellaIscrizione extends HttpServlet {
    Connection conn;
    Utente utente;
    UtenteDAO utenteDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        utenteDAO = new UtenteDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession(false);

        HttpSession session = request.getSession(false);

        String name = "utente0";

        if (session != null) {
            name = (String) session.getAttribute("username");
            session.invalidate(); // invalido la sessione
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
        utenteDAO.deleteByUsername(name); // cancello l'username
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}
