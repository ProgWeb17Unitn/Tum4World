var attivita1 = 0;
var attivita2 = 0;
var attivita3 = 0;

// variabili utilizzate per "Visualizza Dati"
var username = "none";
var tipo = "none";
var nome = "none";
var cognome = "none";
var nascita = "none";
var email = "none";
var telefono = "none";
var password = "none";

var openedDati = 0; // variabile utilizzata per capire se serve aprire o chiudere il div di visualizza dati
var openedIscrizione = 0; // variabile utilizzata per capire se serve aprire o chiudere il div di visualizza dati
// colori visualizzaDati, perchè per evitare di duplicare il codice per aderente e simpatizzante ho realizzato un codice
// "modulare", cosi in simpatizzante basta cambiare le variabili seguenti
var color1 = '#000000';
var color2 = '#DBDFEA';
var color3 = '#ffffff';
var eyecolor = ""; // aderente ha occhio nero mentre simpatizzante bianco
window.addEventListener("load", trovaAttivita);

function handleDonation() {
    /*
        Eseguo due operazioni:
            -Controllo che il valore inserito sia corretto, se è corretto aggiungo la donazione nel DB
            -Se è scorretto "mostro" l'errore
     */

    var valoreInserito=document.getElementById("quantita").value;

    // verifico se non è valido
    if (!isValidInput(valoreInserito)) {
        segnalaErrore(1);
    } else {
        // Form valido, salvo nel db la donazione e "mostro che è stata effettuata"
        saveDonation();
    }
}

function isValidInput(valore) {
    valore = valore.trim(); // rimuovo spazi prima e dopo
    valore = valore.replaceAll(',', '.'); // sostituisco virgole con spazi
    /*
        Accetto valori del tipo "12"; "  12  "; "12.35"; "1200,50"
    */
    return !isNaN(valore);
    // Nan= not a number se la conversione non va a buon fine
    // Quindi true se il numero non è un non-numero
}


function segnalaErrore() {
    var submit = document.getElementById('submit-donation');
    submit.style.backgroundColor = '#A23327FF';
    submit.style.animation = 'donazioneInvalida 0.5s linear';
    /*
        NB: Quando un animazione è applicata ad un elemento è azionata
        una volta, quindi per poterla ri-utilizzare devo eliminarla dopo averla eseguita
     */

    setTimeout(function () {
        submit.style.animation = '';
        submit.style.backgroundColor = '#1D1C1A'; // reset colore precedente
    }, 800);
}

function donazioneEffettuata() {
    var submit = document.getElementById('submit-donation');
    submit.style.backgroundColor = '#6da752';
    setTimeout(function () {
        submit.style.backgroundColor = '#1D1C1A'; // reset colore precedente
    }, 500);
}

function saveDonation() {

    //NB: per ricevere effettivamente il feedback di ricezione impiega qualche istante
    let quantita = document.getElementById("quantita").value;
    let url = "AderenteDonazione?quantita=" + quantita;
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {
            donazioneEffettuata();
        } else {
            if(xhttp.readyState === done && xhttp.status !== ok) {
                // Ho ricevuto una risposta ma con risultato negativo
                console.log("SaveDonation Fail: Donazione non salvata");
                segnalaErrore();
            }
        }
    }
    xhttp.send();
}

function trovaAttivita(){
    check();
    // questa funzione cerca nel database a quali attività l'utente loggato è iscritto
    // Così come quella della donazione ci mette un po' di tempo prima di recuperare il valore
    let url = "TrovaAttivita";
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {

            // la risposta è un array con i codici attività [attivita1,attivita2,attivita3]
            let my_JSON_array = this.response;
            if (my_JSON_array.length > 0) {
                let rettangoliAttiva1 = document.getElementById("Attivita1");
                let rettangoliAttiva2 = document.getElementById("Attivita2");
                let rettangoliAttiva3 = document.getElementById("Attivita3");

                for (let i = 0; i < my_JSON_array.length; i++) {
                    let current_value = JSON.parse(my_JSON_array[i]);
                    if (current_value === "attivita1") {
                        rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita1 = 1; // Segno le attivitò a cui l'utente è iscritto per evitare di
                        // doverle ricontrollare in caso volesse cliccare per iscriversi
                    } else if (current_value === "attivita2") {
                        rettangoliAttiva2.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita2 = 1;
                    } else if (current_value === "attivita3") {
                        rettangoliAttiva3.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita3 = 1;
                    }
                }
            }
        } else {
            if(xhttp.readyState === done && xhttp.status !== ok) {
                // Ho ricevuto una risposta ma con risultato negativo
                console.log("Le attività a cui l'utente è iscritto non sono stare recuperate");

            }
        }
    }
    xhttp.send();
}

