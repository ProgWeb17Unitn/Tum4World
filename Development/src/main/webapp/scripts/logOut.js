window.addEventListener('load', () => setTimeout(changecolor, 200));

function changecolor() {
    // cambia il colore del box di conferma in base al tema corrente

    const rettangolo = document.getElementsByClassName("logOutRectangle")[0];
    rettangolo.style.visibility = 'visible';

    // scelgo la sfumatura in base al valore del tema
    let sfumatura = theme.backgroundColor;
    if (theme.active === 'simpatizzante')
        sfumatura = '#2D7D90';
    else if (theme.active === 'aderente')
        sfumatura = '#8391bb';
    else if (theme.active === 'admin')
        sfumatura = '#8844c9';

    rettangolo.style.background = `linear-gradient(to bottom right, ${sfumatura}, ${theme.backgroundColor})`;
}

function esci() {

    let url = "logOutAction";
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    // Callback
    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {
            // se avviene correttamente viene effettuatu un redirect sulla homepage
            // posso farlo direttamente da js poichè una volta cancellata l'iscrizione invalido la session
            window.location.href = "homepage";
        } else {
            console.log("Logout Fail");
        }
    }
    // Sending request
    xhttp.send();
}

