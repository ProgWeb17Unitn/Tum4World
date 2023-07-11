package com.example.development;


import com.example.development.model.*;
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
import java.time.LocalDate;

import java.util.List;

@WebServlet(name = "VisualizzaDati", value = "/VisualizzaDati")
public class VisualizzaDati extends HttpServlet {
    Connection conn;
    Utente utente;
    UtenteDAO utenteDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        utenteDAO = new UtenteDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        /*
            Controllo dalla sessione qual è l'utente che sta effettuando la richiesta. In teoria se l'utente è nella pagina Aderente/Simpatizzante
            ciò significa che la sessione è presente e contiene il suo nome, può comunque succedere che la sessione scada o vengano
            cancellate dal browser le informazioni per recuperarla, in quel caso l'else "esterno" ritorna uno status 500. Se la sessione è presente recupero il nome
            dell'utente ed utilizzo la classe Utente (Bean) ed il suo Dao per recuperare i suoi dati. Converto i vari dati dell'utente in un array JSON.
            Se per qualche motivo l'azione fallisce ritorna 500.
         */

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession(false);

        String name = "utente0";
        /* Nome di un utente presente nel database, può essere anche inizializzato ad una
           stringa vuota ma così è più facile effettuare debugging
         */

        if (session != null) {
            name = (String) session.getAttribute("username");
            try {
                utente= utenteDAO.getUtente(name); // utilizzo il DAO per recuperare l'utente
                // Scrivo la risposta come array Json

                try (PrintWriter out = response.getWriter()) {
                    JsonArray array = new JsonArray();
                    Gson gson = new Gson();
                    array.add(gson.toJson(utente.getUsername()));
                    array.add(gson.toJson(utente.getTipo()));
                    array.add(gson.toJson(utente.getNome()));
                    array.add(gson.toJson(utente.getCognome()));
                    array.add(gson.toJson(utente.getEmail()));
                    LocalDate nascita=utente.getDataNascita();
                    array.add(gson.toJson(nascita.toString()));
                    array.add(gson.toJson(utente.getTelefono()));
                    array.add(gson.toJson(utente.getPassword()));
                    out.println(array);
                    out.flush();
                    response.setStatus(200);
                } catch (IOException e) {
                    System.out.println("Errore1 in Visualizza Dati");
                    response.setStatus(500);
                }

            } catch (UserNotFoundException e) {
                System.out.println("Errore2 in Visualizza Dati");
                response.setStatus(500);
                throw new RuntimeException(e);
            }
        }
        else{
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
