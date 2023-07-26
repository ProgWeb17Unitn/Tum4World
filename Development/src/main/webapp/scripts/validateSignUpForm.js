function validateData() {
    let form = document.forms.namedItem("formSignUp");

    let errori = "";

    if (form["nome"].value === "") {
        errori += "Specificare un nome\n";
    }

    if (form["cognome"].value === "") {
        errori += "Specificare un cognome\n";
    }

    if (form["dataNascita"].value === "") {
        errori += "Specificare una data di nascita\n";
    } else {
        // sottrae 18 anni alla data di oggi
        let dataMaggiorenne = new Date();
        dataMaggiorenne.setFullYear(dataMaggiorenne.getFullYear() - 18);

        let dataNascita = Date.parse(form["dataNascita"].value);

        let minorenne = (dataNascita > dataMaggiorenne); // è minorenne se dataNascità e almeno 18 anni piu grande

        if (minorenne) {
            errori += "Impossibile creare account per utenti minorenni\n";
        }
    }

    if (form["email"].value === "") {
        errori += "Specificare una e-mail\n";
    } else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(form["email"].value))) {
        errori += "Indirizzo email non valido\n";
    }

    if (form["telefono"].value === "") {
        errori += "Specificare un numero di telefono\n";
    }
    else if(!(/^(\+\d{1,2}\s?)?1?\-?\.?\s?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/.test(form["telefono"].value))) {
        errori += "Numero di telefono non valido\n";
    }

    if (form["tipo"].value === "") {
        errori += "Selezionare un tipo utente: aderente o simpatizzante\n"
    }

    if (form["username"].value === "") {
        errori += "Specificare un username (nome utente)\n"
    }


    if (form["password"].value === "") {
        errori += "Specificare una password\n"
    } else if (form["passwordConferma"].value === "") {
        errori += "Confermare la password\n"
    } else { // i campi password e passwordConferma sono fillati

        // requisiti pasword
        // lunga 8 caratteri, deve contenere la prima lettera dei nomi propri di ciascuno di noi, almeno
        // un carattere numerico, un carattere maiuscolo e un carattere tra $, ! e ?
        let password = form["password"].value
        let nomeMatteo = "mM";
        let nomeRiccardo = "rR";
        let numeri = "0123456789";
        let maiuscole = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        let speciali = "$!?";

        // funzione che controlla se la password soddisfa un requisito, cioè contiene almeno 1 carattere del parametro requisito
        let passContiene = function (requisito) {
            let success = false;
            for (let i = 0; !success && i < requisito.length; i++) {
                // controlla se requisito[i] è contenuto nella password:
                // se è contenuto esce dal ciclo, altrimenti controlla il prossimo char
                let curr = requisito.charAt(i);
                if (password.includes(curr)) {
                    success = true;
                }
            }
            return success;
        }

        if (!passContiene(nomeMatteo)) {
            errori += "La password non contiene la lettera: m / M\n"
        }
        if (!passContiene(nomeRiccardo)) {
            errori += "La password non contiene la lettera: r / R\n"
        }
        if (!passContiene(numeri)) {
            errori += "La password deve contenere almeno un numero\n"
        }
        if (!passContiene(maiuscole)) {
            errori += "La password deve contenere una lettera maiuscola\n"
        }
        if (!passContiene(speciali)) {
            errori += "La password deve contenere un carattere speciale tra !, ?, $\n"
        }
        // controlla che la password sia lunga almeno 8 caratteri
        if (password.length < 8) {
            errori += "La password deve essere lunga almeno 8 caratteri\n"
        }

        // controlla se password e password conferma sono diverse
        if (form["password"].value !== form["passwordConferma"].value) {
            errori += "Le due password non corrispondono\n"
        }

    }

    if (errori === "") {
        makeQuery();
    } else {
        alert(errori);
    }
}

function makeQuery() {
    let form = document.forms.namedItem("formSignUp");

    let xhttp = new XMLHttpRequest();
    let url = "elaboraSignUp";

    // Questa funzione codifica i parametri della POST. Codifica sempre la chiocciola della email, e può servire per
    // codificare uno spazio nel nome/cognome, anche per tutte le lettere accentate e moltissimi caratteri tra cui &, =, ?.
    // Gli spazi vengono codificati con %20, ma nelle richieste con Content-type: application/x-www-form-urlencoded gli spazi
    // dovrebbero essere codificati con dei +, e quindi viene fatto il .replace(...) come spiegato al link
    // https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/encodeURIComponent
    function codifica(param) {
        return encodeURIComponent(param).replace("%20", "+");
    }

    // aggiunge il jsessionid per fare URL rewriting (se necessario)
    let jsessionid = "";
    if(window.location.href.includes("jsessionid")){
        // 'jsessionid=' sono 11 char. Il jsessionid è lungo 32 char, quindi 11 + 32 = 43
        // il ; serve perchè è il separatore utilizzato quando viene fatto URL rewriting
        jsessionid = ";" + window.location.href.split(';')[1].substring(0, 43);

    }

    let params =
        "nome=" + codifica(form["nome"].value) + "&" +
        "cognome=" + codifica(form["cognome"].value) + "&" +
        "dataNascita=" + codifica(form["dataNascita"].value) + "&" +
        "email=" + codifica(form["email"].value) + "&" +
        "telefono=" + codifica(form["telefono"].value) + "&" +
        "tipo=" + codifica(form["tipo"].value) + "&" +
        "username=" + codifica(form["username"].value) + "&" +
        "password=" + codifica(form["password"].value);

    // console.log(url + "?" + params);

    xhttp.open("POST", url + jsessionid, true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xhttp.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                let url = this.responseText; // legge l'url a cui fare redirect
                window.location.replace(url); // simula un redirect
            } else if (this.status == 409) { // 409 = Already Exist cioè esiste già un utente con lo stesso username
                /*
                    Se esiste un utente con lo stesso username, viene
                    ripresentata la pagina Sign-in con un messaggio addizionale di errore (il messaggio inizia con
                    l’id del vostro gruppo, seguito da : e dal messaggio di errore
                */
                document.getElementById('errorText').innerText = this.responseText;
                document.getElementById('error').style.display = "block";

            }

        }
    }

    // invia la POST
    xhttp.send(params);

}

// funzione per resettare tutti i campi del form
function resetForm() {
    let form = document.forms.namedItem("formSignUp");
    form.reset();
}