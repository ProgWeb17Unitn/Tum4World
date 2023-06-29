var value=0;

var attivita1=0;
var attivita2=0;
var attivita3=0;
function handleScroll() {
    if(value===0){
        theme.switch("Simpatizzante");
        value++;
    }
    baseOnscroll();
}
window.addEventListener("scroll", handleScroll);
window.addEventListener("load", findActivities);


function findActivities(){

    // STESSA FUNZIONE DI aderente.js, per maggiori info vedere quella pagina

    let url = "TrovaAttivita";
    let xhttp = new XMLHttpRequest();
    xhttp.open("POST", url, true);
    xhttp.responseType = "json";

    // Callback
    xhttp.onreadystatechange = function () {
        let done = 4, ok = 200;
        if (xhttp.readyState === done && xhttp.status === ok) {

            // la risposta è un array con i codici attività [Att1,Att2,Att3]
            let my_JSON_array = this.response;
            if (my_JSON_array.length > 0) {
                let rettangoliAttiva1 = document.getElementById("Attivita1");
                let rettangoliAttiva2 = document.getElementById("Attivita2");
                let rettangoliAttiva3 = document.getElementById("Attivita3");

                for (let i = 0; i < my_JSON_array.length; i++) {
                    let current_value =JSON.parse(my_JSON_array[i]);
                    //console.log("CUrrent value: " + current_value);
                    if (current_value === "Att1") {
                        // console.log("Isritto ad Attività1");
                        rettangoliAttiva1.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita1=1; // Segno le attivitò a cui l'utente è iscritto per evitare di
                        // doverle ricontrollare in caso volesse cliccare per iscriversi
                    }else if(current_value === "Att2"){
                        //console.log("Isritto ad Attività2");
                        rettangoliAttiva2.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita2=1;
                    }else if(current_value === "Att3") {
                        console.log("Isritto ad Attività3");
                        rettangoliAttiva3.style.boxShadow = "3px 3px 2px rgb(124, 251, 38)";
                        attivita3=1;
                    }
                }
            }
        }else{
            //console.log("Le attività a cui l'utente è iscritto non sono stare recuperate");
        }
    }
    // Sending request
    xhttp.send();
}
