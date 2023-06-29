package com.example.development;

import com.example.development.model.*;
import com.example.development.model.GenericDAO;
import com.example.development.model.Donazione;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;

import com.example.development.model.DonazioneDAO;

@WebServlet(name="AderenteDonazione", value="/AderenteDonazione")
public class AderenteDonazione extends HttpServlet {

    Connection conn;
    DonazioneDAO donazioneDAO;
    Donazione donazione;
    @Override
    public void init(){
        donazione=new Donazione();
        conn = GenericDAO.getConnection();
        donazioneDAO = new DonazioneDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // utilizzo la classe Donazione (Bean) ed il suo Dao per salvare la risposta
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String quantita = (String) request.getParameter("quantita");
        int number = Integer.parseInt(quantita);

        HttpSession session = request.getSession(false);

        String name= "utente0";

        if(session !=null){
            name = (String) session.getAttribute("username");
        }
        donazione.setImporto(number);
        donazione.setUsername(name);
        donazione.setData(LocalDate.now());

        if(donazioneDAO.save(donazione)){
            // se il salvataggio va a buon termine
            response.setStatus(200);
        }
        else {
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
