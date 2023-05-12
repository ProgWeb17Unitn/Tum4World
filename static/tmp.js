activePalette = 0;
activePath = '';
palette = {};
page = {};
menu = {};
quotes = {};
quotes.interval = 0;

page.load = function () {
    menu.status = 'closed';
    page.status = 'landscape';

    // controlla se devo andare in modalità landscape o portrait in base a
    // quanto è larga inizialmente la finestra
    if (window.innerWidth > 1000)
        page.toggleView('landscape');
    else
        page.toggleView('portrait');

    // inizializza le palette per i vari temi del sito
    palette[0] = { // tema scuro
        'primary_l': '#', 'primary_d': '#',
        'secondary_l': '#', 'secondary_d': '#',
        'tertiary': '#'
    };
    palette[1] = { // tema chiaro
        'secondary_l': '#F49E4C', 'secondary_d': '#AB3428',
        'primary_l': '#3B8EA5', 'primary_d': '#2D728F',
        'tertiary': '#F5EE9E'
    };
    palette[2] = { // tema admin
        'primary_l': '#', 'primary_d': '#',
        'secondary_l': '#', 'secondary_d': '#',
        'tertiary': '#'
    };

    // aggiunge funzionalità al pulsante hamburger in modalità portrait
    document.getElementById('hamburger').addEventListener('click', function () {
        // quando viene cliccato, viene aperto il menu se chiuso
        // e viene chiuso se aperto
        if (menu.status === 'closed')
            menu.open();
        else
            menu.close();
    });

    // aggiunge funzionalità al pappagallo in basso a sinistra
    const helper = document.getElementById('helper');
    helper.addEventListener('click', function (){

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
        setTimeout(function (){
            helper.style.marginLeft = '0';
        }, 200)
    });

    // utilizzo lo stile predefinito dal browser
    //if (window.matchMedia && window.matchMedia('(prefers-color-scheme: dark)').matches)
        page.switchStyle(1);
    //else
        //page.switchStyle(2);

}

// controlla se viene aggiornato il tema predefinito del browser
// e cambia il tema di conseguenza
// a meno che non sia in modalità admin
//window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', event => {
    //if (activePalette !== 0)
        //page.switchStyle(event.matches ? 1 : 2);
//});

page.switchStyle = function (n) {

    // scrolla in cima alla pagina per evitare problemi con la trasparenza dell'header
    window.scrollTo(0, 0);

    // in base alla palette scelta, ottiene il path degli asset necessari
    activePalette = n;
    if (n === 0)
        activePath = 'admin';
    else if (n === 1)
        activePath = 'dark';
    else
        activePath = 'light';

    // elementi da modificare
    const icon = document.getElementsByTagName('link')[0];
    const logo = document.getElementById('logo');
    const helper = document.getElementById('helper');
    const background = document.getElementsByTagName('body')[0];
    const download = document.getElementById('download');
    const links = document.getElementsByTagName('a');
    const texts = document.getElementsByClassName('coloredText');
    const quote = document.getElementById('quotes').getElementsByTagName('p')[0];

    // cambia logo, icone ed immagine di sfondo
    icon.setAttribute('href', 'assets/' + activePath + '/icon.svg');
    logo.setAttribute('src', 'assets/' + activePath + '/logo.svg');
    helper.setAttribute('src', 'assets/' + activePath + '/icon.svg');
    background.style.backgroundImage = 'url(assets/' + activePath + '/background.jpg)';

    // cambia il box delle citazioni
    quote.style.backgroundColor = palette[activePalette].tertiary;
    quote.style.color = palette[activePalette].secondary_l;

    // cambia sfondo del footer e dei pulsanti
    document.getElementsByTagName('footer')[0].style.backgroundColor = palette[activePalette].primary_d;
    download.style.backgroundColor = palette[activePalette].secondary_d;

    // cambia il colore di ogni testo nel sito
    for (let i of texts)
        i.style.color = palette[activePalette].tertiary;

    // cambia colore ai pulsanti e link quando il puntatore vi passa sopra
    download.addEventListener('mouseover', function () {
        download.style.backgroundColor = palette[activePalette].secondary_l;
    });
    download.addEventListener('mouseout', function () {
        download.style.backgroundColor = palette[activePalette].secondary_d;
    });
    for (let i of links) {
        i.addEventListener('mouseover', function () {
            i.style.color = palette[activePalette].secondary_l;
        });

        i.addEventListener('mouseout', function () {
            i.style.color = palette[activePalette].tertiary;
        });
    }
}

