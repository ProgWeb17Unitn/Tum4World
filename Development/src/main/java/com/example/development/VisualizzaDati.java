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

        request.getSession(false);

        HttpSession session = request.getSession(false);

        String name = "utente0";

        if (session != null) {
            name = (String) session.getAttribute("username");
        }
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
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
                response.setStatus(200);
                out.println(array);
                out.flush();
            } catch (IOException e) {
                System.out.println("err1");
                response.setStatus(500);
            }

        } catch (UserNotFoundException e) {
            System.out.println("err2");
            response.setStatus(500);
            throw new RuntimeException(e);
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
