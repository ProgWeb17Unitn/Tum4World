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

        // Form valido, permetto l'azione di default
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
}