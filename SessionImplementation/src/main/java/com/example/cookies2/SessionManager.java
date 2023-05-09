package com.example.cookies2;

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
        1)Scelta qualsiasi ma cookies disattivati dal browser --> utilizzo URL Rewritinf
        2)Disattivati ma cookies abilitati nel browser
        3)Tecnici
        4)Tutti (JSESSION ID salvato automaticamente dal browser)
         */
        String scelta = request.getParameter("opzione");
        System.out.println(scelta);

        HttpSession session = request.getSession();
        String origin = request.getHeader("referer");
        String encodedURL = origin;
        // Gestisco CASO 1--> URL rewriting
        encodedURL = response.encodeURL(origin);

        request.removeAttribute("formNeeded");
        request.setAttribute("updated", true);
        // rimuovo la condizione per mettere a video il filtro
        // utilizzo il parametro updated perchè se avessi solo utilizzzato il
        // formNeeded il filtro lo risetterebbe e avrei di nuovo il banner
        // (motivo per cui rimuovere l'attributo formNeeded è superfluo)


        if (scelta.equals("Disattivati")) {
            /* CASO 2:
               La soluzione è un work-around del problema poichè non vi è modo di
               creare la sessione senza aggiungere il cookies, così come non è possibile
               utilizzare la encodeURL poichè se i cookies sono attivati non ha nessuna azione.
               Quindi in questo caso:
               -Aggiungo il session ID "manualmente" all'URL
               -Rimuovo i cookies creati di default settando il l'expire a -1
             */
            System.out.println("Disattivati");

            // controllo che la rescrizione dell'URL non sia già avvenuta a causa della
            // disattivazione nel browser
            if(Objects.equals(encodedURL, origin)){
                String JSESSIONID=session.getId();
                encodedURL =encodedURL +"JSESSIONID="+JSESSIONID;

                Cookie [] cookies= request.getCookies();
                for(Cookie i:cookies){
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
        response.sendRedirect(encodedURL);
    }
}
