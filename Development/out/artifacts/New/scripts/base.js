theme = {};
theme.backgroundColor = '#2D728F';
menu = {};
menu.status = 'closed';
page = {};

theme.switch = function (n) {
    // scrolla in cima alla pagina per evitare problemi con la trasparenza dell'header
    window.scrollTo(0, 0);

    // in base alla palette scelta, ottiene il path degli asset necessari
    theme.active = n;
    if (n === 'light')
        theme.backgroundColor = '#F49E4C';
    else if (n === 'Simpatizzante')
        theme.backgroundColor = '#5f6b8d';
    else if (n === 'Aderente')
        theme.backgroundColor = '#4da2a2';
    else if (n === 'dark')
        theme.backgroundColor = '#2D728F';
    else
        theme.backgroundColor = '#2D728F'; //todo admin colors

    // elementi da modificare in base al tema
    // todo aggiungere helper, textbox dell'helper e scritte sia in header che footer
    const footer = document.getElementsByTagName('footer')[0];
    const hamburger = document.getElementById('hamburger');

    // cambia logo, icone ed immagine di sfondo
    if (hamburger)
        hamburger.setAttribute('src', `assets/themes/${theme.active}/hamburger.svg`);
    if (footer)
        footer.style.backgroundColor = theme.backgroundColor;
}


page.load = function () {
    page.status = 'landscape';

    // controlla se devo andare in modalità landscape o portrait in base a
    // quanto è larga inizialmente la finestra
    if (window.innerWidth > 1000)
        page.toggleView('landscape');
    else
        page.toggleView('portrait');

    // aggiunge funzionalità al pulsante hamburger in modalità portrait
    const hamburger = document.getElementById('hamburger');
    if (hamburger) {
        console.log('hamburger found')
        hamburger.addEventListener('click', function () {
            // quando viene cliccato, viene aperto il menu se chiuso
            // e viene chiuso se aperto
            if (menu.status === 'closed')
                menu.open();
            else
                menu.close();
        });
    }

    // utilizzo lo stile predefinito dal browser
//    if (window.matchMedia && window.matchMedia('(prefers-color-scheme: light)').matches)
//        theme.switch(2);
//    else
    theme.switch('dark');
}

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

    header.style.backgroundColor = theme.backgroundColor; // rendo sempre opaco l'header

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

baseOnscroll = function () {
    const header = document.getElementsByTagName('header')[0];
    const hamburger = document.getElementById('hamburger');

    // quando il menu è aperto, l'header è sempre opaco
    // quindi ritorna senza fare niente
    if (menu.status === 'open')
        return;

    // utilizza ref come punto di riferimento, ovvero il punto che, se raggiunto, rende l'header opaco,
    let ref = document.getElementById('index').offsetTop - header.clientHeight;

    // per fare ciò, utilizzo anche la posizione attuale nella pagina
    let pos = window.scrollY;

    // scalo ed imposto la  trasparenza dell'header in base alla posizione dello scroll
    // rispetto a ref1
    if (pos < ref) {
        hamburger.style.top = '16px';
        header.style.backgroundColor = 'transparent';
        header.style.height = '6vh';
    } else {
        hamburger.style.top = '10px';
        header.style.backgroundColor = theme.backgroundColor;
        header.style.height = '4vh';
    }
}