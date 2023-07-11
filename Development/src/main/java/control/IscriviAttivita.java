package control;

import model.GenericDAO;
import model.IscrizioneDAO;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "IscriviAttivita", value = "/IscriviAttivita")
public class IscriviAttivita extends HttpServlet {
    Connection conn;
    IscrizioneDAO iscrizioneDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        iscrizioneDAO = new IscrizioneDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        /*
            Da Js è stata effettuata una richiesta con il parametro attivita che ha tre possibili valori 1,2,3.
            lo recupero dalla richiesta ed effettuo il parsing. Successivamente controllo dalla
            sessione qual è l'utente che sta effettuando la richiesta. In teoria se l'utente è nella pagina Aderente/Simpatizzante
            ciò significa che la sessione è presente e contiene il suo nome, può comunque succedere che la sessione scada o vengano
            cancellate dal browser le informazioni per recuperarla, in quel caso l'else "esterno" ritorna uno status 500. Se la sessione è presente recupero il nome
            dell'utente ed utilizzo la classe Iscrizione (Bean) ed il suo Dao per salvare l'Iscrizione. In particolare
            effettuo una conversione del numero passato al corrispettivo "codice" richiesto dal database. Si poteva effettuare direttamente
            in js il passaggio del codice richiesto dal database invece del numero, ma in questo modo si ha una divisione migliore
            dei ruoli ed in caso si volesse cambiare il valore dei codici del database basterebbe modificare i valori qui invece che da js.
            Se per qualche motivo l'azione fallisce ritorna 500.
         */

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        request.getSession(false);
        HttpSession session = request.getSession(false);

        String name = "utente0";
         /* Nome di un utente presente nel database, può essere anche inizializzato ad una
           stringa vuota ma così è più facile effettuare debugging
         */

        if (session != null) {
            name = (String) session.getAttribute("username");
            String attCodice = (String) request.getParameter("attivita");
            int number = Integer.parseInt(attCodice);

            String codiceattivita ="";

            if(number==1){
                codiceattivita="attivita1";
            }else if (number==2) {
                codiceattivita="attivita2";
            }else{
                codiceattivita="attivita3";
            }

            if (iscrizioneDAO.nuovaIscrizione(name,codiceattivita)) {
                // se il salvataggio va a buon termine
                response.setStatus(200);
            } else {
                // se il salvataggio va male ritorno un codice 500
                // così che in js viene mostrato l'errore
                response.setStatus(500);
            }
        }
        else{
            response.setStatus(500);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}
