quotes = [];
quotes.interval = 0;
//DEBUG console.log("footer.js called");
// aggiunge funzionalità al pappagallo in basso a sinistra

quotes.animation = function() {
    const helper = document.getElementById('helper');
    helper.addEventListener('click', function () {

        // riproduce il suono quando viene cliccato
        document.getElementById('quotes').getElementsByTagName('audio')[0].play();

        // elimina l'intervallo per la generazione delle citazioni,
        // genera una nuova citazione on-demand e infine
        // crea nuovamente un intervallo di 20s
        clearInterval(quotes.interval);
        quotes.generate();
        quotes.interval = setInterval(quotes.generate, 20000);

        // inizia la transizione definita in css
        helper.style.marginLeft = '-0.5vw';

        // attende il termine della prima transizione,
        // ripristina l'attributo originale
        setTimeout(function () {
            helper.style.marginLeft = '0';
        }, 20000)
    });
};

quotes.init = function (){
    //DEBUGconsole.log("Quotes init called");
    // inizializza le quotes
    // sarà aggiornata
    quotes[0] = 'questa frase è una frase';
    quotes[1] = 'ho davvero poca fantasia';
    quotes[2] = 'qualcuno potrebbe disegnarmi degli occhi?';
    quotes[3] = 'non fa ridere';
    quotes[4] = 'Crispo è il mio migliore amico';
    quotes.generate();
    quotes.interval = setInterval(quotes.generate, 20000);
};

quotes.generate = function (){

    // sceglie a caso le quotes
    // sarà aggiornata per utilizzare Ajax
    let i = Math.floor(Math.random()*5);
    const box = document.getElementById('quotes').getElementsByTagName('p')[0];

    box.innerText = quotes[i];
    box.style.marginBottom = '2vh';
    setTimeout(function (){
        box.style.marginBottom = '1vh';
    }, 200);
};
document.addEventListener("DOMContentLoaded", function() {
    quotes.init();
    quotes.animation();
});