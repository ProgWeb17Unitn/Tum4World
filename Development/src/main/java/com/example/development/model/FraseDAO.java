package com.example.development.model;

import java.sql.*;

public class FraseDAO extends GenericDAO {

    private static final String getRandomFrase = "SELECT * FROM frasi ORDER BY random() OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY";
    public static final String save = "INSERT INTO frasi (frase) VALUES (?)";

    public FraseDAO(Connection conn){
        super.conn = conn;
    }

    public Frase getRandomFrase(){
        Frase ret = new Frase();

        try(PreparedStatement ps = conn.prepareStatement(getRandomFrase)){

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    ret.setFrase(rs.getString("frase")); // salva la frase random nel Bean Frase
                }
                else{
                    System.out.println("Frase random non trovata: probabilmente la tabella e' vuota");
                }
            }
        }catch(SQLException e){
            System.out.println("Errore DB: " + e);
            e.printStackTrace();
        }

        return ret;
    }

    public void save(Frase f){
        try(PreparedStatement ps = conn.prepareStatement(save)){
            ps.setString(1, f.getFrase());

            int nuoveRighe = ps.executeUpdate();

            if(nuoveRighe == 0){
                throw new SQLException("Errore frase non salvata nel database");
            }
        }catch(SQLException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }
}

