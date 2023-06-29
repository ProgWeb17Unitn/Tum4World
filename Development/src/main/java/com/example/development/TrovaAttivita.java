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

@WebServlet(name="TrovaAttivita", value="/TrovaAttivita")
public class TrovaAttivita extends HttpServlet {
    Connection conn;

    Iscrizione iscrizione;

    IscrizioneDAO iscrizioneDAO;

    @Override
    public void init(){

        conn = GenericDAO.getConnection();
        iscrizioneDAO = new IscrizioneDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException{

        request.getSession(false);

        HttpSession session = request.getSession(false);

        String name= "utente0";

        if(session !=null){
            name = (String) session.getAttribute("username");
        }

        List<Iscrizione> iscrizioni=iscrizioneDAO.getIscrizioniUtente(name);

        // Utilizzo il Dao per ricevere la lista delle iscrizioni effettuate dato l'username
        // Scrivo la risposta come array Json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JsonArray array = new JsonArray();
            for(Iscrizione c : iscrizioni) {
                Gson gson = new Gson();
                array.add(gson.toJson(c.getCodiceAttivita()));
            }
            out.println(array);
            out.flush();
        }
        catch(IOException e){
            response.setStatus(600);
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
