package com.example.development;

import com.example.development.model.GenericDAO;
import com.example.development.model.Iscrizione;
import com.example.development.model.IscrizioneDAO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "IscriviAttivita", value = "/IscriviAttivita")
public class IscriviAttivita extends HttpServlet {
    Connection conn;
    IscrizioneDAO iscrizioneDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        iscrizioneDAO = new IscrizioneDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.getSession(false);

        HttpSession session = request.getSession(false);

        String name = "utente0";

        if (session != null) {
            name = (String) session.getAttribute("username");
        }

        String quantita = (String) request.getParameter("attivita");
        int number = Integer.parseInt(quantita);

        String codiceattivita ="";

        if(number==1){
            codiceattivita="Att1";
        } else if (number==2) {
            codiceattivita="Att2";
        }else{
            codiceattivita="Att3";
        }


        response.setContentType("application/json");

        if (iscrizioneDAO.nuovaIscrizione(name,codiceattivita)) {
            // se il salvataggio va a buon termine
            response.setStatus(200);
        } else {
            // se il salvataggio va male ritorno un codice 500
            // così che in js viene mostrato l'error
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


    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}
