package com.example.development;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Objects;

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
               La soluzione è un work-around del problema poichè non vi è modo di
               creare la sessione senza aggiungere il cookies, così come non è possibile
               utilizzare la encodeURL poiché se i cookies sono attivati non inserisce il jsessionid nell'URL.
               Quindi in questo caso:
               -Rimuovo i cookies creati di default settando il l'expire a -1
               -Aggiungo il session ID "manualmente" all'URL
             */

            Cookie [] cookies= request.getCookies();
            if(cookies !=null) {
                for (Cookie i : cookies) {
                    System.out.println(i.toString());
                    i.setMaxAge(0);
                    response.addCookie(i);
                }
            }

            // controllo che la rescrizione dell'URL non sia già avvenuta per il CASO 1
            if(!encodedURL.contains("jsessionid")){;
                String JSESSIONID=session.getId();
                encodedURL =encodedURL +";jsessionid="+JSESSIONID;
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


/*
    OLD notes:
     Per capire meglio questa sezione guardare prima SessionManager.
                Con i cookies disattivati vi è il problema dell'avere il jsessionid nell'URL. A lezione
                abbiamo visto la soluzione per le servlet in cui utilizzavamo encodeURL e poi tramite il meccanismo di include/forward
                il jsession id "rimaneva" parte dell'URL. Se però il passaggio avviene "direttamente" come nella maggior parte dei casi
                cliccando su un anchor per raggiungere su un altra pagina allora il jsession id verrebbe perso.
                Perciò quando è necessario il filtro lo aggiunge "manualmente"


        //String origin = httpRequest.getHeader("referer");
        String origin = request.getHeader("referer");
        String encodedURL = origin;
        // Gestisco CASO 1--> URL rewriting
        encodedURL = response.encodeURL(origin);
        System.out.println("origin: " + origin+ "  encoded: " + encodedURL);
 */