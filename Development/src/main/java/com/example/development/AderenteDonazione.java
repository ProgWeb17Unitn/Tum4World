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

@WebServlet(name = "AderenteDonazione", value = "/AderenteDonazione")
public class AderenteDonazione extends HttpServlet {

    Connection conn;
    DonazioneDAO donazioneDAO;
    Donazione donazione;

    @Override
    public void init() {
        donazione = new Donazione();
        conn = GenericDAO.getConnection();
        donazioneDAO = new DonazioneDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
            Da Js è stata effettuata una richiesta con il parametro quantità già convalidato,
            lo recupero dalla richiesta ed effettuo il parsing. Successivamente controllo dalla
            sessione qual è l'utente che sta effettuando la donazione. In teoria se l'utente è nella pagina Aderente/Simpatizzante
            ciò significa che la sessione è presente e contiene il suo nome, può comunque succedere che la sessione scada o vengano
            cancellate dal browser le informazioni per recuperarla, in quel caso l'else "esterno" ritorna uno status 500. Se la sessione è presente recupero il nome
            dell'utente ed utilizzo la classe Donazione (Bean) ed il suo Dao per salvare la donazione. Se per qualche motivo
            uno dei tre parametri dovesse essere errato o nullo il metodo save fallisce ed anche in questo caso ritorna 500.
         */
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        String quantita = (String) request.getParameter("quantita");
        int number = Integer.parseInt(quantita);

        HttpSession session = request.getSession(false);

        String name = "utente0";
        /* Nome di un utente presente nel database, può essere anche inizializzato ad una
           stringa vuota ma così è più facile effettuare debugging
         */

        if (session != null) {
            name = (String) session.getAttribute("username");
            donazione.setImporto(number);
            donazione.setUsername(name);
            donazione.setData(LocalDate.now());

            if (donazioneDAO.save(donazione)) {
                // se il salvataggio va a buon termine
                response.setStatus(200);
            } else {
                // se il salvataggio va male ritorno un codice 500
                // così che in js viene mostrato l'errore
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
