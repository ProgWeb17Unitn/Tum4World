package model;

import java.io.Serializable;

public class Iscrizione implements Serializable {
    private String username;
    private String codiceAttivita;

    public Iscrizione(){
        setUsername("utente0");
        setCodiceAttivita("Att1");
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCodiceAttivita() {
        return codiceAttivita;
    }

    public void setCodiceAttivita(String codiceAttivita) {
        this.codiceAttivita = codiceAttivita;
    }
}
