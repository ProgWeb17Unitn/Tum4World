package com.example.development.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IscrizioneDAO extends GenericDAO {

    private static final String iscriviUtenteAttivita = "INSERT INTO iscrizioni (username, codice_attivita) VALUES (?, ?)";
    private static final String disiscriviUtenteAttivita = "DELETE FROM iscrizioni WHERE username=? AND codice_attivita=?";
    private static final String getIscrizioniUtente = "SELECT codice_attivita FROM iscrizioni WHERE username=?";
    public IscrizioneDAO(Connection conn){
        super.conn = conn;
    }

    public void nuovaIscrizione(String username, String codiceAttivita){
        try(PreparedStatement ps = conn.prepareStatement(iscriviUtenteAttivita)){
            ps.setString(1, username);
            ps.setString(2, codiceAttivita);

            int nuovaIscrizione = ps.executeUpdate(); // ritorna il numero di nuove righe (success = 1 nuova riga, fail = 0 );

            if(nuovaIscrizione == 0){
                throw new SQLException();
            }

        }catch(SQLException sqle){
            System.out.println("Errore iscrizione di " + username + " ad " + codiceAttivita + " non effettuata");
            sqle.printStackTrace();
        }
    }

    public void rimuoviIscrizione(String username, String codiceAttivita){
        try(PreparedStatement ps = conn.prepareStatement(disiscriviUtenteAttivita)){
            ps.setString(1, username);
            ps.setString(2, codiceAttivita);

            int rimossa = ps.executeUpdate(); // ritorna il numero di righe cancellate

            if(rimossa == 0){
                throw new SQLException();
            }

        }catch(SQLException sqle){
            System.out.println("Errore rimozione iscrizione di " + username + " ad " + codiceAttivita + ". Disiscrizione non effettuata");
            sqle.printStackTrace();
        }
    }

    public List<Iscrizione> getIscrizioniUtente(String username){
        // Si potrebbe anche fare un LEFT JOIN con attività, cosi ritorna direttamente tutte le attività e per vedere se l'utente è iscritto si guarderebbe il campo username,
        // sarebbe che se iscrizione.username == "NULL" l'utente non è iscritto, iscrizione.username != NULL allora è iscritto.
        // Si dovrebbe creare un DTO (Data Transfer Object) per trasferire efficientemente il risultato del JOIN alla JSP della pagina privata, ma penso sia overkill per questo progetto
        // Quindi per ottenere tutte le attività bisogna fare una chiamata a AttivitaDAO, che è un po più inefficente

        List<Iscrizione> iscrizioni = new ArrayList<>();
        try(PreparedStatement ps = conn.prepareStatement(getIscrizioniUtente)){
            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Iscrizione isc = new Iscrizione();
                    isc.setUsername(username);
                    isc.setCodiceAttivita(rs.getString("codice_attivita"));
                    iscrizioni.add(isc);
                }
            }
        }catch(SQLException e){
            System.out.println("Errore ricerca iscrizioni utente");
            e.printStackTrace();
        }

        return iscrizioni;
    }


}
