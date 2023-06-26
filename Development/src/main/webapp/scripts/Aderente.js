function handleScroll() {
    baseOnscroll();
}
window.addEventListener("scroll", handleScroll);

function handleDonation() {
    /*
        Vi è una "doppia" gestione della donazione, questo JS si occupa  di verificare che il valore inserito
        sia valido, se è valido allora l'action specificata nel form (ovvero nel JSP) utilizzerà una servlet
        per inserire il valore tra le donazioni dell'utente.
        Se il valore inserito non è valido allora viene bloccato il "passaggio" alla servlet e mostrato un errore
        nella pagina
     */
    var form = document.getElementById('form-donazione');

    form.addEventListener('submit', function (event) {
        var valoreInserito = form.elements.quantita.value;

        // verifico se non è valido
        if (!isValidInput(valoreInserito)) {
            event.preventDefault(); // Non passo alla servlet
            segnalaErrore();
        }
        else{
            // Form valido, permetto l'azione di default e "mostro che è stata effettuata"
            donazioneEffettuata();
        }
    });

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