package com.example.development.model;

public class Messaggio {
    private String nomeCognome;
    private String email;
    private String motivo;
    private String testo;

    public  Messaggio(){
        setNomeCognome("Alfredo Rossi");
        setEmail("test@tum4world.it");
        setMotivo("Informazioni");
        setTesto("Vorrei avere informazioni in merito a..");
    }
    public String getNomeCognome() {
        return nomeCognome;
    }

    public void setNomeCognome(String nomeCognome) {
        this.nomeCognome = nomeCognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
