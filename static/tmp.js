page = {};
menu = {};
quotes = {};
quotes.interval = 0;
palette = {};
palette.active = 0;
palette.path = '';

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
    palette[0] = { // tema admin
        'primary_l': '#', 'primary_d': '#',
        'secondary_l': '#', 'secondary_d': '#',
        'tertiary': '#'
    };
    palette[1] = { // tema scuro
        'secondary_l': '#F49E4C', 'secondary_d': '#AB3428',
        'primary_l': '#3B8EA5', 'primary_d': '#2D728F',
        'tertiary': '#F5EE9E'
    };
    palette[2] = { // tema chiaro
        'primary_l': '#F5EE9E', 'primary_d': '#F49E4C',
        'secondary_l': '#3B8EA5', 'secondary_d': '#F5EE9E',
        'tertiary': '#063042'
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

    // aggiunge funzionalità al pulsante per cambiare tema
    document.getElementById('themeSwitcher').addEventListener('click', page.toggleStyle);


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
//    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: light)').matches)
//        page.switchStyle(2);
//    else
        page.switchStyle(1);
}

// controlla se viene aggiornato il tema predefinito del browser
// e cambia il tema di conseguenza
// a meno che non sia in modalità admin
//window.matchMedia('(prefers-color-scheme: dark)').addEventListener('change', event => {
//if (palette.active !== 0)
//page.switchStyle(event.matches ? 1 : 2);
//});

page.switchStyle = function (n) {

    // scrolla in cima alla pagina per evitare problemi con la trasparenza dell'header
    window.scrollTo(0, 0);

    // in base alla palette scelta, ottiene il path degli asset necessari
    palette.active = n;
    if (n === 0)
        palette.path = 'admin';
    else if (n === 1)
        palette.path = 'dark';
    else
        palette.path = 'light';

    // elementi da modificare
    const icon = document.getElementsByTagName('link')[0];
    const logo = document.getElementById('logo');
    const helper = document.getElementById('helper');
    const background = document.getElementsByTagName('body')[0];
    const download = document.getElementById('download');
    const hamburger = document.getElementById('hamburger');
    const themeSwitcher = document.getElementById('themeSwitcher');
    const links = document.getElementsByTagName('a');
    const texts = document.getElementsByClassName('coloredText');
    const quote = document.getElementById('quotes').getElementsByTagName('p')[0];

    // cambia logo, icone ed immagine di sfondo
    icon.setAttribute('href', `assets/${palette.path}/icon.svg`);
    logo.setAttribute('src', `assets/${palette.path}/logo.svg`);
    helper.setAttribute('src', `assets/${palette.path}/icon.svg`);
    hamburger.setAttribute('src', `assets/${palette.path}/hamburger.svg`);
    themeSwitcher.setAttribute('src', `assets/${palette.path}/themeSwitcher.svg`);
    background.style.backgroundImage = `url(assets/${palette.path}/background.jpg)`;

    // cambia il box delle citazioni
    quote.style.backgroundColor = palette[palette.active].tertiary;
    quote.style.color = palette[palette.active].secondary_d;

    // cambia sfondo del footer e dei pulsanti
    document.getElementsByTagName('footer')[0].style.backgroundColor = palette[palette.active].primary_d;
    download.style.backgroundColor = palette[palette.active].secondary_d;

    // cambia il colore di ogni testo nel sito
    for (let i of texts)
        i.style.color = palette[palette.active].tertiary;

    // cambia colore ai pulsanti e link quando il puntatore vi passa sopra
    download.addEventListener('mouseover', function () {
        download.style.backgroundColor = palette[palette.active].secondary_l;
    });
    download.addEventListener('mouseout', function () {
        download.style.backgroundColor = palette[palette.active].secondary_d;
    });
    for (let i of links) {
        i.addEventListener('mouseover', function () {
            i.style.color = palette[palette.active].secondary_l;
        });

        i.addEventListener('mouseout', function () {
            i.style.color = 'inherit';
        });
    }
}

window.onscroll = function () {
    // chiamata ogni volta che la pagina viene scrollata
    // utilizzata per rendere l'header opaco quando necessario
    const header = document.getElementsByTagName('header')[0];
    const logo = document.getElementById('logo');
    const image = document.getElementById('mainImg');
    const hamburger = document.getElementById('hamburger');

    // quando il menu è aperto, l'header è sempre opaco
    // quindi ritorna senza fare niente
    if (menu.status === 'open')
        return;

    // utilizza ref come punto di riferimento, ovvero il punto che, se raggiunto

    // rende l'header opaco
    let ref1 = document.getElementById('index').offsetTop - header.clientHeight;
    // rende il logo visibile
    let ref2 = logo.offsetTop - logo.clientHeight;
    // rende l'immagine visibile
    let ref3 = image.offsetTop - image.clientHeight;
    // per fare ciò, utilizzo anche la posizione attuale nella pagina
    let pos = window.scrollY;

    // scalo ed imposto la trasparenza dell'header in base alla posizione dello scroll
    // rispetto a ref1
    if (pos < ref1) {
        hamburger.style.top = '16px';
        header.style.backgroundColor = 'transparent';
        header.style.height = '6vh';
    } else {
        hamburger.style.top = '10px';
        header.style.backgroundColor = palette[palette.active].primary_d;
        header.style.height = '4vh';
    }

    // rendo visibile e sposto il logo 10vh più in alto, se pos >=
    // rispetto a ref2
    if (pos < ref2 && page.status !== 'portrait') {
        logo.style.opacity = '0';
        logo.style.marginTop = '20vh';
        logo.style.marginBottom = '10vh';
    } else {
        logo.style.opacity = '100%';
        logo.style.marginTop = '10vh';
        logo.style.marginBottom = '20vh';
    }

    // rendo visibile e sposto il logo 10vh più in alto, se pos >=
    // rispetto a ref3
    if (pos < ref3 && page.status !== 'portrait') {
        image.style.opacity = '0';
        image.style.marginTop = '20vh';
        image.style.marginBottom = '10vh';
    } else {
        image.style.opacity = '100%';
        image.style.marginTop = '10vh';
        image.style.marginBottom = '20vh';
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
    header.style.flexDirection = 'column';
    header.style.padding = '0';
    header.style.height = 'min-content';
    document.getElementById('hamburger').style.top = '16px';

    header.style.backgroundColor = palette[palette.active].primary_d; // rendo sempre opaco l'header

    title.style.alignSelf = 'start';

    setTimeout(function () {
        nav.style.display = 'block';
    }, 400);

    nav.style.alignSelf = 'start';
    title.style.marginLeft = '2%';

    menu.status = 'open';
}

menu.close = function () {

    // elementi da modificare
    const nav = document.getElementsByTagName('nav')[0];
    const header = document.getElementsByTagName('header')[0];
    const title = document.getElementsByTagName('h1')[0];

    header.style.transition = 'none';
    title.style.alignSelf = 'center';
    setTimeout(function () {
        header.style.transition = 'ease-in-out background-color 0.6s, ease-out height 0.4s';
    }, 400);

    // modifica lo stile degli elementi per nascondere il menu nav
    header.style.padding = '2%';
    header.style.height = '6vh';

    // per evitare comportamenti strani con la trasparenza
    // resetta la trasparenza dell'header
    window.scrollTo(window.scrollX, window.scrollY + 1);

    header.style.flexDirection = 'row';
    nav.style.display = 'none';

    nav.style.alignSelf = 'center';
    title.style.marginLeft = '0';

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
    page.status = mode;
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
    if (palette.active === 1)
        page.switchStyle(2);
    else if (palette.active === 2)
        page.switchStyle(1);
}