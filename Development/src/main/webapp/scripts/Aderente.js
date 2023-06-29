var value=0;
var attivita1=0;
var attivita2=0;
var attivita3=0;

function handleScroll() {
    if(value===0){
        theme.switch("Aderente");
        value++; // cambio il tema solo una volta
    }
    baseOnscroll();
}
window.addEventListener("scroll", handleScroll);
window.addEventListener("load", trovaAttivita);
function handleDonation() {
    /*
        Esefuo due operazioni:
            -Controllo che il valore inserito sia corretto, se è corretto aggiungo la donazione nel DB
            -Se è scorretto "mostro" l'errore
     */
    var form = document.getElementById('form-donazione');
    var valoreInserito = form.elements.quantita.value;

    // verifico se non è valido
    if (!isValidInput(valoreInserito)) {;
        segnalaErrore();
    } else {
        // Form valido, salvo nel db la donazione e "mostro che è stata effettuata"
        saveDonation();
    }
}

function isValidInput(valore) {
    valore=valore.trim(); // rimuovo spazi prima e dopo
    valore=valore.replaceAll(',','.'); // sostituisco virgole con spazi
    /*
        Accetto valori del tipo "12"; "  12  "; "12.35"; "1200,50"
    */
    return !isNaN(valore);
    // Nan= not a number se la conversione non va a buon fine
    // Quindi true se il numero non è un non-numero
}


function segnalaErrore(){
    //console.log("Inserito valore errato");
    var submit = document.getElementById('submit-donation');
    submit.style.backgroundColor = '#A23327FF';
    submit.style.animation='donazioneInvalida 0.5s linear';
    /*
        NB: Quando un animazione è applicata ad un elemento è azionata
        una volta, quindi per poterla ri-utilizzare devo eliminarla dopo averla eseguita

     */
    setTimeout(function() {
        submit.style.animation = '';
        submit.style.backgroundColor = '#1D1C1A'; // reset colore precedente
    }, 800);
}

function donazioneEffettuata(){
    var submit = document.getElementById('submit-donation');
    submit.style.backgroundColor = '#6da752';
    setTimeout(function() {
        submit.style.backgroundColor = '#1D1C1A'; // reset colore precedente
    }, 500);
}

function saveDonation(){
    // Making request

    //NB: per ricevere effettivamente il feedback di ricezione impiega qualche istante
    let quantita = document.getElementById("quantita").value;
    let url = "AderenteDonazione?quantita=" + quantita;
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    // Callback
    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {
            donazioneEffettuata();
        }
        else {
            segnalaErrore();
        }
    }
    // Sending request
    xhttp.send();
}

function trovaAttivita() {
    check();
    // questa funzione cerca nel database a quali attivita l'utente loggato è iscritto
    // Così come quella della donazione ci mette un po' di tempo prima di recuperare il valore
    let url = "TrovaAttivita";
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    // Callback
    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {

            // la risposta è un array con i codici attività [Att1,Att2,Att3]
            let my_JSON_array = this.response;
            if (my_JSON_array.length > 0) {
                let rettangoliAttiva1 = document.getElementById("Attivita1");
                let rettangoliAttiva2 = document.getElementById("Attivita2");
                let rettangoliAttiva3 = document.getElementById("Attivita3");

                for (let i = 0; i < my_JSON_array.length; i++) {
                    let current_value =JSON.parse(my_JSON_array[i]);
                    //console.log("CUrrent value: " + current_value);
                    if (current_value === "Att1") {
                       // console.log("Isritto ad Attività1");
                        rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita1=1; // Segno le attivitò a cui l'utente è iscritto per evitare di
                        // doverle ricontrollare in caso volesse cliccare per iscriversi
                    }else if(current_value === "Att2"){
                        //console.log("Isritto ad Attività2");
                        rettangoliAttiva2.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita2=1;
                    }else if(current_value === "Att3") {
                        console.log("Isritto ad Attività3");
                        rettangoliAttiva3.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita3=1;
                    }
                }
            }
        }else{
            //console.log("Le attività a cui l'utente è iscritto non sono stare recuperate");
        }
    }
    // Sending request
    xhttp.send();
}

function check(){

    // La funzione trovaAttivita setta a 1 le attivita a cui l'utente è iscritto
    // e cambia la sfumatura del rettangolo  quando la pagina viene caricata,
    // se poi per qualche motivo si ricarica la pagina questa indormazione tornerebbe "momentaneamente" come l'originale
    // finchè non recuperata, tramite questa dfunzione si imposta subito il cambio di stile per le informazioni già presenti
    let rettangoliAttiva1 = document.getElementById("Attivita1");
    let rettangoliAttiva2 = document.getElementById("Attivita2");
    let rettangoliAttiva3 = document.getElementById("Attivita3");
    if (attivita1 === 1) {
        rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
    }else if(attivita2 === 1){
        rettangoliAttiva2.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
    }else if(attivita3 === 1) {
        rettangoliAttiva3.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
    }
}