window.onscroll = function () {
    // chiamata ogni volta che la pagina viene scrollata
    // utilizzata per rendere l'header opaco quando necessario
    const header = document.getElementsByTagName('header')[0];

    // quando il menu è aperto, l'header è sempre opaco
    // quindi ritorna senza fare niente
    if (menu.status === 'open')
        return;

    // utilizza ref come punto di riferimento, ovvero il punto che, se sovrapposto
    // all'header, quest'ultimo dev'essere reso opaco
    let ref = document.getElementById('index').offsetTop - header.clientHeight;
    // per fare ciò, utilizzo anche la posizione attuale nella pagina
    let pos = window.scrollY;

    // scalo ed imposto la trasparenza in base alla posizione dello scroll
    // rispetto a ref
    if (ref > pos) {
        header.style.backgroundColor = 'transparent';
        header.style.height = '6vh';
    } else {
        header.style.backgroundColor = palette[activePalette].primary_d;
        header.style.height = '4vh';
    }
};

window.onresize = function () {
    // chiamata ogni volta che la pagina viene resizata

    // chiudo il menu, per evitare comportamenti strani
    menu.close();

    // controlla se devo andare in modalità landscape o portrait in base a
    // quanto l'utente ha resizato la finestra
    if (window.innerWidth > 1000)
        page.toggleView('landscape');
    else
        page.toggleView('portrait');
}

menu.open = function () {

    // elementi da modificare
    const nav = document.getElementsByTagName('nav')[0];
    const header = document.getElementsByTagName('header')[0];
    const title = document.getElementsByTagName('h1')[0];

    // modifica lo stile degli elementi per far apparire il menu nav sotto all'header
    header.style.padding = '0';
    nav.style.color = 'transparent';
    header.style.height = '15vh';
    setTimeout(function (){
        // aspetto che finisca la transition, poi mostro di nuovo le scritte
        nav.style.color = palette[activePalette].tertiary;
    }, 600);
    header.style.backgroundColor = palette[activePalette].primary_d; // rendo sempre opaco l'header
    header.style.flexDirection = 'column';
    nav.style.display = 'block';
    nav.style.alignSelf = 'flex-start';
    nav.style.fontSize = '150%';
    nav.getElementsByTagName('a')[0].style.marginLeft = '0';
    title.style.marginLeft = '2%';
    title.style.alignSelf = 'flex-start';
    menu.status = 'open';
}

menu.close = function () {

    // elementi da modificare
    const nav = document.getElementsByTagName('nav')[0];
    const header = document.getElementsByTagName('header')[0];
    const title = document.getElementsByTagName('h1')[0];

    // modifica lo stile degli elementi per nascondere il menu nav
    header.style.flexDirection = 'row';
    header.style.padding = '2%';
    header.style.height = '6vh';
    setTimeout(function () {
        title.style.alignSelf = 'center';
    }, 400);
    nav.style.alignSelf = 'center';
    nav.style.display = 'none';
    nav.style.fontSize = '200%';
    nav.getElementsByTagName('a')[0].style.marginLeft = '1%';
    title.style.marginLeft = '0';
    window.scrollTo(window.scrollX, window.scrollY - 1); // per evitare comportamenti strani con la trasparenza
    menu.status = 'closed';
}

page.toggleView = function (mode) {

    // elementi da modificare
    const nav = document.getElementsByTagName('nav')[0];
    const hamburger = document.getElementById('hamburger');

    // abilito la navbar o il pulsante hamburger in base alla modalità
    if (mode === 'landscape') {
        hamburger.style.display = 'none';
        nav.style.display = 'block';
    } else {
        hamburger.style.display = 'block';
        nav.style.display = 'none';
    }
    self.status = mode;
}

quotes.init = function (){

    // inizializza le quotes
    // sarà aggiornata
    quotes[0] = 'questa frase è una frase';
    quotes[1] = 'ho davvero poca fantasia';
    quotes[2] = 'qualcuno potrebbe disegnarmi degli occhi?';
    quotes[3] = 'non fa ridere';
    quotes[4] = 'Crispo è il mio migliore amico';
    quotes.generate();
    quotes.interval = setInterval(quotes.generate, 20000);
}

quotes.generate = function (){

    // sceglie a caso le quotes
    // sarà aggiornata per utilizzare Ajax
    let i = Math.floor(Math.random()*5);
    const box = document.getElementById('quotes').getElementsByTagName('p')[0];

    box.innerText = quotes[i];
    box.style.marginBottom = '2vh';
    setTimeout(function (){
        box.style.marginBottom = '1vh';
    }, 1000);
}

page.toggleStyle = function () {
    // cambia tra il tema corrente e quello rimanente
    if (activePalette === 1)
        page.switchStyle(2);
    else if (activePalette === 2)
        page.switchStyle(1);
}