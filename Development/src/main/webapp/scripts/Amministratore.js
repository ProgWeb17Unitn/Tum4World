//variabili usate per visualizzare utenti
var username="none";
var openedDati=0; // variabile utilizzata per capire se serve aprire o chiudere il div di visualizza dati

var color1='#000000';
var color2='#DBDFEA';
var color3='#ffffff';
var eyecolor ="";


function visualizzaRegistrati(){
    if(openedDati===0) {
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

                    openedDati=1;
                    const container=document.getElementsByClassName("flexbox-container")[0];
                    const datidiv = document.createElement('div');
                    datidiv.classList.add("datidiv"); //assegno un nome a questo div per poterlo rimuovere nella funzione visualizzaDati();
                    // Div stile:
                    datidiv.style.color=color1;
                    datidiv.style.fontSize='x-large';
                    datidiv.style.textAlign='center';
                    datidiv.style.display= 'flex';
                    datidiv.style.flexDirection='column';
                    datidiv.style.justifyContent= 'center';
                    datidiv.style.alignItems= 'center';
                    datidiv.style.margin='5%';
                    datidiv.style.padding='1%';
                    datidiv.style.backgroundColor=color2;
                    datidiv.style.border="2px solid"+color2;
                    datidiv.style.boxShadow="2px 2px 2px"+color3;

                    const title= document.createElement('span');
                    title.textContent="Elenco Utenti Registrati:";
                    title.style.color=color1;
                    title.style.fontSize='xx-large';
                    datidiv.appendChild(title); //aggiungo il titolo al div

                    for (let i = 0; i < my_JSON_array.length; i++){
                        const usernamediv = document.createElement('div');
                        usernamediv.textContent=my_JSON_array[i];
                        datidiv.appendChild(usernamediv);
                    }

                    container.appendChild(datidiv);

                    var occhio = document.getElementById("eyeicon1") //cambio icona con L'occhio Aperto
                    occhio.src='./assets/images/S/eyeOPEN'+eyecolor+'.svg';

                } else {

                }
            }
        }
        // Sending request
        xhttp.send();
    }else{
        const datidiv =document.getElementsByClassName("datidiv")[0];
        datidiv.remove();
        openedDati=0;
        var occhio = document.getElementById("eyeicon1") //cambio icona con L'occhio chiuso
        occhio.src='./assets/images/S/eyeCLOSED'+eyecolor+'.svg';
    }
}

function visualizzaSimpatizzanti(){
    if(openedDati===0) {
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

                    openedDati=1;
                    const container=document.getElementsByClassName("flexbox-container")[0];
                    const datidiv = document.createElement('div');
                    datidiv.classList.add("datidiv"); //assegno un nome a questo div per poterlo rimuovere nella funzione visualizzaDati();
                    // Div stile:
                    datidiv.style.color=color1;
                    datidiv.style.fontSize='x-large';
                    datidiv.style.textAlign='center';
                    datidiv.style.display= 'flex';
                    datidiv.style.flexDirection='column';
                    datidiv.style.justifyContent= 'center';
                    datidiv.style.alignItems= 'center';
                    datidiv.style.margin='5%';
                    datidiv.style.padding='1%';
                    datidiv.style.backgroundColor=color2;
                    datidiv.style.border="2px solid"+color2;
                    datidiv.style.boxShadow="2px 2px 2px"+color3;

                    const title= document.createElement('span');
                    title.textContent="Elenco Simpatizzanti:";
                    title.style.color=color1;
                    title.style.fontSize='xx-large';
                    datidiv.appendChild(title); //aggiungo il titolo al div

                    for (let i = 0; i < my_JSON_array.length; i++){
                        const usernamediv = document.createElement('div');
                        usernamediv.textContent=my_JSON_array[i];
                        datidiv.appendChild(usernamediv);
                    }

                    container.appendChild(datidiv);

                    var occhio = document.getElementById("eyeicon2") //cambio icona con L'occhio Aperto
                    occhio.src='./assets/images/S/eyeOPEN'+eyecolor+'.svg';

                } else {

                }
            }
        }
        // Sending request
        xhttp.send();
    }else{
        const datidiv =document.getElementsByClassName("datidiv")[0];
        datidiv.remove();
        openedDati=0;
        var occhio = document.getElementById("eyeicon2") //cambio icona con L'occhio chiuso
        occhio.src='./assets/images/S/eyeCLOSED'+eyecolor+'.svg';
    }
}

function visualizzaAderenti(){
    if(openedDati===0) {
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

                    openedDati=1;
                    const container=document.getElementsByClassName("flexbox-container")[0];
                    const datidiv = document.createElement('div');
                    datidiv.classList.add("datidiv"); //assegno un nome a questo div per poterlo rimuovere nella funzione visualizzaDati();
                    // Div stile:
                    datidiv.style.color=color1;
                    datidiv.style.fontSize='x-large';
                    datidiv.style.textAlign='center';
                    datidiv.style.display= 'flex';
                    datidiv.style.flexDirection='column';
                    datidiv.style.justifyContent= 'center';
                    datidiv.style.alignItems= 'center';
                    datidiv.style.margin='5%';
                    datidiv.style.padding='1%';
                    datidiv.style.backgroundColor=color2;
                    datidiv.style.border="2px solid"+color2;
                    datidiv.style.boxShadow="2px 2px 2px"+color3;

                    const title= document.createElement('span');
                    title.textContent="Elenco Aderenti:";
                    title.style.color=color1;
                    title.style.fontSize='xx-large';
                    datidiv.appendChild(title); //aggiungo il titolo al div

                    for (let i = 0; i < my_JSON_array.length; i++){
                        const usernamediv = document.createElement('div');
                        usernamediv.textContent=my_JSON_array[i];
                        datidiv.appendChild(usernamediv);
                    }

                    container.appendChild(datidiv);

                    var occhio = document.getElementById("eyeicon3") //cambio icona con L'occhio Aperto
                    occhio.src='./assets/images/S/eyeOPEN'+eyecolor+'.svg';

                } else {

                }
            }
        }
        // Sending request
        xhttp.send();
    }else{
        const datidiv =document.getElementsByClassName("datidiv")[0];
        datidiv.remove();
        openedDati=0;
        var occhio = document.getElementById("eyeicon3") //cambio icona con L'occhio chiuso
        occhio.src='./assets/images/S/eyeCLOSED'+eyecolor+'.svg';
    }
}

function visualizzaVisite(){
    alert("Hai schiacciato!");
}

function resetContatori(){
    alert("Hai schiacciato!");
}

function visualizzaDonazioni(){
    alert("Hai schiacciato!");
}
