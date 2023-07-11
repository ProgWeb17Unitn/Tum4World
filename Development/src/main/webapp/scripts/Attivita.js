/*
    Cambio il modo di visualizzare la pagina se è già in modalita portrait o se lo diventa resizando
*/

window.addEventListener('resize', checkflex);

if(page.status === 'portrait' ){
    checkflex();
}

var changed=0
var elementiAttivita;
var foto;
var rettangoli;
function checkflex() {
    if (page.status === 'portrait') {

        // se la pagina viene ridimensionata fino a passare in modalità portrait gli elementi diventano uno sotto l'altro
        // invece di essere affianco

        changed=1;
        console.log("Switched to portrait");

        elementiAttivita = document.getElementsByClassName("attivita");
        foto = document.getElementsByClassName("fotoAttivita");
        rettangoli= document.getElementsByClassName("Rectangle");

        var inverti=0;
        /*
         Gli elementi quando il flex è in modalità 'row' sono sfalsati (rettangolo, foto, foto, rettangolo),
         per questo motivo se fosse usato esclusivamente column quando si passa a portrait otterremmo lo stesso ordine in
         verticale ma in questo caso sarebbe meglio avere rettangolo foto, rettangolo, foto. Per cui, inverto gli elementi
         del flex centrale (che è l'unico "sfalsato")
        */

        for (var i = 0; i < elementiAttivita.length; i++) {
            var elementA = elementiAttivita[i];
            elementA.style.flexDirection = 'column';
            if(inverti===1){
                elementA.style.flexDirection='column-reverse'
            }
            else{
                elementA.style.flexDirection = 'column';
            }
            inverti++;
        }
        inverti=0;

        for (var x = 0; x < foto.length; x++) {
            var elementF = foto[x];
            elementF.style.minWidth = '100%';
            elementF.style.maxWidth = '100%';
        }

        for (var j = 0; j < rettangoli.length ; j++) {
            var elementR=rettangoli[j];
            elementR.style.width='90%';

        }
    }
    else{

        // se sono stato in modalità portrait e torno in modalità landscape ripristino
        if(changed===1){

            elementiAttivita = document.getElementsByClassName("attivita");
            foto = document.getElementsByClassName("fotoAttivita");
            rettangoli= document.getElementsByClassName("Rectangle");

            for (i = 0; i < elementiAttivita.length; i++) {
                var elementA2 = elementiAttivita[i];
                elementA2.style.flexDirection = 'row';
            }
            for (x = 0; x < foto.length; x++) {
                var elementF2 = foto[x];
                elementF2.style.minWidth = '50%';
                elementF2.style.maxWidth = '50%';
            }

            for (j = 0; j < rettangoli.length ; j++) {
                var elementR2 = rettangoli[j];
                elementR2.style.width = '40%';
                changed = 0;
            }
        }
    }
}