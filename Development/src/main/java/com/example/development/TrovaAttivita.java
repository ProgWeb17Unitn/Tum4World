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
import java.util.List;

@WebServlet(name = "TrovaAttivita", value = "/TrovaAttivita")
public class TrovaAttivita extends HttpServlet {
    Connection conn;

    Iscrizione iscrizione;

    IscrizioneDAO iscrizioneDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        iscrizioneDAO = new IscrizioneDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        /*
            Controllo dalla sessione qual è l'utente che sta effettuando la richiesta. In teoria se l'utente è nella pagina Aderente/Simpatizzante
            ciò significa che la sessione è presente e contiene il suo nome, può comunque succedere che la sessione scada o vengano
            cancellate dal browser le informazioni per recuperarla, in quel caso l'else "esterno" ritorna uno status 500. Se la sessione è presente recupero il nome
            dell'utente ed utilizzo la classe Iscrizione (Bean) ed il suo Dao per recuperare l'array che contiene i codici delle attività a cui è iscritto.
            Converto successivamente l'array in un Json Array.
            Se per qualche motivo l'iscrizione fallisce ritorna 500.
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
            List<Iscrizione> iscrizioni = iscrizioneDAO.getIscrizioniUtente(name);

            // Utilizzo il Dao per ricevere la lista delle iscrizioni effettuate dato l'username
            // Scrivo la risposta come array Json
            try (PrintWriter out = response.getWriter()) {
                JsonArray array = new JsonArray();
                Gson gson = new Gson(); // invece di creare un nuovo oggetto ad ogni iterazione è più efficiente
                // crearne uno solo e riutilizzarlo, il risultato è lo stesso
                for (Iscrizione c : iscrizioni) {
                    array.add(gson.toJson(c.getCodiceAttivita()));
                }
                out.println(array);
                out.flush();
                response.setStatus(200);
            } catch (IOException e) {
                response.setStatus(500);
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
