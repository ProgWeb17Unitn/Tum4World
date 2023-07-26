package control;

import model.Frase;
import model.FraseDAO;
import model.GenericDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "fraseRandom", value = "/fraseRandom")
public class fraseRandom extends HttpServlet {

    // connessione al db
    Connection conn;
    FraseDAO fraseDAO;

    @Override
    public void init() {
        // genera una nuova connessione
        conn = GenericDAO.getConnection();

        // crea un DAO con questa Connection
        // la stessa connection può essere riutilizzata da più DAO, cosi segue la filosofia 'una connection per servlet'
        // !!! ricordarsi poi di chiudere questa connection (con il metodo di GenericDAO) nel destroy() della servlet !!!
        fraseDAO = new FraseDAO(conn);
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
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // legge la frase random dal database e la salva nel Bean Frase
        Frase frase = fraseDAO.getRandomFrase();

        // scrive la frase nella risposta
        PrintWriter writer = response.getWriter();
        writer.print(frase.getFrase());
        writer.close();
    }

    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}

