package com.example.development.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class DonazioneDAO extends GenericDAO {
    private static final String saveDonazione = "INSERT INTO donazioni (username, importo, data) VALUES (?, ?, ?)";
    private static final String getAllDonazioniMensili = "SELECT YEAR(data) as anno, MONTH(data) as mese, SUM(importo) as importo FROM donazioni GROUP BY YEAR(data), MONTH(data) ORDER BY YEAR(data) ASC";
    private static final String getDonazioniByYear = "SELECT MONTH(data) as mese, SUM(importo) as importo FROM donazioni WHERE YEAR(data)=? GROUP BY MONTH(data)";

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
                throw new SQLException();
            }

        }catch(SQLException e){
            System.out.println("Errore salvando donazione di " + d.getUsername() + " di euro " + d.getImporto() + ". Causa: " + e);
            e.printStackTrace();
        }
    }

    public String getDonazioniCurrentYearJSON(){
        int currYear = LocalDate.now().getYear(); // anno corrente
        return getDonazioniByYearJSON(currYear);
    }

    public String getDonazioniByYearJSON(int year){
        String donazioniJSON = "";

        try(PreparedStatement ps = conn.prepareStatement(getDonazioniByYear)){
            ps.setInt(1, year);

            try(ResultSet rs = ps.executeQuery()){
                // ogni cella di questo array rappresenta le donazioni di quel mese esempio:
                // donazioni[0] contiene le donaz. di gennaio, donazioni[1] contiene le donaz. di febbraio, ecc ecc
                List<Integer> donazioni= new ArrayList<Integer>(12);

                // inizializza a 0 tutte le celle della lista
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

                // Crea oggetto JSON con il seguente formato: contiene solo un array javascript
                // contenente le donazioni mese per mese
                // [n, n, n, ...]

                Gson gson = new Gson();
                // converte List in json array
                donazioniJSON = gson.toJson(donazioni);

            }
        }catch(SQLException e){
            System.out.println("Errore DB: " + e);
            e.printStackTrace();
        }

        return donazioniJSON;
    }

    public void delete(Donazione d){

    }






}
