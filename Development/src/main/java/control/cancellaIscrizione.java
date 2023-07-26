package control;

import model.GenericDAO;
import model.Utente;
import model.UtenteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "cancellaIscrizione", value = "/cancellaIscrizione")
public class cancellaIscrizione extends HttpServlet {
    Connection conn;
    Utente utente;
    UtenteDAO utenteDAO;

    @Override
    public void init() {

        conn = GenericDAO.getConnection();
        utenteDAO = new UtenteDAO(conn);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

         /*
            Controllo quale utente vuole cancellare il proprio profilo. In teoria se l'utente è nella pagina Aderente o Simpatizzante
            ciò significa che la sessione è presente e contiene il suo nome, può comunque succedere che la sessione scada o vengano
            cancellate dal browser le informazioni per recuperarla, in quel caso l'else "esterno" ritorna uno status 500. Se la sessione è presente recupero il nome
            dell'utente ed utilizzo la classe utente (Bean) ed il suo Dao per eliminare il profilo. Invalido inoltre la sessione
            per eliminare i dati riguardo l'utente salvati in essa. Sarebbe stato possibile lasciare attiva la sessione
            e cancellare i singoli parametri ma dato che la sessione non contiene altri dati utili oltre a quelli specifici per l'utente
            era una soluzione più "pulita". Inoltre un nuovo utente può effettuare in questo modo una scelta differente per i cookies.
         */

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession(false);

        String name = "utente0";
        /* Nome di un utente presente nel database, può essere anche inizializzato ad una
           stringa vuota ma così è più facile effettuare debugging
         */

        if (session != null) {
            name = (String) session.getAttribute("username");
            utenteDAO.deleteByUsername(name); // cancello l'Utente
            session.invalidate(); // Invalido la sessione
            response.setStatus(200);
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
