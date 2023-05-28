package com.example.development.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessaggioDAO implements DAO<Messaggio>{
    private static String saveMessaggio = "INSERT INTO messaggi (nomeCognome, email, motivo, testo) VALUES (?, ?, ?, ?)";
    Connection conn;
    public MessaggioDAO(){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/testDB");

        } catch(Exception sqle){
            System.out.println("Connessione al database fallita: " + sqle);
        }
    }

    public void save(Messaggio m){
        try(PreparedStatement ps = conn.prepareStatement(saveMessaggio)){
            ps.setString(1, m.getNomeCognome());
            ps.setString(2, m.getEmail());
            ps.setString(3, m.getMotivo());
            ps.setString(4, m.getTesto());

            int nuoveRighe = ps.executeUpdate();

            if(nuoveRighe == 0){
                System.out.println("Errore messaggio non salvato nel database");
            }
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
