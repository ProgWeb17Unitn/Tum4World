package com.example.development;

import com.example.development.model.*;
import com.example.development.model.Attivita;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    Random rand = new Random();


    @Override
    public void init() {
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
        creaTabelle();


        try {
            popolaUtenti();
        } catch (UserAlreadyExistsException e) {
            e.printStackTrace();
        }


        popolaDonazioni();
        popolaAttivita();
        popolaVisite();
        popolaFrasi();
        popolaIscrizioni();

    }

    public void creaTabelle() {
        try {
            Statement s = conn.createStatement();
            try {
                s.executeUpdate("CREATE TABLE utenti ( " +
                        "username VARCHAR(255) NOT NULL, " +
                        "password VARCHAR(255) NOT NULL, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "cognome VARCHAR(255) NOT NULL, " +
                        "data_nascita DATE NOT NULL, " +
                        "email VARCHAR(255) NOT NULL, " +
                        "telefono VARCHAR(20) NOT NULL, " +
                        "tipo VARCHAR(20) NOT NULL, " +
                        "CONSTRAINT pk_user PRIMARY KEY (username), " +
                        "CONSTRAINT tipo_valido CHECK(tipo IN('aderente', 'simpatizzante', 'admin'))" +
                        ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                s.executeUpdate("CREATE TABLE donazioni ( " +
                        "id INT GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1), " +
                        "username VARCHAR(255), " +
                        "importo INT NOT NULL, " +
                        "data DATE NOT NULL, " +
                        "PRIMARY KEY (id), " +
                        "CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES utenti(username)  ON DELETE SET NULL" +
                        ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                s.executeUpdate("CREATE TABLE messaggi( " +
                        "id INT GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1), " +
                        "nomeCognome VARCHAR(255) NOT NULL, " +
                        "email VARCHAR(255) NOT NULL, " +
                        "motivo VARCHAR(255) NOT NULL, " +
                        "testo VARCHAR(5000) NOT NULL" +
                        ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                s.executeUpdate("CREATE TABLE visite( " +
                        "pagina VARCHAR(255) NOT NULL, " +
                        "visite INT NOT NULL, " +
                        "PRIMARY KEY (pagina)" +
                        ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                s.executeUpdate("CREATE TABLE frasi( " +
                        "id INT GENERATED ALWAYS AS IDENTITY(start with 1, increment by 1), " +
                        "frase VARCHAR(10000) NOT NULL" +
                        ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                s.executeUpdate("CREATE TABLE attivita( " +
                        "codice VARCHAR(255) NOT NULL, " +
                        "nome VARCHAR(255) NOT NULL, " +
                        "PRIMARY KEY (codice)" +
                        ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                s.executeUpdate("CREATE TABLE iscrizioni( " +
                        "username VARCHAR(255) NOT NULL, " +
                        "codice_attivita VARCHAR(255) NOT NULL, " +
                        "CONSTRAINT pk_iscrizioni PRIMARY KEY (username, codice_attivita), " +
                        "CONSTRAINT fk_iscritto FOREIGN KEY (username) REFERENCES utenti(username) ON DELETE CASCADE, " +
                        "CONSTRAINT fk_attivita FOREIGN KEY (codice_attivita) REFERENCES attivita(codice)" +
                        ")");
            } catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            System.out.println("Errore DB creando tabelle: " + e);
            e.printStackTrace();
        }

    }

    public void popolaUtenti() throws UserAlreadyExistsException {
        // crea 100 utenti
        for (int i = 0; i < 100; i++) {
            Utente u = new Utente();
            u.setNome("nome");
            u.setCognome("cognome");
            u.setEmail("email@email.com");
            u.setTelefono("0123456789");
            u.setTipo(new String[]{"simpatizzante", "aderente"}[rand.nextInt(2)]); // sceglie a caso aderente o simpatizzante
            int minYear=1900;
            int maxYear=2022;
            int year= rand.nextInt(maxYear-minYear+1)+minYear;
            int month=rand.nextInt(12)+1;
            int day=rand.nextInt(28)+1;
            u.setDataNascita(LocalDate.of(year, month, day));
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

    public void popolaDonazioni() {
        // assumendo di avere nel db gli utenti generati da popolaUtenti:
        for (int i = 0; i < 1000; i++) {
            Donazione d = new Donazione();
            d.setUsername("utente" + rand.nextInt(100)); // donazione di un utente casuale
            d.setImporto(rand.nextInt(10001 - 100) + 100); // importo da 100 euro a 10000
            d.setData(LocalDate.ofYearDay(2023, rand.nextInt(363) + 1)); // giorno dell'anno 2023 a caso
            donazioneDAO.save(d);
        }
    }

    public void popolaAttivita() {
        Attivita attivita1 = new Attivita();
        attivita1.setNome("Salvataggio");
        attivita1.setCodice("attivita1");

        Attivita attivita2 = new Attivita();
        attivita2.setNome("Educazione");
        attivita2.setCodice("attivita2");

        Attivita attivita3 = new Attivita();
        attivita3.setNome("Prevenzione");
        attivita3.setCodice("attivita3");

        attivitaDAO.save(attivita1);
        attivitaDAO.save(attivita2);
        attivitaDAO.save(attivita3);
    }

    public void popolaIscrizioni() {
        iscrizioneDAO.nuovaIscrizione("utente0", "attivita2");
        iscrizioneDAO.nuovaIscrizione("utente1", "attivita1");
        iscrizioneDAO.nuovaIscrizione("utente1", "attivita3");
    }

    public void popolaVisite() {
        // viste sono dei valori fittizi
        // il nome delle pagine è del tutto arbitrario: conviene scegliere un nome 'comodo' quando si implementa nei Filters
        String[] pagine = {"Aderente", "Simpatizzante", "Admin", "Attivita1", "Attivita2", "Attivita3", "ChiSiamo", "contatti", "invioConfermato", "homepage", "signUp", "login", "registrazioneConfermata"};
        for (String pag : pagine) {
            Visite v = new Visite();
            v.setPagina(pag);
            v.setVisite(rand.nextInt(10001 - 100) + 100); // pagina ha da 100 a 10000 visite
            visiteDAO.save(v);
        }

    }


    public void popolaFrasi() {
        // frasi prese da internet, sentitevi liberi di modificare, aggiungere o cancellare tutte quelle che volete
        String[] frasi = {
                "Il modo migliore per superare le difficoltà è attaccarle con un magnifico sorriso",
                "Non sbagliamo mai quando proviamo a fare il nostro dovere, sbagliamo sempre quando trascuriamo di farlo",
                "Se provi a fallire e hai successo, che cosa hai fatto?",
                "Lascia questo mondo un po’ meglio di come l’hai trovato",
                "Le cose belle arrivano a coloro che aspettano",
                "Se devi pensare, tanto vale pensare in grande",
                "Essere buoni è una bella cosa, ma fare del bene è molto di pi",
                "La vita ti offre sempre una seconda possibilità, si chiama \"domani\"",
                "Nessun insegnamento vale quanto l’esempio",
                "La fortuna è ciò che ti rimane dopo aver dato il 100%",
                "Fare o non fare. Non c’è un tentativo",
                "Va bene guardare il passato e il futuro. Ma non fissarlo",
                "Chi è cieco davanti alla bellezza della natura, ha perso la metà del piacere di vivere",
                "Se il piano non funziona, cambia il piano",
                "Guardare la bellezza della natura è il primo passo per purificare la mente",
                "La natura non è un posto da visitare. E' casa nostra.",
                "Non smettere mai di imparare, perché la vita non smette mai di insegnare",
                "L’uomo che sposta una montagna inizia portando via piccole pietre",
                "In tutte le cose della natura esiste qualcosa di meraviglioso"
        };

        for (String text : frasi) {
            Frase frase = new Frase();
            frase.setFrase(text);
            fraseDAO.save(frase);
        }

    }


    @Override
    public void destroy() {
        GenericDAO.closeConnection(conn);
    }
}