function check(){

    // La funzione trovaAttivita setta a 1 le attivita a cui l'utente è iscritto
    // e cambia la sfumatura del rettangolo quando la pagina viene caricata,
    // se poi per qualche motivo si ricarica la pagina questa informazione tornerebbe "momentaneamente" come l'originale
    // finché non recuperata, tramite questa funzione si imposta subito il cambio di stile per le informazioni già presenti
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

function iscriviAttivita(attivita) {
    if (attivita === 1 && attivita1 === 1) {
        // si vuole iscrivere a 1 ma è già iscritto
        giaIscritto(attivita);
        return;
    } else if (attivita === 2 && attivita2 === 1) {
        giaIscritto(attivita);
        return;
    } else if (attivita === 3 && attivita3 === 1) {
        giaIscritto(attivita);
        return
    }
    let url = "IscriviAttivita?attivita=" + attivita;
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    // Callback
    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {
            iscrizioneAvvenuta(attivita);
        } else {
            if(xhttp.readyState === done && xhttp.status !== ok) {
                // Ho ricevuto una risposta ma con risultato negativo
                iscrizioneFail(attivita);
                console.log("Iscrizione Fallita");
            }
        }
    }
    xhttp.send();
}

function iscrizioneAvvenuta(attivita){
    if (attivita === 1) {
        attivita1 = 1; // aggiorno il fatto che sia iscritto
    } else if (attivita === 2) {
        attivita2 = 1;
    } else {
        attivita3 = 1;
    }
    let rettangoliAttiva1 = document.getElementById("Attivita" + attivita);
    rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";

}

function iscrizioneFail(attivita) {

    let rettangoliAttiva1 = document.getElementById("Attivita" + attivita);
    rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(255, 0, 0)";
}

function giaIscritto(attivita) {
    window.alert("Sei gia' iscritto all'attivita " + attivita + "!");
}

function visualizzaDati() {
    if (openedDati === 0) {
        if (openedIscrizione === 1) {
            // se si è cliccato anche il pulsante di cancella iscrizione allora lo chiudo
            const conferma = document.getElementsByClassName("conferma")[0];
            conferma.remove();
            openedIscrizione = 0;
        }
        let url = "VisualizzaDati";
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url, true);
        xhttp.responseType = "json";

        // Callback
        xhttp.onreadystatechange = function () {
            let done = 4, ok = 200;
            if (xhttp.readyState === done && xhttp.status === ok) {
                let my_JSON_array = this.response;
                if (my_JSON_array.length > 0) {

                    username = JSON.parse(my_JSON_array[0]);
                    tipo = JSON.parse(my_JSON_array[1]);
                    nome = JSON.parse(my_JSON_array[2]);
                    cognome = JSON.parse(my_JSON_array[3]);
                    email = JSON.parse(my_JSON_array[4]);
                    nascita = JSON.parse(my_JSON_array[5]);
                    telefono = JSON.parse(my_JSON_array[6]);
                    password = JSON.parse(my_JSON_array[7]);

                    mostra();

                }else{
                    visualizzaDatiFail();
                    console.log("Non sono riuscito a recuperare i dati dell'utente");
                }
            }else{
                if(xhttp.readyState === done && xhttp.status !== ok) {
                    // Ho ricevuto una risposta ma con risultato negativo
                    visualizzaDatiFail();
                    console.log("Non sono riuscito a recuperare i dati dell'utente");
                }
            }
        }
        xhttp.send();
    } else {
        const datidiv = document.getElementsByClassName("datidiv")[0];
        datidiv.remove();
        openedDati = 0;
        var occhio = document.getElementById("eyeicon") //cambio icona con L'occhio chiuso
        occhio.src = './assets/images/S/eyeCLOSED' + eyecolor + '.svg';

    }
}

function visualizzaDatiFail(){
    var bottone=document.getElementById("btn1");
    bottone.style.boxShadow="3px 3px 2px rgb(255, 0, 0)";
}

