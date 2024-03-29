package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO extends GenericDAO{
    private static final String getUtentiByTipo = "SELECT * FROM utenti WHERE tipo=?";
    private static final String getUtenteByUsername = "SELECT * FROM utenti WHERE username=?";
    private static final String getAllUtenti = "SELECT * FROM utenti";
    private static final String deleteUtenteByUsername = "DELETE FROM utenti WHERE username=?";
    private static final String saveUtente = "INSERT INTO utenti (username, password, nome, cognome, data_nascita, email, telefono, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String checkLogin = "SELECT * FROM utenti WHERE username=? AND password=?";

    public UtenteDAO(Connection conn){
        super.conn = conn;
    }
    public void save(Utente nuovoUtente) throws UserAlreadyExistsException {
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

            int nuoveRighe = ps.executeUpdate(); // se fallisce perchè l'utente è gia presente nel db lancia una SQLException

            if(nuoveRighe == 0){
                throw new SQLException();
            }

        }catch(SQLException e){
            // 23505 status code di violazione Primary Key: provato ad inserire un record con PK gia' presente nella tabella
            if(e.getSQLState().equals("23505")){
                throw new UserAlreadyExistsException("Utente con questa email gia presente nel database");
            }
            else{
                e.printStackTrace();
            }
        }

    }

    public void deleteByUsername(String username){
        Utente utente = new Utente();
        utente.setUsername(username);
        this.delete(utente);
    }

    private void delete(Utente u){
        try(PreparedStatement ps = conn.prepareStatement(deleteUtenteByUsername)){
            // inserisce nella query l'username dell'utente da eliminare
            ps.setString(1, u.getUsername());

            int numRigheCancellate = ps.executeUpdate();
            if(numRigheCancellate == 0){
                throw new SQLException("0 righe cancellate");
            }
        }catch(SQLException e){
            System.out.println("Errore cancellando Utente: " + u.getUsername() + ". Causa: " + e);
            e.printStackTrace();
        }
    }

    // se non trova un utente fa una UserNotFoundException
    public Utente getUtente(String username) throws UserNotFoundException {
        Utente utente = null; // user returned

        try(PreparedStatement ps = conn.prepareStatement(getUtenteByUsername)){
            // inserisce nella query l'username dell'utente da cercare nel database
            ps.setString(1, username);

            try(ResultSet rs = ps.executeQuery()){
                if(!rs.next()){ // controlla se il ResultSet è vuoto
                    System.out.println("Errore non esiste utente con username: " + username);
                    throw new UserNotFoundException();
                }
                else{
                    utente = new Utente();
                    setAttributi(utente, rs);
                }
            }

        }catch(SQLException e){
            System.out.println("Errore DB prendendo Utente: " + e);
            e.printStackTrace();
        }

        return utente;
    }

    /*
    public boolean checkLogin(String username, String password){
        boolean isValid = false
                ;
        try(PreparedStatement ps = conn.prepareStatement(checkLogin)){
            // inserisce nella query le credenziali dell'utente da cercare nel database
            ps.setString(1, username);
            ps.setString(2, password);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){ // controlla se la query ha ritornato un record, cioè se esiste un utente con queste credenziali
                    isValid = true;
                }
                else{
                    isValid = false;
                }
            }

        }catch(SQLException sqle){
            System.out.println("Errore controllando credenziali utente: " + sqle);
            sqle.printStackTrace();
        }

        return isValid;
    }
    */


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
            System.out.println("Errore prendendo utente: " + sqle);
            sqle.printStackTrace();
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
            System.out.println("Errore prendendo tutti gli utenti: " + sqle);
            sqle.printStackTrace();
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
