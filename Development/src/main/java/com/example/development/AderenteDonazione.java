package com.example.development;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AderenteDonazione", value="/AderenteDonazione")
public class AderenteDonazione extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("Donazione ricevuta");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Aderente");
        dispatcher.forward(request, response);

        // Aggiunta del cookie Banner se necessario
        if((request.getAttribute("formNeeded") != null)&& (request.getAttribute("updated")==null)){
            RequestDispatcher dispatcher2= request.getRequestDispatcher("static/cookieBanner.html");
            dispatcher2.include(request, response);
        }

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // La form è di tipo POST, non serve la GET (questo comporta che l'url non
        // è accessibile direttamente tramite URL, cosa importante per garantire la consistenza)
        processRequest(request, response);
    }
}
