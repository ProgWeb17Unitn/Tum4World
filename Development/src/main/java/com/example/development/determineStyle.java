package com.example.development;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "determineStyle", value = "/determineStyle")
public class determineStyle extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // il tipo di risposta è text/plain cioè ritorna una normale stringa
        // invece di una pagina html
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(false);
        String theme;

        if (session != null && session.getAttribute("tipo") != null)
            theme = (String) session.getAttribute("tipo");
        else
            theme = "none";

        // scrive la frase nella risposta
        PrintWriter writer = response.getWriter();
        writer.print(theme);
        writer.close();
    }
}
