function validateData(){
    let form = document.forms.namedItem("formLogin");

    let errori = ""

    if(form["username"].value === ""){
        errori += "Specificare un username";
    }

    if(form["password"].value === ""){
        errori += "Specificare una password";
    }
    else{
        // TODO serve fare anche qui i controlli della password?
    }

    if(errori === ""){
        makeQuery();
    } else{
        alert(errori);
    }


}



function makeQuery(){
    let form = document.forms.namedItem("formLogin");

    let xhttp = new XMLHttpRequest();
    let url = "elaboraLogin";

    // funzione per codificare i parametri, la stessa di validateSignUp form (dove trovate una spiegazione più dettagliata)
    // il replace serve per codificare gli spazi con '+'
    function codifica(param){
        return encodeURIComponent(param).replace("%20", "+");
    }

    let params =
        "username=" + codifica(form["username"].value) + "&" +
        "password=" + codifica(form["password"].value);

    let query = url + "?" + params;

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4){
            if(this.status == 200){ // login successful
                // bisogna fare qui (lato client) il redirect dalla pagina di conferma perchè facendo una richiesta XHR il browser non la 'intercetta' e processa
                //  come farebbe con una richiesta normale. Il browser quando vede che la richiesta XHR ha ricevuto come risposta un redirect, effettua un'altra
                // richiesta XHR (in background) alla destinazione del redirect. Praticamente è la richiesta XHR che 'subisce' il redirect, non la pagina del browser.
                // Quindi per fare il redirect il client ottiene il link della destinazione dal server, e poi effettua con il redirect con javascript
                // https://github.com/axios/axios/issues/396 qui trovate una spiegazione migliore, anche se riferita a node.js

                let url = this.responseText; // legge l'url a cui fare redirect
                window.location.replace(url); // simula un redirect

                console.log("Login successful")
            }
            else if(this.status == 401){ // 401 Error code: non autorizzato, credenziali errate
                /*
                TODO quando c'è il form bello
                    In caso di errore viene ripresentata la pagina Login con un messaggio addizionale di errore (il
                    messaggio inizia con l’id del vostro gruppo, seguito da : e dal messaggio di errore)
                */
                console.log("Credenziali errate")
            }


        }

        if(this.status == 302){
            console.log("intercept redirect whaaat?")
        }
    }

    // invia richiesta
    xhttp.open("GET", query, true);

    xhttp.send();
}