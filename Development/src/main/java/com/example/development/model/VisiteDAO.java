package com.example.development.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisiteDAO extends GenericDAO {

    private static final String incrementViste = "UPDATE visite SET visite=visite+1 WHERE pagina=?";
    private static final String getAllVisite = "SELECT * FROM visite";
    private static final String getVisiteTotali = "SELECT SUM(visite) AS totale FROM visite";

    public VisiteDAO(Connection conn){
        super.conn = conn;
    }

    public void incrementViste(Visite v){
        incrementVisite(v.getPagina());
    }

    public void incrementVisite(String pagina){
        try(PreparedStatement ps = conn.prepareStatement(incrementViste)){
            ps.setString(1, pagina);

            int numRighe = ps.executeUpdate();
            if(numRighe == 0){ // provato ad incrementare viste di una pagina non presente nel database
                System.out.println("Errore visite pagina: " + pagina + " NON ESISTE nel database");
            }

        }catch(SQLException e){
            System.out.println("Errore incrementando visite pagina: " + pagina);
            e.printStackTrace();
        }
    }

    public List<Visite> getAllVisite(){
        List<Visite> visite = new ArrayList<>();

        try(PreparedStatement ps = conn.prepareStatement(getAllVisite)){
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Visite v = new Visite();
                    v.setPagina(rs.getString("pagina"));
                    v.setVisite(rs.getInt("visite"));
                    visite.add(v);
                }
            }
        }catch(SQLException e){
            System.out.println("Errore query elenca visite");
            e.printStackTrace();
        }

        return visite;
    }

    public int getVisiteTotali(){
        int visiteTotali = -1;

        try(PreparedStatement ps = conn.prepareStatement(getVisiteTotali)){
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    visiteTotali = rs.getInt("totale");
                }
                else{
                    System.out.println("Errore calcolo visite totali");
                }
            }
        }catch(SQLException e){
            System.out.println("Errore preparazione query visite totali");
            e.printStackTrace();
        }

        return visiteTotali;
    }


}
