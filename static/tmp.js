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

    if (window.innerWidth > 1000)
        page.toggleView('landscape');
    else
        page.toggleView('portrait');

    palette[0] = {
        'primary_l': '#', 'primary_d': '#',
        'secondary_l': '#', 'secondary_d': '#',
        'tertiary': '#'
    };
    palette[1] = {
        'secondary_l': '#F49E4C', 'secondary_d': '#AB3428',
        'primary_l': '#3B8EA5', 'primary_d': '#2D728F',
        'tertiary': '#F5EE9E'
    };
    palette[2] = {
        'primary_l': '#', 'primary_d': '#',
        'secondary_l': '#', 'secondary_d': '#',
        'tertiary': '#'
    };

    document.getElementById('hamburger').addEventListener('click', function () {
        if (menu.status === 'closed')
            menu.open();
        else
            menu.close();
    });

    const helper = document.getElementById('helper');
    helper.addEventListener('click', function (){
        document.getElementById('quotes').getElementsByTagName('audio')[0].play();
        clearInterval(quotes.interval);
        quotes.generate();
        quotes.interval = setInterval(quotes.generate, 20000);
        helper.style.marginLeft = '-0.5vw';
        setTimeout(function (){
            helper.style.marginLeft = '0';
        }, 200)
    });

    page.switchStyle(1);
}
page.switchStyle = function (n) {
    window.scrollTo(0, 0);

    activePalette = n;
    if (n === 0)
        activePath = 'admin';
    else if (n === 1)
        activePath = 'dark';
    else
        activePath = 'light';

    const icon = document.getElementsByTagName('link')[0];
    const logo = document.getElementById('logo');
    const helper = document.getElementById('helper');
    const background = document.getElementsByTagName('body')[0];
    const download = document.getElementById('download');
    const links = document.getElementsByTagName('a');
    const texts = document.getElementsByClassName('coloredText');
    const quote = document.getElementById('quotes').getElementsByTagName('p')[0];

    icon.setAttribute('href', 'assets/' + activePath + '/icon.svg');
    logo.setAttribute('src', 'assets/' + activePath + '/logo.svg');
    helper.setAttribute('src', 'assets/' + activePath + '/icon.svg');
    background.style.backgroundImage = 'url(assets/' + activePath + '/background.jpg)';

    quote.style.backgroundColor = palette[activePalette].tertiary;
    quote.style.color = palette[activePalette].secondary_l;
    document.getElementsByTagName('footer')[0].style.backgroundColor = palette[activePalette].primary_d;
    download.style.backgroundColor = palette[activePalette].secondary_d;

    download.addEventListener('mouseover', function () {
        download.style.backgroundColor = palette[activePalette].secondary_l;
    });

    download.addEventListener('mouseout', function () {
        download.style.backgroundColor = palette[activePalette].secondary_d;
    });

    for (let i of texts)
        i.style.color = palette[activePalette].tertiary;

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
    const header = document.getElementsByTagName('header')[0];
    if (menu.status === 'open')
        return;

    let ref = document.getElementById('index').offsetTop - header.clientHeight;
    let pos = window.scrollY;

    if (ref > pos) {
        header.style.backgroundColor = 'transparent';
        header.style.height = '6vh';
    } else {
        header.style.backgroundColor = palette[activePalette].primary_d;
        header.style.height = '4vh';
    }
};

window.onresize = function () {
        menu.close();
    if (window.innerWidth > 1000)
        page.toggleView('landscape');
    else
        page.toggleView('portrait');
}

menu.open = function () {
    const nav = document.getElementsByTagName('nav')[0];
    const header = document.getElementsByTagName('header')[0];
    const title = document.getElementsByTagName('h1')[0];
    header.style.height = 'min-content';
    header.style.backgroundColor = palette[activePalette].primary_d;
    header.style.flexDirection = 'column';
    nav.style.display = 'block';
    nav.style.alignSelf = 'flex-start';
    nav.style.fontSize = '100%';
    menu.status = 'open';
}

menu.close = function () {
    const nav = document.getElementsByTagName('nav')[0];
    const header = document.getElementsByTagName('header')[0];
    const title = document.getElementsByTagName('h1')[0];
    header.style.height = '6vh';
    header.style.flexDirection = 'row';
    nav.style.display = 'none';
    nav.style.alignSelf = 'center';
    nav.style.fontSize = '200%';
    window.scrollTo(window.scrollX, window.scrollY - 1);
    menu.status = 'closed';
}

page.toggleView = function (mode) {
    const nav = document.getElementsByTagName('nav')[0];
    const hamburger = document.getElementById('hamburger');

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
    quotes[0] = 'questa frase è una frase';
    quotes[1] = 'ho davvero poca fantasia';
    quotes[2] = 'qualcuno potrebbe disegnarmi degli occhi?';
    quotes[3] = 'non fa ridere';
    quotes[4] = 'Crispo è il mio migliore amico';
    quotes.interval = setInterval(quotes.generate, 20000);
}

quotes.generate = function (){
    let i = Math.floor(Math.random()*5);
    const box = document.getElementById('quotes').getElementsByTagName('p')[0];

    box.innerText = quotes[i];
    box.style.marginBottom = '2vh';
    setTimeout(function (){
        box.style.marginBottom = '1vh';
    }, 1000);
}

page.toggleStyle = function () {
    if (activePalette === 1)
        page.switchStyle(2);
    else if (activePalette === 2)
        page.switchStyle(1);
}