package com.example.development.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Donazione implements Serializable {
    private String username;
    private int importo;
    private LocalDate data;

    public Donazione() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getImporto() {
        return importo;
    }

    public void setImporto(int importo) {
        this.importo = importo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
