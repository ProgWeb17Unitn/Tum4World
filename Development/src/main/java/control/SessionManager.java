package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "SessionManager", value = "/SessionManager")
public class SessionManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
        Il Banner è stato inviato, ho 4 opzioni (in base al valore del form cliccato):

        1)Scelta qualsiasi ma cookies disattivati dal browser (URL Rewriting)
        2)Tecnici  (JSESSION ID salvato automaticamente dal browser)
        3)Tutti (JSESSION ID salvato automaticamente dal browser)
        4)Disattivati ma cookies abilitati nel browser

        */

        String scelta = request.getParameter("opzione");
        HttpSession session = request.getSession();
        String origin = request.getHeader("referer"); //ottengo l'url dalla quale ho cliccato il banner

        String encodedURL = response.encodeURL(origin); // CASO 1: cookies disattivati: uso URLRewriting

        /*
            Negli esempi a lezione avevamo visto che in caso di Cookeis disattivati era possibile utilizzare
            il meccanismo di urlRewriting che faceva gestire al contaiener in automatico la rescrizione dell'url
            con l'aggiunta del jsessionID. Vi è un "problema" però rispetto agli esempi visti a lezione: il jsessionID
            rimane nell'url se il passaggio avviene tramite include/forward, mentre se si clicca su un anchor HTML non viene
            aggiunto. Per questo gli anchor presenti nel sito hanno all'interno la verifica della necessità dell'URL rewriting.
         */

        request.removeAttribute("formNeeded");
        request.setAttribute("updated", true);
        // rimuovo la condizione per includere a video il banner
        // utilizzo il parametro updated perché se avessi solo utilizzato il
        // formNeeded il filtro lo ri-setterebbe e avrei di nuovo il banner

        switch (scelta) {
            case "Tecnici":
                // JSESSION ID salvato automaticamente dal browser
                // imposto cookiePolicy solo cookie tecnici (no statistiche quindi non incrementa le visite)
                session.setAttribute("cookiePolicy", "tecnici");
                break;
            case "Tutti":
                session.setAttribute("cookiePolicy", "tutti");
                break;
            case "Disattivati":

               /* CASO 4:
                   Questa opzione non è direttamente implementabile poiché se i cookies sono attivati
                   l'encodeURL() non inserisce il jsessionid "automaticamente" tra le richieste nell'URL.
                   Questo è un problema "intrinseco" del framework in quanto è possibile nel web.xml decidere
                   la modalità di session tracking (URL o cookies) ma non è modificabile dinamicamente.

                   Un possibile work-around della soluzione sarebbe creare normalmente la session, salvarsi il
                   JsessionID, ciclare sui cookies creati e settare il maxAge a 0 per eliminarli. Una volta fatto questo si potrebbe
                   utilizzare l'input type hidden per passare il jsessionid tra le richieste.

                   La richiesta del progetto chiedeva di gestire i cookies disattivati da browser cosa che è stata
                   realizzata con l'urlEncoding per i casi 1,2,3. Per quanto riguarda la scelta di disattivarli quindi si è deciso di lasciare il cookies
                   formato durante la creazione della session non salvando però le azioni dell'utente in questo abbia preso questa scelta.
                   Se avessimo cancellato il cookies o non creato la sessione, questo avrebbe comportato che ad ogni pagina il cookie banner sarebbe ricomparso (nel
                   caso in cui i cookies erano attivati nel browser) e non sarebbe stato possibile effettuare il login.

                   Un approccio più "reale" ed in linea con la regolamentazione italiana ed Europea sarebbe stato quello di creare
                   all'accesso dell'utente ad una pagina la sessione e fornire solo due possibilità: accettare i cookies tecnici
                   (già creati all'accesso) oppure tutti. Ma poichè era richiesta un opzione "disattivati" abbiamo fatto quanto
                   appena descrtitto.
                */
                session.setAttribute("cookiePolicy", "disattivati");
                break;
        }

        /*
            In automatico il Container aggiunge il JSESSIONID nella pagina in cui il cookie viene accettato
            (mentre nelle successive poi no se non è necessario).
            (encodeURL modifica l'origin URL solo nella prima pagina (nel caso in cui i cookies siano attivi)).
        */
        response.sendRedirect(encodedURL);
    }
}

