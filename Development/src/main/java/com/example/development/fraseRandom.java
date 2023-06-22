package com.example.development;

import com.example.development.model.Frase;
import com.example.development.model.FraseDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "fraseRandom", value = "/fraseRandom")
public class fraseRandom extends HttpServlet {
    FraseDAO fraseDAO;
    @Override
    public void init(){
        fraseDAO = new FraseDAO();
    }
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
        response.setContentType("text/plain;charset=UTF-8");

        // legge la frase random dal database e la salva nel Bean Frase
        Frase frase = fraseDAO.getRandomFrase();

        // scrive la frase nella risposta
        PrintWriter writer = response.getWriter();
        writer.print(frase.getFrase());
        writer.close();
    }

    @Override
    public void destroy(){
        // TODO fare un metodo dedicato per chiudere la connessione
        fraseDAO = null;
    }
}

