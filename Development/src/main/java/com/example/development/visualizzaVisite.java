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

@WebServlet(name = "visualizzaVisite", value = "/visualizzaVisite")
public class visualizzaVisite extends HttpServlet {
    Connection conn;

    VisiteDAO visiteDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        visiteDAO = new VisiteDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        request.getSession(false);

        HttpSession session = request.getSession(false);

        String name = "utente0";

        if (session != null) {
            name = (String) session.getAttribute("username");
        }

        // Utilizzo il Dao per ottenete la lista delle visite per ogni pagina e le visite totali
        List<Visite> visite = visiteDAO.getAllVisite();
        int visiteTot = visiteDAO.getVisiteTotali();

        // Scrivo la risposta come array Json
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        try (PrintWriter out = response.getWriter()) {
            JsonArray array = new JsonArray();
            Gson gson = new Gson();
            array.add(gson.toJson(visiteTot));
            // invece di creare un nuovo oggetto ad ogni iterazione è più efficiente
            // crearne uno solo e riutilizzarlo, il risultato è lo stesso
            for (Visite c : visite) {
                // nelle posizioni dispari dell'array ci sarà il nome della pagina
                // nelle posizioni pari dell'array ci sarà il numero di visite della pagina
                array.add(gson.toJson(c.getPagina()));
                array.add(gson.toJson(c.getVisite()));
            }
            out.println(array);
            out.flush();
            response.setStatus(200);
        } catch (IOException e) {
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
