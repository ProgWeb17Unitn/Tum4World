package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Utente implements Serializable {

    private String username;
    private String password;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private String email;
    private String telefono;
    private String tipo;

    public Utente() {
        setUsername("Usertest");
        setNome("Mario");
        setCognome("Rossi");
        setPassword("M1234?s");
        setDataNascita(LocalDate.now());
        setTelefono("3495344221");
        setTipo("Simpatizzante");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
