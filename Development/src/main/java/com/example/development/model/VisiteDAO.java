package com.example.development.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

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
    private static final String resetVisite = "UPDATE visite SET visite=0";

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

    public void resetVisite(){
        try(PreparedStatement ps = conn.prepareStatement(resetVisite)){

            int numReset = ps.executeUpdate(); // numero di pagine i cui contatori visite sono stati azzerati

            if(numReset == 0){ // nessun contatore azzerato: errore del database oppure la tabella viste è vuota
                System.out.println("Errore reset contatori visite: nessun azzeramento (tabella vuota)");
            }

        }catch(SQLException e){
            System.out.println("Errore azzeramento visite");
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

    public String getAllVisiteJSON(){
        // prende dal db tutte le visite
        List<Visite> visite = getAllVisite();

        // converte la List in json String. Il risultato sarà un array di oggetti JSON, ad esempio
        /*
          [
             {
                "pagina": "homepage",
                "visite": 84355
             },
             {
                "pagina": "login",
                "visite": 12845
             }
           ]
         */

        Gson gson = new Gson();
        String visiteJSON = gson.toJson(visite);

        return visiteJSON;

        // in alternativa si poteva usare questo metodo per convertire a JsonObject
        // JsonElement visiteJSON = gson.toJsonTree(visite, new TypeToken<List<Visite>>(){}.getType());
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
