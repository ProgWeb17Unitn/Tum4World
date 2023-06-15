package com.example.development;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name="signUp", value="/signUp")
public class signUp extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("signUp.jsp");
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
