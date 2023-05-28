function validateData(){
    let form = document.forms.namedItem("formSignUp");

    let valido = true;

    if(form["nome"].value === ""){
        alert("nome vuoto");
        valido = false;
    }

    if(form["cognome"].value === ""){
        alert("cognome vuoto");
        valido = false;
    }

    if(form["dataNascita"].value === ""){
        alert("data di nascita vuoto");
        valido = false;
    }
    else{
        let dataOggi = new Date();
        let dataNascita = Date.parse(form["dataNascita"].value);
        let msAnno = 1000 * 60 * 60 * 24 * 365.24; // ms in un anno (un anno = 365.24 giorni per calcolare anni bisestili)
        let anni = Math.floor(dataOggi - dataNascita) / msAnno;

        if(anni < 18){
            alert("minorenne: " + anni);
        }
    }

    if(form["email"].value === ""){
        alert("email vuoto");
        valido = false;
    }

    if(form["telefono"].value === ""){
        alert("telefono vuoto");
        valido = false;
    }

    if(form["tipo"].value === ""){
        alert("tipo vuoto");
        valido = false;
    }

    if(form["username"].value === ""){
        alert("username vuoto");
        valido = false;
    }

    // da sistemare, con tutti questi nested if non si capisce molto il flow del controllo password
    if(form["password"].value === ""){
        alert("password vuoto");
        valido = false;
    }
    else if(form["passwordConferma"].value === ""){
        alert("conferma password vuoto");
        valido = false;
    }
    else{ // password e passwordConferma sono fillati

        if(form["password"].value === form["passwordConferma"].value){
            // controllo requisiti password
            // array di caratteri che la password deve contenere
            let requisiti = ["mM", "rR", "0123456789", "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "$!?"];

            let password = Array.from(form["password"].value) // converte la password in char array

            // questo si potrebbe fare meglio e dare un errrore specifico per ogni requisito non rispettato
            // per ogni requisito
            let valida = requisiti.every(requisito => {
                // almeno un elemento del requisito
                return Array.from(requisito).some(elemento => {
                    // deve essere contenuto nella password
                    return password.includes(elemento);
                })
            });

            if(!valida){
                alert("password non rispetta i requisiti");
                valido = false;
            }

            if(form["password"].value.length < 8){
                alert("password deve essere lunga almeno 8 caretteri");
            }


        }
        else{ // password e password conferma sono diverse
            alert("password conferma diversa da password inserita");
            valido = false;
        }

        if(valido){
            makeQuery();
        }
    }
}

function makeQuery(){
    let form = document.forms.namedItem("formSignUp");

    let xhttp = new XMLHttpRequest();
    let url = "elaboraSignUp";
    let params =
        "nome=" + form["nome"].value + "&" +
        "cognome=" + form["cognome"].value + "&" +
        "dataNascita=" + form["dataNascita"].value + "&" +
        "email=" + form["email"].value + "&" +
        "telefono=" + form["telefono"].value + "&" +
        "tipo=" + form["tipo"].value + "&" +
        "username=" + form["username"].value + "&" +
        "password=" + form["password"].value;

    console.log(url+"?"+params)


    xhttp.open("GET", url + "?" + params, true);

    xhttp.onreadystatechange = function(){
        if(this.readyState == 4){
            if(this.status == 200){

            }
            else if(this.status == 409){
                // gestisci errori
                alert("utente esiste gia");
            }

        }
    }

    xhttp.send();
}