var N = 0;

palette = {};
palette[0] = {
    'primary_l': '#7dbf63', 'primary_d': '#97cc82',
    'secondary_l': '#9ce0e4', 'secondary_d': '#83d8dd',
    'tertiary': 'white'
};
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
    'primary_l': '#F49E4C', 'primary_d': '#AB3428',
    'secondary_l': '#3B8EA5', 'secondary_d': '#2D728F',
    'tertiary': '#F5EE9E'
};
palette[4] = {
    'primary_l': '#F4D35E', 'primary_d': '#0D3B66',
    'secondary_l': '#F4D35E', 'secondary_d': '#EE964B',
    'tertiary': '#FAF0CA'
};


function switch_style(n) {
    N = n;
    const style = document.getElementsByTagName('style')[0];
    const icon = document.getElementsByTagName('link')[0];
    const logo = document.getElementById('logo');
    const background = document.getElementsByTagName('body')[0];


    icon.setAttribute('href', 'assets/option' + n + '/logo.svg');
    logo.setAttribute('src', 'assets/option' + n + '/logo.svg');
    background.setAttribute('style', 'background-image: url(assets/option'+n+'/background.jpg);');


    document.getElementsByTagName('footer')[0].setAttribute('style', 'background-color:'+palette[N].primary_d+';');
    document.getElementsByTagName('button')[0].setAttribute('style', 'background-color:'+palette[N].secondary_d+';');

    style.innerText = 'main button:hover{background-color:' + palette[N].primary_l + ';}\n'
        + 'nav a:hover {color:'+palette[n].secondary_l+'}';
}

window.onscroll = function () {
    let header = document.getElementsByTagName('header')[0];

    let ref = document.getElementById('index').offsetTop - header.clientHeight;
    //calcolo come punto di riferimento il punto in cui termina l'header

    let pos = window.scrollY;
    //quanto ho scrollato la pagina per ora

    if (ref > pos)
        header.setAttribute('style', 'background-color:transparent;');
    else
        //ho scrollato fino a che il testo arriva all'header, quindi devo renderlo opaco
        header.setAttribute('style', 'background-color:' + palette[N].primary_d + ';');
};