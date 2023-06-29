package com.example.development;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name="EmailSending", value="/EmailSending")
public class EmailSending extends HttpServlet {

    // Configurazione server SMTP fittizia
    private String host = "smtp.gmail.com";
    private String port = "587";
    private String user = "tum4world@nessunonoluogonoesiste.com";
    private String pass = "12345678";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // imposta tipo risposta a text. Questa servlet ritorna l'url (invioConfermato.jsp) a cui il client farà redirect in caso di successo
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // legge i dettagli della mail da inviare ( rispettivamente: destinatario, oggetto, contenuto)
        String recipient = request.getParameter("email");
        String subject = request.getParameter("motivo");
        String content = request.getParameter("dettagli");

        String resultMessage = "";

        try {
            EmailUtility.sendEmail(host, port, user, pass, recipient, subject,
                    content);
            resultMessage = "The e-mail was sent successfully";
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            request.setAttribute("Message", resultMessage);
            // invia al client l'url della pagina di conferma (anche in caso di invio fallito in quanto l'invio è simulato)
            PrintWriter writer = response.getWriter();
            writer.print(request.getContextPath() + "/invioConfermato.jsp");
            writer.close();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }
}
