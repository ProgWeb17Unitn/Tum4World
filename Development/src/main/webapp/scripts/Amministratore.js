// variabili usate per le visualizzazioni
var username = "none";
var openedDati = 0;   // variabile utilizzata per capire se serve aprire o chiudere il div di visualizza dati
var openedDonazioni = 0;  // stessa cosa ma per il grafico delle donazioni
var openedVisite = 0;     // stessa cosa ma per il grafico delle visite
var my_JSON_array;

var color1 = '#000000';
var color2 = '#DBDFEA';
var color3 = '#ffffff';

/*
    Tutte le visualizzazioni funzionano allo stesso modo:
    Se si clicca sul bottone e non si sta visualizzando nulla, si procede a visualizzare i dati desiderati
    Se invece si sta gia visualizzando altri dati, il click sul bottone provvederà a "chiudere" i dati che si sta gia visualizzando
    Inoltre sono previste delle icone diverse nel caso in cui si stia visualizzando oppure no determinati dati, le quali vengono opportunamente aggiornate
 */

function visualizzaRegistrati() {
    if (openedDati === 0) {
        let url = "visualizzaRegistrati";
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url, true);
        xhttp.responseType = "json";

        // Callback
        xhttp.onreadystatechange = function () {
            let done = 4, ok = 200;
            if (xhttp.readyState === done && xhttp.status === ok) {
                let my_JSON_array = this.response;
                if (my_JSON_array.length > 0) {

                    // provvedo alla creazione di un div contenente i dati desiderati
                    openedDati = 1;
                    const container = document.getElementsByClassName("flexbox-container")[0];
                    const datidiv = document.createElement('div');
                    datidiv.classList.add("datidiv"); //assegno un nome a questo div per poterlo rimuovere nella funzione visualizzaDati();
                    // Div stile:
                    datidiv.style.color = color1;
                    datidiv.style.fontSize = 'x-large';
                    datidiv.style.textAlign = 'center';
                    datidiv.style.display = 'flex';
                    datidiv.style.flexDirection = 'column';
                    datidiv.style.justifyContent = 'center';
                    datidiv.style.alignItems = 'center';
                    datidiv.style.margin = '5%';
                    datidiv.style.padding = '1%';
                    datidiv.style.backgroundColor = color2;
                    datidiv.style.border = "2px solid" + color2;
                    datidiv.style.boxShadow = "2px 2px 2px" + color3;

                    const title = document.createElement('span');
                    title.textContent = "Elenco Utenti Registrati:";
                    title.style.color = color1;
                    title.style.fontSize = 'xx-large';
                    datidiv.appendChild(title); //aggiungo il titolo al div

                    for (let i = 0; i < my_JSON_array.length; i++) {
                        const usernamediv = document.createElement('div');
                        usernamediv.textContent = my_JSON_array[i];
                        datidiv.appendChild(usernamediv);
                    }

                    container.appendChild(datidiv);

                    // cambio l'icona del bottone
                    var user = document.getElementById("user1")
                    user.src = './assets/images/S/user-check-svgrepo-com.svg';

                } else {

                }
            }
        }
        // Sending request
        xhttp.send();
    } else {
        // rimuovo i dati che si stanno gia visualizzando e aggiorno le icone dei bottoni
        const datidiv = document.getElementsByClassName("datidiv")[0];
        datidiv.remove();
        openedDati = 0;
        var user = document.getElementById("user1")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
        user = document.getElementById("user2")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
        user = document.getElementById("user3")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
    }
}

function visualizzaSimpatizzanti() {
    if (openedDati === 0) {
        let url = "visualizzaSimpatizzanti";
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url, true);
        xhttp.responseType = "json";

        // Callback
        xhttp.onreadystatechange = function () {
            let done = 4, ok = 200;
            if (xhttp.readyState === done && xhttp.status === ok) {
                let my_JSON_array = this.response;
                if (my_JSON_array.length > 0) {

                    // provvedo alla creazione di un div contenente i dati desiderati
                    openedDati = 1;
                    const container = document.getElementsByClassName("flexbox-container")[0];
                    const datidiv = document.createElement('div');
                    datidiv.classList.add("datidiv"); //assegno un nome a questo div per poterlo rimuovere nella funzione visualizzaDati();
                    // Div stile:
                    datidiv.style.color = color1;
                    datidiv.style.fontSize = 'x-large';
                    datidiv.style.textAlign = 'center';
                    datidiv.style.display = 'flex';
                    datidiv.style.flexDirection = 'column';
                    datidiv.style.justifyContent = 'center';
                    datidiv.style.alignItems = 'center';
                    datidiv.style.margin = '5%';
                    datidiv.style.padding = '1%';
                    datidiv.style.backgroundColor = color2;
                    datidiv.style.border = "2px solid" + color2;
                    datidiv.style.boxShadow = "2px 2px 2px" + color3;

                    const title = document.createElement('span');
                    title.textContent = "Elenco Simpatizzanti:";
                    title.style.color = color1;
                    title.style.fontSize = 'xx-large';
                    datidiv.appendChild(title); //aggiungo il titolo al div

                    for (let i = 0; i < my_JSON_array.length; i++) {
                        const usernamediv = document.createElement('div');
                        usernamediv.textContent = my_JSON_array[i];
                        datidiv.appendChild(usernamediv);
                    }

                    container.appendChild(datidiv);

                    // cambio l'icona del bottone
                    var user = document.getElementById("user2")
                    user.src = './assets/images/S/user-check-svgrepo-com.svg';

                } else {

                }
            }
        }
        // Sending request
        xhttp.send();
    } else {
        // rimuovo i dati che si stanno gia visualizzando e aggiorno le icone dei bottoni
        const datidiv = document.getElementsByClassName("datidiv")[0];
        datidiv.remove();
        openedDati = 0;
        var user = document.getElementById("user1")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
        user = document.getElementById("user2")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
        user = document.getElementById("user3")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
    }
}

