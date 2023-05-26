package com.example.development;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import com.example.development.model.AlreadyExistsException;
import com.example.development.model.Utente;
import com.example.development.model.UtenteDAO;

@WebServlet(name = "elaboraSignUp", value = "/elaboraSignUp")
public class elaboraSignUp extends HttpServlet {
    UtenteDAO userDAO;

    @Override
    public void init(){
        userDAO = new UtenteDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            Utente nuovo = new Utente();
            nuovo.setUsername(request.getParameter("username"));
            nuovo.setPassword(request.getParameter("password"));
            nuovo.setNome(request.getParameter("nome"));
            nuovo.setCognome(request.getParameter("cognome"));
            nuovo.setDataNascita(LocalDate.parse(request.getParameter("dataNascita")));
            nuovo.setTelefono(request.getParameter("telefono"));
            nuovo.setTipo(request.getParameter("tipo"));

            userDAO.save(nuovo);

            // redirect alla pagina di conferma
            response.sendRedirect("registrazioneConfermata.jsp");

            /*
            inoltro fatto con .forward se fosse necessario inviare parametri alla JSP registrazioneConfermata
            RequestDispatcher rd = request.getRequestDispatcher("registrazioneConfermata.jsp");
            // inoltra il nome dell'utente alla jsp così da poterlo visualizzare
            request.setAttribute("username", nuovo.getUsername());
            rd.forward(request, response);
            */

        }
        catch(AlreadyExistsException e) {
            // provato a creare un utente con username gia esistente, invia risposta di errore
            response.setStatus(HttpServletResponse.SC_CONFLICT); // status code 409
            response.setContentType("text/plain");

            PrintWriter out = response.getWriter();
            out.print("Errore: esiste già un utente con questo username!");
            out.close();
        }



    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void destroy(){
        userDAO = null;
    }
}
