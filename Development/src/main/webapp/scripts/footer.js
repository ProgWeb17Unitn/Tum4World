// oggetto quotes
// contiene l'intervallo corrente con cui vengono generate le citazioni
// e le principali funzioni riguardo esse
quotes = {};
quotes.interval = 0;

quotes.update = function () {
    // chiamata per ottenere una nuova frase on demand
    // ie quando viene cliccato il pappagallo

    // elimina l'intervallo attuale
    clearInterval(quotes.interval);

    // ottiene una nuova frase
    quotes.generate();

    // fa ripartire l'intervallo
    quotes.interval = setInterval(quotes.generate, 20000);
}

quotes.init = function () {
    // inizializza le seguenti funzionalità delle citazioni:
    // * l'intervallo di 20 secondi con cui generare le frasi
    // * il pulsante per generare frasi on-demand

    quotes.interval = setInterval(quotes.generate, 20000);

    const helper = document.getElementById('helper');
    helper.addEventListener('click', function () {

        // riproduce il suono quando viene cliccato
        document.getElementById('quotes').getElementsByTagName('audio')[0].play();

        // chiama la funzione per ottenere le frasi on demand
        quotes.update();

        // inizia la transizione definita in css
        helper.style.marginLeft = '-0.5vw';

        // attende il termine della transizione,
        // ripristina l'attributo originale
        setTimeout(function () {
            helper.style.marginLeft = '0';
        }, 500)
    });
}

quotes.generate = function () {
    // riempie il box delle citazioni con una frase casuale presente nel DB

    const box = document.getElementById('quotes').getElementsByTagName('p')[0];
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            // setta la visibilità del fumetto con il testo
            box.style.visibility = 'visible';

            // e lo riempie con la frase ottenuta dal database
            box.innerText = this.responseText;

            // attiva un'animazione per far capire all'utente che
            // è 'arrivata' una nuova frase
            box.style.marginBottom = '2vh';
            setTimeout(function () {
                box.style.marginBottom = '1vh';
            }, 1000);
        }
    }

    xhttp.open('GET', 'fraseRandom');
    xhttp.send();
};

