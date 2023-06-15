package com.example.development;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name="Attivita1", value="/Attivita1")
public class Attivita1 extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Attivita1.jsp");
        dispatcher.include(request, response);

        // Aggiunta del cookie Banner se necessario
        if((request.getAttribute("formNeeded") != null)&& (request.getAttribute("updated")==null)){
            RequestDispatcher dispatcher2= request.getRequestDispatcher("static/cookieBanner.html");
            dispatcher2.include(request, response);
        }

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
}
