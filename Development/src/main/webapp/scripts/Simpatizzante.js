/*
    Tutte le funzioni di Simpatizzante sono in aderente.js
    poichè sono uguali, sono qui presenti le variabili della pagina
    in modo che le funzioni in aderente.js facciano riferimento a queste

 */

var attivita1 = 0;
var attivita2 = 0;
var attivita3 = 0;

// variabili utilizzate per "Visualizza Dati"
var username="none";
var tipo="none";
var nome="none";
var cognome="none";
var nascita="none";
var email="none";
var telefono="none";
var password="none";

var openedDati=0; // variabile utilizzata per capire se serve aprire o chiudere il div di visualizza dati
var openedIscrizione=0;
// colori visualizzaDati
var color1='#000000';
var color2='#DBDFEA';
var color3='#ffffff';

window.addEventListener("load", () => {
    trovaAttivita();
    page.load();
    theme.switch("simpatizzante");
});
