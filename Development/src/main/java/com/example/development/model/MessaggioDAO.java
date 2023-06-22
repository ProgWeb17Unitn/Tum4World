package com.example.development.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessaggioDAO extends GenericDAO{
    private static final String saveMessaggio = "INSERT INTO messaggi (nomeCognome, email, motivo, testo) VALUES (?, ?, ?, ?)";

    public MessaggioDAO(Connection conn){
        super.conn = conn;
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
