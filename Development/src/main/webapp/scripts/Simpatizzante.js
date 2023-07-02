var attivita1 = 0;
var attivita2 = 0;
var attivita3 = 0;

window.addEventListener("load", () => {
    trovaAttivita();
    page.load();
    theme.switch("simpatizzante");
});

function trovaAttivita() {


    // STESSA FUNZIONE DI aderente.js, per maggiori info vedere quella pagina
    check();
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
                    let current_value = JSON.parse(my_JSON_array[i]);
                    //console.log("CUrrent value: " + current_value);
                    if (current_value === "Att1") {
                        // console.log("Isritto ad Attività1");
                        rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita1 = 1; // Segno le attivitò a cui l'utente è iscritto per evitare di
                        // doverle ricontrollare in caso volesse cliccare per iscriversi
                    } else if (current_value === "Att2") {
                        console.log("Isritto ad Attività2");
                        rettangoliAttiva2.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita2 = 1;
                    } else if (current_value === "Att3") {
                        console.log("Isritto ad Attività3");
                        rettangoliAttiva3.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita3 = 1;
                    }
                }
            }
        } else {
            //console.log("Le attività a cui l'utente è iscritto non sono stare recuperate");
        }
    }
    // Sending request
    xhttp.send();
}


function check() {

    // La funzione trovaAttivita setta a 1 le attivita a cui l'utente è iscritto
    // e cambia la sfumatura del rettangolo  quando la pagina viene caricata,
    // se poi per qualche motivo si ricarica la pagina questa indormazione tornerebbe "momentaneamente" come l'originale
    // finchè non recuperata, tramite questa dfunzione si imposta subito il cambio di stile per le informazioni già presenti
    let rettangoliAttiva1 = document.getElementById("Attivita1");
    let rettangoliAttiva2 = document.getElementById("Attivita2");
    let rettangoliAttiva3 = document.getElementById("Attivita3");
    if (attivita1 === 1) {
        rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
    } else if (attivita2 === 1) {
        rettangoliAttiva2.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
    } else if (attivita3 === 1) {
        rettangoliAttiva3.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
    }
}


function iscriviAttivita(attivita){
    if(attivita === 1 && attivita1 === 1) {
        // si vuole iscrivere a 1 ma è già iscritto
        giaIscritto(attivita);
        return;
    }
    else if(attivita === 2 && attivita2 === 1){
        giaIscritto(attivita);
        return;
    }
    else if(attivita === 3 && attivita3 === 1){
        giaIscritto(attivita);
        return
    }
    let url = "IscriviAttivita?attivita="+attivita;
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    // Callback
    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {
            iscrizioneAvvenuta(attivita);
        } else {
            iscrizioneFail(attivita);
            //console.log("Iscrizione Fallita");
        }
    }
    // Sending request
    xhttp.send();

}

function iscrizioneAvvenuta(attivita){
    if(attivita===1){
        attivita1=1; // aggiorno il fatto che sia  iscritto
    }
    else if(attivita===2){
        attivita2=1;
    }
    else{
        attivita3=1;
    }
    let rettangoliAttiva1 = document.getElementById("Attivita"+attivita);
    rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)"

}

function iscrizioneFail(attivita){
    function iscrizioneAvvenuta(attivita){
        if(attivita===1){
            attivita1=1; // aggiorno il fatto che sia iscritto
        }
        else if(attivita===2){
            attivita2=1;
        }
        else{
            attivita3=1;
        }
        let rettangoliAttiva1 = document.getElementById("Attivita"+attivita);
        rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(255, 0, 0)"
    }
}

function giaIscritto(attivita) {
    window.alert("Sei gia' iscritto all'attivita" + attivita + "!");
}
