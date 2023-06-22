package com.example.development.model;

import java.io.Serializable;

public class Visite implements Serializable {
    private String pagina;
    private int visite;

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public int getVisite() {
        return visite;
    }

    public void setVisite(int visite) {
        this.visite = visite;
    }
}
