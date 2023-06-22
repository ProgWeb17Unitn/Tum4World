package com.example.development.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO extends GenericDAO{
    private static final String getUtentiByTipo = "SELECT * FROM utenti WHERE tipo=?";
    private static final String getUtenteByUsername = "SELECT * FROM utenti WHERE username=?";
    private static final String getAllUtenti = "SELECT * FROM utenti";
    private static final String deleteUtenteByUsername = "DELETE FROM utenti WHERE username=?";
    private static final String saveUtente = "INSERT INTO utenti (username, password, nome, cognome, data_nascita, email, telefono, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public UtenteDAO(Connection conn){
        super.conn = conn;
    }
    public void save(Utente nuovoUtente) throws AlreadyExistsException {
        try(PreparedStatement ps = conn.prepareStatement(saveUtente)){
            // riempie la query con i dati nell'utente da salvare nel database
            ps.setString(1, nuovoUtente.getUsername());
            ps.setString(2, nuovoUtente.getPassword());
            ps.setString(3, nuovoUtente.getNome());
            ps.setString(4, nuovoUtente.getCognome());
            ps.setDate(5, java.sql.Date.valueOf(nuovoUtente.getDataNascita())); // converte java.time.LocalDate in java.sql.Date
            ps.setString(6, nuovoUtente.getEmail());
            ps.setString(7, nuovoUtente.getTelefono());
            ps.setString(8, nuovoUtente.getTipo());

            int numNuoveRighe = ps.executeUpdate(); // executeUpdate ritorna il numero di nuove righe inserite nella tabella

            if(numNuoveRighe == 0){
                System.out.println("errore: 0 nuove righe, utente non inserito");
            }

        }catch(SQLException sqle){
            // 23505 status code di violazione Primary Key: provato ad inserire un record con PK gia' presente nella tabella
            if(sqle.getSQLState().equals("23505")){
                throw new AlreadyExistsException("Utente con questa email gia presente nel database");
            }

        }

    }

    public void deleteByUsername(String username){
        Utente utente = new Utente();
        utente.setUsername(username);
        this.delete(utente);
    }

    public void delete(Utente u){
        try(PreparedStatement ps = conn.prepareStatement(deleteUtenteByUsername)){
            // inserisce nella query l'username dell'utente da eliminare
            ps.setString(1, u.getUsername());

            int numRigheCancellate = ps.executeUpdate();

            if(numRigheCancellate == 0){
                System.out.println("errore 0 rows deleted: utente non cancellato");
            }
        }catch(SQLException sqle){
            System.out.println(sqle);
        }
    }

    public Utente getUtente(String username){
        Utente utente = null; // user returned

        try(PreparedStatement ps = conn.prepareStatement(getUtenteByUsername)){
            // inserisce nella query l'username dell'utente da cercare nel database
            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()){
                if(!rs.next()){ // controlla se il ResultSet è vuoto
                    System.out.println("errore 0 rows found: utente non esiste");

                    utente = null;
                }
                else{
                    utente = new Utente();
                    setAttributi(utente, rs);
                }
            }

        }catch(SQLException sqle){
            System.out.println(sqle);
        }

        return utente;
    }

    private List<Utente> getUtentiByTipo(String tipo){
        List<Utente> list = new ArrayList<Utente>();

        try(PreparedStatement ps = conn.prepareStatement(getUtentiByTipo)){
            ps.setString(1, tipo);

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Utente utente = new Utente();
                    setAttributi(utente, rs);
                    list.add(utente);
                }
            }

        }catch(SQLException sqle){
            System.out.println(sqle);
        }

        return list;
    }

    public List<Utente> getAllUsers(){
        List<Utente> list = new ArrayList<Utente>();

        try(PreparedStatement ps = conn.prepareStatement(getAllUtenti)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Utente utente = new Utente();
                    setAttributi(utente, rs);
                    list.add(utente);
                }
            }

        }catch(SQLException sqle){
            System.out.println(sqle);
        }

        return list;
    }

    public List<Utente> getAllSimpatizzanti(){
        return getUtentiByTipo("simpatizzante");
    }

    public List<Utente> getAllAderenti(){
        return getUtentiByTipo("aderente");
    }

    private void setAttributi(Utente utente, ResultSet rs) throws SQLException {
        utente.setUsername(rs.getString("username"));
        utente.setPassword(rs.getString("password"));
        utente.setNome(rs.getString("nome"));
        utente.setCognome(rs.getString("cognome"));
        utente.setDataNascita(rs.getDate("data_nascita").toLocalDate()); // converte java.sql.Date in java.time.LocalDate
        utente.setEmail(rs.getString("email"));
        utente.setTelefono(rs.getString("telefono"));
        utente.setTipo(rs.getString("tipo"));
    }

}
