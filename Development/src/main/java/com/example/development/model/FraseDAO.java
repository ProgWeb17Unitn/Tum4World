package com.example.development.model;

import java.sql.*;

public class FraseDAO extends GenericDAO {

    private static final String getRandomFrase = "SELECT * FROM frasi ORDER BY random() OFFSET 0 ROWS FETCH NEXT 1 ROW ONLY";

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
                    System.out.println("frase random non trovata");
                }
            }
        }catch(SQLException e){
            System.out.println("request al database fallita");
        }

        return ret;
    }
}

