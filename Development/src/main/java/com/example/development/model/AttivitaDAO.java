package com.example.development.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttivitaDAO extends GenericDAO {

    private static final String saveAttivita= "INSERT INTO attivita (codice, nome) VALUES (?,?)";
    private static final String getAllAttivita = "SELECT * FROM attivita";
    public static final String save = "INSERT INTO attivita (codice, nome) VALUES (?, ?)";

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

        }catch(SQLException e){
            System.out.println("Errore prendendo tutte le attivita: " + e);
            e.printStackTrace();
        }

        return list;
    }

    public void save(Attivita a){
        try(PreparedStatement ps = conn.prepareStatement(save)){
            ps.setString(1, a.getCodice());
            ps.setString(2, a.getNome());

            int nuoveRighe = ps.executeUpdate();
            if(nuoveRighe == 0){
                throw new SQLException("0 nuove righe");
            }
        }catch(SQLException e){
            System.out.println("Errore attivita non salvata nel database: " + e);
            e.printStackTrace();
        }
    }

    public void save(String codice, String nome){
        Attivita attivita = new Attivita();
        attivita.setCodice(codice);
        attivita.setNome(nome);
        save(attivita);
    }
}
