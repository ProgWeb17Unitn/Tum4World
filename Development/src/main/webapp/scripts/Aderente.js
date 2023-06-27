var value=0;
function handleScroll() {
    if(value===0){
        theme.switch("Aderente");
        value++;
    }
    baseOnscroll();
}
window.addEventListener("scroll", handleScroll);

function handleDonation() {
    /*
        Esefuo due operazioni:
            -Controllo che il valore inserito sia corretto, se è corretto aggiungo la donazione nel DB
            -Se è scorretto "mostro" l'errore
     */
    var form = document.getElementById('form-donazione');
    var valoreInserito = form.elements.quantita.value;

    // verifico se non è valido
    if (!isValidInput(valoreInserito)) {
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
    let quantita = document.getElementById("quantita").value;
    let url = "AderenteDonazione?quantita=" + quantita;
    let xhttp = new XMLHttpRequest();
    xhttp.open("POSR", url, true);
    xhttp.responseType = "json";

    // Callback
    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {
            donazioneEffettuata()
        }
        else {
            segnalaErrore();
        }
    }
    // Sending request
    xhttp.send();
}