package com.example.development;

import com.example.development.model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

@WebServlet(name = "popolaDB", value = "/popolaDB")
public class popolaDB extends HttpServlet {
    Connection conn;
    UtenteDAO utenteDAO;
    DonazioneDAO donazioneDAO;
    VisiteDAO visiteDAO;
    FraseDAO fraseDAO;
    AttivitaDAO attivitaDAO;
    IscrizioneDAO iscrizioneDAO;
    Random rand  = new Random();



    @Override
    public void init(){
        conn = GenericDAO.getConnection();
        utenteDAO = new UtenteDAO(conn);
        donazioneDAO = new DonazioneDAO(conn);
        visiteDAO = new VisiteDAO(conn);
        fraseDAO = new FraseDAO(conn);
        attivitaDAO = new AttivitaDAO(conn);
        iscrizioneDAO = new IscrizioneDAO(conn);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request, response);
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // !! e' necessario creare manualmente le tabelle, sono nel file tabelle.txt
        /*
        try{ popolaUtenti(); }
        catch (AlreadyExistsException e)
        { e.printStackTrace(); }
        */


        // popolaDonazioni();
        // popolaVisite();
        // popolaFrasi();

        // per popolare le attività ho eseguito da ij
        // INSERT INTO attivita (codice, nome) VALUES ('attivita1', 'qui il nome attività');

    }

    public void popolaUtenti() throws AlreadyExistsException {
        // crea 100 utenti
        for(int i=0; i<100; i++){
            Utente u = new Utente();
            u.setNome("nome");
            u.setCognome("cognome");
            u.setEmail("email@email.com");
            u.setTelefono("0123456789");
            u.setTipo(new String[]{"simpatizzante", "aderente"}[rand.nextInt(2)]); // sceglie a caso aderente o simpatizzante
            u.setDataNascita(LocalDate.of(1111, 1, 1)); // 1 gennaio 1111
            u.setUsername("utente" + i);
            u.setPassword("testRM1!");
            utenteDAO.save(u);
        }

        // crea l'admin
        Utente a = new Utente();
        a.setNome("admin");
        a.setCognome("admin");
        a.setEmail("admin@admin.adm");
        a.setTelefono("9876543210");
        a.setTipo("admin");
        a.setDataNascita(LocalDate.of(1111, 1, 1)); // 1 gennaio 1111
        a.setUsername("admin");
        a.setPassword("17Adm1n!");
        utenteDAO.save(a);
    }

    public void popolaDonazioni(){
        // assumendo di avere nel db gli utenti generati da popolaUtenti:
        for(int i=0; i<1000; i++){
            Donazione d = new Donazione();
            d.setUsername("utente" + rand.nextInt(100)); // donazione di un utente casuale
            d.setImporto(rand.nextInt(10001 - 100) + 100); // importo da 100 euro a 10000
            d.setData(LocalDate.ofYearDay(2023, rand.nextInt(363) + 1)); // giorno dell'anno 2023 a caso
            donazioneDAO.save(d);
        }
    }

    public void popolaVisite(){
        // viste sono dei valori fittizi
        // il nome delle pagine è del tutto arbitrario: conviene scegliere un nome 'comodo' quando si implementa nei Filters
        String[] pagine = {"Aderente", "Simpatizzante",  "Admin", "Attivita1", "Attivita2", "Attivita3", "ChiSiamo", "contatti", "invioConfermato", "homepage", "signUp", "login", "registrazioneConfermata"};
        for(String pag : pagine){
            Visite v = new Visite();
            v.setPagina(pag);
            v.setVisite(rand.nextInt(10001 - 100) + 100); // pagina ha da 100 a 10000 visite
            visiteDAO.save(v);
        }

    }

    public void popolaFrasi(){
        // frasi prese da internet, sentitevi liberi di modificare, aggiungere o cancellare tutte quelle che volete
        String[] frasi = {
                "Se non crei il tuo piano di vita, cadrai nel piano di qualcun altro",
                "Non rimandare a domani quello che può essere fatto dopodomani altrettanto bene",
                "Se provi a fallire e hai successo, che cosa hai fatto?",
                "Un giorno senza sole è come la notte",
                "Le cose belle arrivano a coloro che aspettano",
                "Se devi pensare, tanto vale pensare in grande",
                "Intendo vivere per sempre. Finora tutto bene",
                "La vita ti offre sempre una seconda possibilità, si chiama \"domani\"",
                "Se non sai dove stai andando, potresti finire in un altro posto",
                "La fortuna è ciò che ti rimane dopo aver dato il 100%",
                "Fare o non fare. Non c’è un tentativo",
                "Va bene guardare il passato e il futuro. Ma non fissarlo",
                "Chiunque abbia detto: \"Non conta se si vince o si perde\", probabilmente ha perso",
                "Se il piano non funziona, cambia il piano",
                "Anche se sei sulla strada giusta, verrai investito se rimani seduto",
                "Se non puoi riparare i tuoi freni, rendi più rumoroso il clacson",
                "Mi hanno buttato in mezzo ai lupi. Ne sono uscito capobranco",
                "La vita dura fino a quando non finisce",
                "Quando vinci, non perdi",
                "La strada per il successo è costellata da molti parcheggi allettanti",
                "Quando sei triste, smetti di esserlo",
                "Le decisioni sbagliate creano belle storie",
                "Non puoi avere tutto. Dove lo metteresti?",
                "Vola come una farfalla, pungi come un'ape",
                "Quando la vita chiude una porta... riaprila",
                "Non smettere mai di imparare, perché la vita non smette mai di insegnare",
                "Ricorda che oggi è il domani di cui ti sei preoccupato ieri",
                "Non fare nulla è molto difficile, non sai mai quando hai finito",
                "Gli haters sono solo ammiratori confusi",
                "L’uomo che sposta una montagna inizia portando via piccole pietre",
                "Anche un orologio fermo ha ragione due volte al giorno"
        };

        for(String text : frasi){
            Frase frase = new Frase();
            frase.setFrase(text);
            fraseDAO.save(frase);
        }

    }



    @Override
    public void destroy(){
        GenericDAO.closeConnection(conn);
    }
}
