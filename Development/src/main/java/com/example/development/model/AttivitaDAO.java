package com.example.development.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttivitaDAO extends GenericDAO {

    private static final String getAllAttivita = "SELECT * FROM attivita";

    public AttivitaDAO(Connection conn){
        super.conn = conn;
    }

    public List<Attivita> getAllAttivita() {
        List<Attivita> list = new ArrayList<>(3);

        try(PreparedStatement ps = conn.prepareStatement(getAllAttivita)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Attivita attivita = new Attivita();
                    attivita.setCodice(rs.getString("codice"));
                    attivita.setNome(rs.getString("nome"));
                    list.add(attivita);
                }
            }

        }catch(SQLException sqle){
            System.out.println("Errore prendendo tutte le attivita: " + sqle);
            sqle.printStackTrace();
        }

        return list;
    }
}
