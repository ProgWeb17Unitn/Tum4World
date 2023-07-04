quotes = {};
quotes.interval = 0;
//DEBUG console.log("footer.js called");
// aggiunge funzionalità al pappagallo in basso a sinistra

quotes.onClick = function () {
    const helper = document.getElementById('helper');
    helper.addEventListener('click', function () {

        // riproduce il suono quando viene cliccato
        document.getElementById('quotes').getElementsByTagName('audio')[0].play();

        // elimina l'intervallo per la generazione delle citazioni,
        // genera una nuova citazione on-demand e infine
        // crea nuovamente un intervallo di 20s
        quotes.init();

        // inizia la transizione definita in css
        helper.style.marginLeft = '-0.5vw';

        // attende il termine della prima transizione,
        // ripristina l'attributo originale
        setTimeout(function () {
            helper.style.marginLeft = '0';
        }, 500)
    });
};

quotes.init = function () {
    if (quotes.interval)
        clearInterval(quotes.interval);
    quotes.generate();
    quotes.interval = setInterval(quotes.generate, 20000);
}

quotes.generate = function () {
    const box = document.getElementById('quotes').getElementsByTagName('p')[0];

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            box.style.visibility = 'visible';
            box.innerText = this.responseText;
            box.style.marginBottom = '2vh';
            setTimeout(function () {
                box.style.marginBottom = '1vh';
            }, 1000);
        }
    }

    xhttp.open('GET', 'fraseRandom');
    xhttp.send();
};