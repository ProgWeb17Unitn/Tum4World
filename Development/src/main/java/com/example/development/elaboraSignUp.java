package com.example.development;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;

import com.example.development.model.AlreadyExistsException;
import com.example.development.model.GenericDAO;
import com.example.development.model.Utente;
import com.example.development.model.UtenteDAO;

@WebServlet(name = "elaboraSignUp", value = "/elaboraSignUp")
public class elaboraSignUp extends HttpServlet {

    Connection conn;
    UtenteDAO userDAO;

    @Override
    public void init(){
        // genera nuova connessione
        conn = GenericDAO.getConnection();

        // crea DAO con la Connection
        userDAO = new UtenteDAO(conn);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try{
            System.out.println("process request iniziato");

            Utente nuovo = new Utente();
            nuovo.setUsername(request.getParameter("username"));
            nuovo.setPassword(request.getParameter("password"));
            nuovo.setNome(request.getParameter("nome"));
            nuovo.setCognome(request.getParameter("cognome"));
            nuovo.setDataNascita(LocalDate.parse(request.getParameter("dataNascita")));
            nuovo.setTelefono(request.getParameter("telefono"));
            nuovo.setEmail(request.getParameter("email"));
            nuovo.setTipo(request.getParameter("tipo"));

            /* System.out.println("Nuovo Utente" +
                    "\nusername: " + nuovo.getUsername() +
                    "\nnome: " + nuovo.getNome() +
                    "\ncognome" + nuovo.getCognome() +
                    "\ntipo: " + nuovo.getTipo() +
                    "\nemail: " + nuovo.getEmail() +
                    "\npassword: " + nuovo.getPassword() +
                    "\nData nascita: " + nuovo.getDataNascita()); */

            userDAO.save(nuovo);

            // redirect alla pagina di conferma
            RequestDispatcher rd = request.getRequestDispatcher("registrazioneConfermata.jsp");
            // inoltra il nome utente alla jsp così da poterlo visualizzare
            request.setAttribute("username", nuovo.getUsername());
            rd.include(request, response);

            System.out.println("forwardato");

        }
        catch(AlreadyExistsException e) {
            e.printStackTrace();

            // provato a creare un utente con username gia esistente, invia risposta di errore
            response.setStatus(HttpServletResponse.SC_CONFLICT); // status code 409
            response.setContentType("text/plain");

            PrintWriter out = response.getWriter();
            out.print("Errore: esiste già un utente con questo username!");
            out.close();
        }
        catch(Exception e){
            e.printStackTrace();
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