function mostra() {
    openedDati = 1;
    // dopo aver recuperato i dati grazie a visualizza attività devo mostrarli sulla pagina
    const container = document.getElementsByClassName("flexbox-container")[0];
    const datidiv = document.createElement('div');
    datidiv.classList.add("datidiv"); //assegno un nome a questo div per poterlo rimuovere nella funzione visualizzaDati();
    // Div stile:
    datidiv.style.color = color1;
    datidiv.style.fontSize = 'x-large';
    datidiv.style.textAlign = 'center';
    datidiv.style.display = 'flex';
    datidiv.style.flexDirection = 'column';
    datidiv.style.justifyContent = 'center';
    datidiv.style.alignItems = 'center';
    datidiv.style.margin = '5%';
    datidiv.style.padding = '1%';
    datidiv.style.backgroundColor = color2;
    datidiv.style.border = "2px solid" + color2;
    datidiv.style.boxShadow = "2px 2px 2px" + color3;

    const title = document.createElement('span');
    title.textContent = "Dati Profilo:";
    title.style.color = color1;
    title.style.fontSize = 'xx-large';
    datidiv.appendChild(title); //aggiungo il titolo al div

    const usernamediv = document.createElement('div');
    usernamediv.textContent = "Username: " + username;
    datidiv.appendChild(usernamediv);

    const tipodiv = document.createElement('div');
    tipodiv.textContent = "Tipo: " + tipo;
    datidiv.appendChild(tipodiv);

    const nomediv = document.createElement('div');
    nomediv.textContent = "Nome: " + nome;
    datidiv.appendChild(nomediv);

    const cognomediv = document.createElement('div');
    cognomediv.textContent = "Cognome: " + cognome;
    datidiv.appendChild(cognomediv);

    const nascitadiv = document.createElement('div');
    nascitadiv.textContent = "Nascita: " + nascita;
    datidiv.appendChild(nascitadiv);

    const emaildiv = document.createElement('div');
    emaildiv.textContent = "Email: " + email;
    datidiv.appendChild(emaildiv);

    const telefonodiv = document.createElement('div');
    telefonodiv.textContent = "Telefono: " + telefono;
    datidiv.appendChild(telefonodiv);

    const passworddiv = document.createElement('div');
    passworddiv.textContent = "Password: " + password;
    datidiv.appendChild(passworddiv);

    container.appendChild(datidiv);

    var occhio = document.getElementById("eyeicon") //cambio icona con L'occhio Aperto
    occhio.src = './assets/images/S/eyeOPEN' + eyecolor + '.svg';
}

function cancellaIscrizione() {
    // aggiungo un bottone di conferma, cliccando quello si cancella effettivamente l'iscrizione
    // anche per esso utilizzo la modalita "aperto-chiuso" di visualizza dati
    if (openedIscrizione === 0) {
        if (openedDati === 1) {
            // se il menu dati è aperto lo chiudo
            const dati = document.getElementsByClassName('datidiv')[0];
            dati.remove()
            openedDati = 0;
        }
        const container = document.getElementsByClassName("flexbox-container")[0];
        const conferma = document.createElement('div');
        conferma.classList.add("conferma");
        conferma.textContent = 'Conferma';
        conferma.style.fontSize = 'x-large';
        conferma.style.textAlign = 'center';
        conferma.style.margin = '5%';
        conferma.style.padding = '1%';
        conferma.style.color = 'white';
        conferma.style.backgroundColor = 'red';
        conferma.style.border = '2px solid red';
        conferma.style.borderRadius = '6px';
        conferma.style.cursor = 'pointer';
        conferma.addEventListener('click', cancella);
        container.appendChild(conferma);
        openedIscrizione = 1;
    } else {
        const conferma = document.getElementsByClassName("conferma")[0];
        conferma.remove();
        openedIscrizione = 0;
    }
}

function cancella() {

    let url = "cancellaIscrizione";
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    // Callback
    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {
            // se avviene correttamente viene effettuatu un redirect sulla homepage
            // posso farlo direttamente da js poichè una volta cancellata l'iscrizione invalido la session
            window.location.href = "homepage";
        } else {
            if(xhttp.readyState === done && xhttp.status !== ok) {
                // Ho ricevuto una risposta ma con risultato negativo
                console.log("Eliminazione Profilo Fallita");
                cancellaIscrizioneFail();
            }
        }
    }
    xhttp.send();
}

function cancellaIscrizioneFail(){
    var bottone=document.getElementById("btn2");
    bottone.style.boxShadow="3px 3px 2px rgb(255, 0, 0)";
}