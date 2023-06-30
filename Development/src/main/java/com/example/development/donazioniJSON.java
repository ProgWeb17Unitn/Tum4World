package com.example.development;

import com.example.development.model.DonazioneDAO;
import com.example.development.model.GenericDAO;
import com.google.gson.JsonObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "donazioniJSON", value = "/donazioniJSON")
public class donazioniJSON extends HttpServlet {

    // Questa servlet ritorna un JSON contenente le donazioni dell'anno corrente
    // per avere l'array donazioni in javascript basta fare un JSON.parse( ) della response di questa servlet

    // TODO In futuro si potrebbe aggiungere la possibilità di passare un argomento 'anno' e la servlet
    // ritornerebbe le donazioni dell'anno indicato

    Connection conn;
    DonazioneDAO donazioneDAO;

    @Override
    public void init() {
        conn = GenericDAO.getConnection();
        donazioneDAO = new DonazioneDAO(conn);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // imposta il tipo di risposta a JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        // prende dal database le donazioni dell'anno corrente (cioè un array JSON di 12 elementi)
        String donazioniJSON = donazioneDAO.getDonazioniCurrentYearJSON();

        // scrive la stringa JSON nella risposta
        PrintWriter out = response.getWriter();
        out.print(donazioniJSON);
        out.close();

    }

    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}