function visualizzaAderenti() {
    if (openedDati === 0) {
        let url = "visualizzaAderenti";
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url, true);
        xhttp.responseType = "json";

        // Callback
        xhttp.onreadystatechange = function () {
            let done = 4, ok = 200;
            if (xhttp.readyState === done && xhttp.status === ok) {
                let my_JSON_array = this.response;
                if (my_JSON_array.length > 0) {

                    // provvedo alla creazione di un div contenente i dati desiderati
                    openedDati = 1;
                    const container = document.getElementsByClassName("flexbox-container")[0];
                    const datidiv = document.createElement('div');
                    datidiv.classList.add("datidiv"); //assegno un nome a questo div per poterlo rimuovere nella funzione visualizzaDati();
                    // Div stile:
                    datidiv.style.color = color1;
                    datidiv.style.fontSize = 'x-large';
                    datidiv.style.textAlign = 'center';
                    datidiv.style.display = 'flex';
                    datidiv.style.flexDirection = 'column';
                    datidiv.style.justifyContent = 'center';
                    datidiv.style.alignItems = 'center';
                    datidiv.style.margin = '5%';
                    datidiv.style.padding = '1%';
                    datidiv.style.backgroundColor = color2;
                    datidiv.style.border = "2px solid" + color2;
                    datidiv.style.boxShadow = "2px 2px 2px" + color3;

                    const title = document.createElement('span');
                    title.textContent = "Elenco Aderenti:";
                    title.style.color = color1;
                    title.style.fontSize = 'xx-large';
                    datidiv.appendChild(title); //aggiungo il titolo al div

                    for (let i = 0; i < my_JSON_array.length; i++) {
                        const usernamediv = document.createElement('div');
                        usernamediv.textContent = my_JSON_array[i];
                        datidiv.appendChild(usernamediv);
                    }

                    container.appendChild(datidiv);

                    // cambio l'icona del bottone
                    var user = document.getElementById("user3")
                    user.src = './assets/images/S/user-check-svgrepo-com.svg';

                } else {

                }
            }
        }
        // Sending request
        xhttp.send();
    } else {
        // rimuovo i dati che si stanno gia visualizzando e aggiorno le icone dei bottoni
        const datidiv = document.getElementsByClassName("datidiv")[0];
        datidiv.remove();
        openedDati = 0;
        var user = document.getElementById("user1")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
        user = document.getElementById("user2")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
        user = document.getElementById("user3")
        user.src = './assets/images/S/user-minus-svgrepo-com.svg';
    }
}

function visualizzaVisite() {
    // visualizzo o non-visualizzo il grafico a seconda della situazione precedente
    if (openedVisite === 0) {
        if (openedDonazioni === 1) {
            // se si sta visualizzando il grafico delle donazioni lo chiudo
            document.getElementById("container").style.display = "none";
            openedDonazioni = 0;
        }
        document.getElementById("container1").style.display = "block";
        openedVisite = 1;
    } else {
        document.getElementById("container1").style.display = "none";
        openedVisite = 0;
    }
}

function resetContatori() {
    // essendo un operazione irreversibile chiedo conferma della decisione di resettare i dati
    let conferma = confirm("Sei veramente sicuro di voler resettare i contatori delle visite di ogni pagina?");
    // in caso positivo procedo alla cancellazione dei dati
    if (conferma) {
        let url = "resetContatori";
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url, true);
        xhttp.responseType = "json";

        // Callback
        xhttp.onreadystatechange = function () {
            let done = 4, ok = 200;
            if (xhttp.readyState === done && xhttp.status === ok) {
                // refresh della pagina necessario per vedere le modifiche apportate
                location.reload();
            } else {

            }
        }
        // Sending request
        xhttp.send();
    }
}

function visualizzaDonazioni() {
    // visualizzo o non-visualizzo il grafico a seconda della situazione precedente
    if (openedDonazioni === 0) {
        if (openedVisite === 1) {
            // se si sta visualizzando il grafico delle visite lo chiudo
            document.getElementById("container1").style.display = "none";
            openedVisite = 0;
        }
        document.getElementById("container").style.display = "block";
        openedDonazioni = 1;
    } else {
        document.getElementById("container").style.display = "none";
        openedDonazioni = 0;
    }
}


