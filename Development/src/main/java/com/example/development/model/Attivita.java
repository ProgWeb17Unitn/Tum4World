package com.example.development.model;

import java.io.Serializable;

public class Attivita implements Serializable {
    private String codice;
    private String nome;

    public Attivita(){
        setCodice("none");
        setNome("none");
    }
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
