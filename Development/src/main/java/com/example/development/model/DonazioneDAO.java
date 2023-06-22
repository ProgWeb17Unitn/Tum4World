package com.example.development.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class DonazioneDAO extends GenericDAO {
    private static final String saveDonazione = "INSERT INTO donazioni (username, importo, data) VALUES (?, ?, ?)";
    private static final String getDonazioniMensili = "SELECT YEAR(data) as anno, MONTH(data) as mese, SUM(importo) as importo FROM donazioni GROUP BY YEAR(data), MONTH(data) ORDER BY YEAR(data) ASC";

    public DonazioneDAO(Connection conn){
        this.conn = conn;
    }

    public void save(Donazione d){

        try(PreparedStatement ps = conn.prepareStatement(saveDonazione)){
            ps.setString(1, d.getUsername());
            ps.setInt(2, d.getImporto());
            ps.setDate(3, java.sql.Date.valueOf(d.getData())); // converte java.time.LocalDate in java.sql.Date

            int numNuoveDonazioni = ps.executeUpdate();

            if(numNuoveDonazioni == 0){
                System.out.println("Errore donazione non inserita");
            }

        }catch(SQLException sqle){
            System.out.println(sqle);
        }
    }

    public void getDonazioniJSON(){
        // funziona solo se nel databse ci sono solo donazioni nello stesso anno
        try(PreparedStatement ps = conn.prepareStatement(getDonazioniMensili)){
            try(ResultSet rs = ps.executeQuery()){
                // ogni cella di questo array rappresenta le donazioni di quel mese esempio:
                // donazioni[0] contiene le donaz. di gennaio, donazioni[1] contiene le donaz. di febbraio, ecc ecc
                List<Integer> donazioni= new ArrayList<Integer>(12);

                // inizializza a 0 la lista
                for(int i=0; i<12; i++){
                    donazioni.add(0);
                }

                while(rs.next()){
                    // int anno = rs.getInt("anno");
                    int mese = rs.getInt("mese");
                    int importo = rs.getInt("importo");

                    // somma la donazione al totale di quel mese
                    donazioni.set(mese - 1, donazioni.get(mese - 1) + importo);
                }

                // TODO da implementare
                Gson gson = new Gson();
                gson.toJson(donazioni);
            }
        }catch(SQLException sqle){
            System.out.println(sqle);
        }
    }

    public void delete(Donazione d){

    }






}
