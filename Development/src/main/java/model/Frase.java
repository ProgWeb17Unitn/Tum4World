package model;

import java.io.Serializable;

public class Frase implements Serializable {
    private String frase;

    public Frase(){
        setFrase("Lascia il mondo migliore di come lo hai trovato");
    }
    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }
}
