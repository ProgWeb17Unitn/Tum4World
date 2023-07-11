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

        // setto il tema in base al tipo di utente salvato
        // nell'attributo 'tipo' della sessione;
        // se la sessione o l'attributo specificato sono nulli,
        // setto il tema a nullo
        if(session != null && session.getAttribute("tipo") != null)
            theme = (String) session.getAttribute("tipo");
        else
            theme = "none";

        // scrive il tipo di utente nella risposta,
        // la richiesta è inviata da Javascript, che
        // userà la risposta per settare il tema di header e footer
        PrintWriter writer = response.getWriter();
        writer.print(theme);
        writer.close();
    }
}
