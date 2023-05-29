function validateData(){

    let form = document.forms.namedItem("formContatti");
    let valido = true;
    let stringaRitorno = "";

    if(form["nome"].value === ""){
        stringaRitorno += "Specificare nome e cognome\n";
        valido = false;
    }

    if(form["email"].value === ""){
        stringaRitorno += "Specificare indirizzo email\n";
        valido = false;
    }else if (!(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(form["email"].value))){
        stringaRitorno += "Indirizzo email non valido\n";
        valido = false;
    }

    if(form["motivo"].value === ""){
        stringaRitorno += "Specificare motivazione\n";
        valido = false;
    }

    if(form["dettagli"].value === ""){
        stringaRitorno += "Specificare ulteriori dettagli\n";
        valido = false;
    }

    if(valido){
        //Invio email con protocollo SMTP
        //L'oggetto della mail è il motivo ed il contenuto sono i dettagli
        Email.send({
            Host: "smtp.gmail.com",
            Username : "tum4world@nessunonoluogonoesiste.com",
            Password : "12345678",  //Password della mail tum4world
            To : 'form[\"email\"].value',
            From : "tum4world@nessunonoluogonoesiste.com",
            Subject : "form[\"motivo\"].value",
            Body : "form[\"dettagli\"].value",
        });

        //Reindirizzamento a pagina Invio Confermato
        window.location.replace("./invioConfermato.jsp");
    }else{
        alert(stringaRitorno);
    }

}


function resetData(){
    document.getElementById("formContatti").reset();
}