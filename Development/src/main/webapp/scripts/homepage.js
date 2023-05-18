window.onscroll = function () {
    // chiamata ogni volta che la pagina viene scrollata
    // utilizzata per rendere l'header opaco quando necessario
    const logo = document.getElementById('logo');
    const image = document.getElementById('mainImg');

    // rende il logo visibile,
    let ref1 = logo.offsetTop - logo.clientHeight;
    // rende l'immagine visibile
    let ref2 = image.offsetTop - image.clientHeight;
    // per fare ciò, utilizzo anche la posizione attuale nella pagina
    let pos = window.scrollY;

    // rendo visibile e sposto il logo 10vh più in alto, se pos >=
    // rispetto a ref2
    if (pos < ref1 && window.innerWidth > 1000) {
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
    if (pos < ref2 && window.innerWidth > 1000) {
        image.style.opacity = '0';
        image.style.marginTop = '20vh';
        image.style.marginBottom = '10vh';
    } else {
        image.style.opacity = '100%';
        image.style.marginTop = '10vh';
        image.style.marginBottom = '20vh';
    }
};