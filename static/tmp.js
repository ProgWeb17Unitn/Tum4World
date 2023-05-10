activePalette = 0;
palette = {};
page = {};
menu = {};

page.load = function () {
    menu.status = 'closed';
    page.status = 'landscape';

    if (window.innerWidth > 960)
        page.toggle('landscape');
    else
        page.toggle('portrait');

    palette[1] = {
        'primary_l': '#7CB518', 'primary_d': '#5C8001',
        'secondary_l': '#F3DE2C', 'secondary_d': '#FBB02D',
        'tertiary': 'white'
    };
    palette[2] = {
        'primary_l': '#38A3A5', 'primary_d': '#22577A',
        'secondary_l': '#80ED99', 'secondary_d': '#57CC99',
        'tertiary': '#C7F9CC'
    };
    palette[3] = {
        'secondary_l': '#F49E4C', 'secondary_d': '#AB3428',
        'primary_l': '#3B8EA5', 'primary_d': '#2D728F',
        'tertiary': '#F5EE9E'
    };
    palette[4] = {
        'primary_l': '#F4D35E', 'primary_d': '#0D3B66',
        'secondary_l': '#F4D35E', 'secondary_d': '#EE964B',
        'tertiary': '#FAF0CA'
    };

    document.getElementById('hamburger').addEventListener('click', function () {
        if (menu.status === 'closed')
            menu.open();
        else
            menu.close();
    });

    switchStyle(1);
}
function switchStyle(n) {
    window.scrollTo(0, 0);
    activePalette = n;
    const icon = document.getElementsByTagName('link')[0];
    const logo = document.getElementById('logo');
    const background = document.getElementsByTagName('body')[0];
    const download = document.getElementById('download');
    const links = document.getElementsByTagName('a');
    const texts = document.getElementsByClassName('coloredText');

    icon.setAttribute('href', 'assets/option' + n + '/logo.svg');
    logo.setAttribute('src', 'assets/option' + n + '/logo.svg');
    background.style.backgroundImage = 'url(assets/option' + n + '/background.jpg)';

    document.getElementsByTagName('footer')[0].style.backgroundColor = palette[n].primary_d;
    download.style.backgroundColor = palette[n].secondary_d;

    download.addEventListener('mouseover', function () {
        download.style.backgroundColor = palette[n].secondary_l;
    });

    download.addEventListener('mouseout', function () {
        download.style.backgroundColor = palette[n].secondary_d;
    });

    for (let i of texts)
        i.style.color = palette[n].tertiary;

    for (let i of links) {
        i.addEventListener('mouseover', function () {
            i.style.color = palette[n].secondary_l;
        });

        i.addEventListener('mouseout', function () {
            i.style.color = palette[n].tertiary;
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
    if (window.innerWidth > 960)
        page.toggle('landscape');
    else
        page.toggle('portrait');
}

menu.open = function () {
    const nav = document.getElementsByTagName('nav')[0];
    const header = document.getElementsByTagName('header')[0];
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
    header.style.height = '6vh';
    header.style.flexDirection = 'row';
    nav.style.display = 'none';
    nav.style.alignSelf = 'center';
    nav.style.fontSize = '200%';
    window.scrollTo(window.scrollX, window.scrollY - 1);
    menu.status = 'closed';
}

page.toggle = function (mode) {
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