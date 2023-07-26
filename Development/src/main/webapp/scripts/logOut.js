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
            // se avviene correttamente viene effettuato un redirect sulla homepage
            window.location.href = "homepage";
        } else {
            if(xhttp.readyState === done && xhttp.status !== ok) {
                // Ho ricevuto una risposta ma con risultato negativo
                console.log("Logout Fail");
            }
        }
    }
    xhttp.send();
}

