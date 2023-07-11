// oggetto theme
// contiene informazioni sul tema corrente della pagina
// e le funzioni per cambiarlo
theme = {};
theme.backgroundColor = '#2D728F';
theme.active = 'none';

// oggetto menu
// contiene informazioni sullo stato del menu ad hamburger
// e le funzioni per cambiarlo
menu = {};
menu.status = 'closed';

// oggetto page
// contiene informazioni sull'orientamento della pagina
// ed alcune funzioni fondamentali per il funzionamento di ogni pagina
page = {};
page.status = 'landscape';


theme.switch = function (n) {
    // utilizzata per cambiare il tema dell'header e footer, qualora presenti
    // in base al parametro n, che può essere una delle 3 tipologie di utente
    // se n è invalido, setta semplicemente il colore di default

    // scrolla in cima alla pagina per evitare problemi con la trasparenza dell'header
    window.scrollTo(0, 0);

    // sceglie il colore degli oggetti in base ad n
    theme.active = n;
    if (n === 'simpatizzante')
        theme.backgroundColor = '#1c4664';
    else if (n === 'aderente')
        theme.backgroundColor = '#5f6b8d';
    else if (n === 'admin')
        theme.backgroundColor = '#502477';
    else
        theme.backgroundColor = '#2D728F';

    // modifica lo sfondo del footer, controllando che sia presente nella pagina
    const footer = document.getElementsByTagName('footer')[0];
    if (footer) {
        footer.style.backgroundColor = theme.backgroundColor;

        // modifica lo sfondo delle frasi, controllando che sia presente nella pagina la sezione apposita
        const helperBox = document.getElementById('quotes').getElementsByTagName('p')[0];
        if (helperBox)
            helperBox.style.backgroundColor = theme.backgroundColor;
    }
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

menu.toggle = function () {
    // viene aperto il menu se chiuso
    // e viene chiuso se aperto
    if (menu.status === 'closed')
        menu.open();
    else
        menu.close();
}

page.toggle = function (mode) {

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

page.onresize = function () {
    // chiamata ogni volta che la pagina viene resizata

    // chiudo il menu, per evitare comportamenti strani
    menu.close();

    // controlla se devo andare in modalità landscape o portrait in base a
    // quanto l'utente ha resizato la finestra
    if (window.innerWidth > 1000)
        page.toggle('landscape');
    else
        page.toggle('portrait');
}

page.load = function () {
    // eseguita ogni volta che si carica una pagina qualsiasi

    // controlla se devo andare in modalità landscape o portrait in base a
    // quanto è larga inizialmente la finestra
    if (window.innerWidth > 1000)
        page.toggle('landscape');
    else
        page.toggle('portrait');

    // aggiunge funzionalità al pulsante hamburger in modalità portrait
    const hamburger = document.getElementById('hamburger');
    if (hamburger)
        hamburger.addEventListener('click', menu.toggle);

    // aggiunge funzionalità al pulsante per tornare in cima alla pagina, se presente
    const backToTop = document.getElementById('backToTop');
    if (backToTop)
        backToTop.addEventListener('click', () => window.scrollTo(0, 0));
}

page.onscroll = function () {
    // chiamata ogni volta che una pagina qualsiasi viene scrollata

    // elementi da modificare
    const header = document.getElementsByTagName('header')[0];
    const hamburger = document.getElementById('hamburger');
    const backToTop = document.getElementById('backToTop');

    // quando il menu è aperto, l'header è sempre opaco
    // quindi ritorna senza fare niente
    if (menu.status === 'open')
        return;

    // utilizza ref come punto di riferimento, ovvero il punto che, se raggiunto, rende l'header opaco,
    let ref = document.getElementById('index').offsetTop - header.clientHeight;

    // per fare ciò, utilizzo anche la posizione attuale nella pagina
    let pos = window.scrollY;

    // scalo e imposto la trasparenza dell'header in base alla posizione dello scroll
    // rispetto a ref
    if (pos < ref) {
        hamburger.style.top = '16px';
        header.style.backgroundColor = 'transparent';
        header.style.height = '6vh';
    } else {
        hamburger.style.top = '10px';
        header.style.backgroundColor = theme.backgroundColor;
        header.style.height = '4vh';
    }

    if (backToTop) {
        if (pos > ref * 5)
            backToTop.style.bottom = '15%';
        else
            backToTop.style.bottom = '-10%';
    }
}

// new: possiamo definire più handler per i diversi eventi in questo modo
// se avete bisogno di aggiungere handler alle altre pagine, seguite lo stesso format
window.addEventListener('load', page.load);
window.addEventListener('resize', page.onresize);
window.addEventListener('scroll', page.onscroll);
