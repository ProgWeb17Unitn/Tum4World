package com.example.development;

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
        Il Banner è stato inviato, ho 4 opzioni:

        1)Scelta qualsiasi ma cookies disattivati dal browser
        2)Disattivati ma cookies abilitati nel browser
        3)Tecnici
        4)Tutti (JSESSION ID salvato automaticamente dal browser)

        */

        String scelta = request.getParameter("opzione");
        HttpSession session = request.getSession();
        String origin = request.getHeader("referer"); //ottengo l'url dalla quale ho cliccato il banner

        String encodedURL = response.encodeURL(origin); // CASO 1: cookies disattivati: uso URLRewriting

        /*
            Negli esempi a lezione avevamo visto che in caso di Cookeis disattivati era possibile utilizzare
            il meccanismo di urlRewriting che faceva gestire al contaiener in automatico la rescrizione dell'url
            con l'aggiunta del jsessionID. Vi è un problema però rispetto agli esempi visti a lezione: il jsessionID
            rimane nell'url se il passaggio avviene tramite include/forward, mentre se si clicca su un anchor non viene
            aggiunto. Per questo gli anchor presenti nel sito hanno all'interno la verifica della necessità dell'URL rewriting
         */

        request.removeAttribute("formNeeded");
        request.setAttribute("updated", true);
        // rimuovo la condizione per mettere a video il filtro
        // utilizzo il parametro updated perché se avessi solo utilizzato il
        // formNeeded il filtro lo ri-setterebbe e avrei di nuovo il banner
        // (motivo per cui rimuovere l'attributo formNeeded è superfluo)


        if (scelta.equals("Disattivati")) {
            /* CASO 2:
               Questa cosa non è direttamente implementabile poiché se i cookies sono attivati
               l'encodeURL() non inserisce il jsessionid "automaticamente" tra le richieste nell'URL.
               Questo è un problema "intrinseco" del framework in quanto è possibile nel web.xml decidere
               la modalità di session tracking (URL o cookies) ma non è modificabile dinamicamente.

               Un possibile work-around della soluzione sarebbe creare normalmente la session, salvarsi il
               JsessionID ciclare sui cookies creati e settare il maxAge a 0 per eliminarli. Una volta fatto questo si potrebbe
               utilizzare l'input type hidden per passare il jsessionid tra le richieste
             */

            String JSESSIONID=session.getId();
            Cookie [] cookies= request.getCookies();
            if(cookies !=null) {
                for (Cookie i : cookies) {
                    System.out.println(i.toString());
                    i.setMaxAge(0);
                    response.addCookie(i);
                }
            }

        } else if (scelta.equals("Tecnici")) {
            //JSESSION ID salvato automaticamente dal browser
            //Aggiungo alla session il parametro tecnici, se sono stati selezionati Tutti
            //questo parametro non sarà presente
            session.setAttribute("Tecnici", "true");
        }
        else if(scelta.equals("Tutti")){
            session.setAttribute("Tutti", "true");
        }

        response.sendRedirect(encodedURL);
    }
}

