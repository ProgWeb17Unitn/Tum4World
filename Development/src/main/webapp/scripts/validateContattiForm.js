function validateData() {

    let form = document.forms.namedItem("formContatti");
    let valido = true;
    let stringaRitorno = "";

    if (form["nome"].value === "") {
        stringaRitorno += "Specificare nome e cognome\n";
        valido = false;
    }

    if (form["email"].value === "") {
        stringaRitorno += "Specificare indirizzo email\n";
        valido = false;
    } else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(form["email"].value))) {
        stringaRitorno += "Indirizzo email non valido\n";
        valido = false;
    }

    if (form["motivo"].value === "") {
        stringaRitorno += "Specificare motivazione\n";
        valido = false;
    }

    if (form["dettagli"].value === "") {
        stringaRitorno += "Specificare ulteriori dettagli\n";
        valido = false;
    }

    if (valido) {
        let xhttp = new XMLHttpRequest();
        let url = "EmailSending";

        function codifica(param) {
            return encodeURIComponent(param).replace("%20", "+");
        }

        let params =
            "nome=" + codifica(form["nome"].value) + "&" +
            "email=" + codifica(form["email"].value) + "&" +
            "motivo=" + codifica(form["motivo"].value) + "&" +
            "dettagli=" + codifica(form["dettagli"].value);

        xhttp.open("POST", url, true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

        xhttp.onreadystatechange = function () {
            if (this.readyState == 4) {
                if (this.status == 200) {
                    let url = this.responseText; // legge l'url a cui fare redirect
                    window.location.replace(url); // simula un redirect
                }
            }
        }

        // invia POST
        xhttp.send(params);
    } else {
        alert(stringaRitorno);
    }

}


function resetData() {
    document.getElementById("formContatti").reset();
}