package com.example.development;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="Simpatizzante", value="/Simpatizzante")
public class Simpatizzante extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Simpatizzante.jsp");
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
