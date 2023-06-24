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

    makeQuery();

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

    console.log(url + "?" + params);

    xhttp.open("GET", url + "?" + params, true);

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4){
            if(this.status == 200){ // login successful
                // redirect alla pagina privata
            }
            else if(this.status == 401){ // 401 Error code: non autorizzato, credenziali errate
                /*
                TODO quando c'è il form bello
                    In caso di errore viene ripresentata la pagina Login con un messaggio addizionale di errore (il
                    messaggio inizia con l’id del vostro gruppo, seguito da : e dal messaggio di errore)
                */
            }

        }
    }

    xhttp.send();
}