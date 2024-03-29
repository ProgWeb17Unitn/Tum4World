function validateData() {
    let form = document.forms.namedItem("formLogin");

    let errori = "";

    /* todo: sostituire con un altro metodo per notificare l'utente che deve compiere una scelta sui cookies
        possibilità:
            - quando l'utente clicca login, reindirizzare su un'altra pagina quando non ha compiuto la scelta
            - quando l'utente clicca login, includere in login.jsp un cookiebanner più grosso che copra il login finché non si compie una scelta
            - attuale: fallimento login e mostra un messaggio di errore se non si è compiuta la scelta (un po' brutto)
*/
    const cookiesCheck = document.getElementsByClassName('CookieBanner')[0];
    if(cookiesCheck){
        // mostra un errore chiedendo all'utente di effettuare una scelta
        document.getElementById('errorText').innerText = "Login fallito: bisogna prima effettuare una scelta per i cookie";
        document.getElementById('error').style.display = "block";
        return;
    }


    if (form["username"].value === "") {
        errori += "Specificare un username\n";
    }

    if (form["password"].value === "") {
        errori += "Specificare una password\n";
    } else {
        // TODO serve fare anche qui i controlli della password?
    }

    if (errori === "") {
        makeQuery();
    } else {
        alert(errori);
    }


}


function makeQuery() {
    let form = document.forms.namedItem("formLogin");

    let xhttp = new XMLHttpRequest();
    xhttp.withCredentials = true;
    let url = "elaboraLogin";

    // funzione per codificare i parametri, la stessa di validateSignUp form (dove trovate una spiegazione più dettagliata)
    // il replace serve per codificare gli spazi con '+'
    function codifica(param) {
        return encodeURIComponent(param).replace("%20", "+");
    }

    let params =
        "username=" + codifica(form["username"].value) + "&" +
        "password=" + codifica(form["password"].value);

    // se i cookie sono disattivati, aggiunge manualmente il jsessionid alla richiesta XHR
    let jsessionid = "";
    if(window.location.href.includes("jsessionid")){
        // 'jsessionid=' sono 11 char. Il jsessionid è lungo 32 char, quindi 11 + 32 = 43
        // il ; serve perchè è il separatore utilizzato quando viene fatto URL rewriting
        jsessionid = ";" + window.location.href.split(';')[1].substring(0, 43);
    }
    let query = url + jsessionid + "?" + params;

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) { // login successful
                // bisogna fare qui (lato client) il redirect dalla pagina di conferma perchè facendo una richiesta XHR il browser non la 'intercetta' e processa
                //  come farebbe con una richiesta normale. Il browser quando vede che la richiesta XHR ha ricevuto come risposta un redirect, effettua un'altra
                // richiesta XHR (in background) alla destinazione del redirect. Praticamente è la richiesta XHR che 'subisce' il redirect, non la pagina del browser.
                // Quindi per fare il redirect il client ottiene il link della destinazione dal server, e poi effettua con il redirect con javascript
                // https://github.com/axios/axios/issues/396 qui trovate una spiegazione migliore, anche se riferita a node.js

                let url = this.responseText; // legge l'url a cui fare redirect
                console.log("URL redirect: " + url);
                window.location.replace(url); // simula un redirect
                console.log("Login successful")

            } else if (this.status == 401) { // 401 Error code: non autorizzato, credenziali errate
                /*
                    In caso di errore viene ripresentata la pagina Login con un messaggio addizionale di errore (il
                    messaggio inizia con l’id del vostro gruppo, seguito da : e dal messaggio di errore)
                */
                document.getElementById('errorText').innerText = this.responseText;
                document.getElementById('error').style.display = "block";
            }

        }

    }

    // invia richiesta
    xhttp.open("GET", query, true);

    xhttp.send();
}