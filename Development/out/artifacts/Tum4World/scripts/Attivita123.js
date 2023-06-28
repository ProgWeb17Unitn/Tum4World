function handleScroll() {
    baseOnscroll();
}

window.addEventListener("scroll", handleScroll); // Funzione solita di scroll implementata in base JS

function scrollGalleriaLoader() {
    const galleria = document.getElementsByClassName('galleria')[0];
    const imgelement = galleria.getElementsByClassName('img-element');
    const elementi = imgelement.length;
    const imgWidth = imgelement[0].offsetWidth;


    let scrollPosizione = 0;
    let avanti = true;
    let elementi_passati = 1;

    /*
        Il funzionamento dello scroll è il seguente: ho il numero di elementi della galleria (chiamato appunto elementi)
        ho una variabile scrollPosizione che mi cambia la posizione dello scroll aumentandolo della dimensione dell'immagine
        o diminuendolo della dimensione dell'immagine
        vado avanti (aumentando la posizione dello scroll) segnando "quante" immagini ho scrollato
        ad un certo punto arrivo all'ultima immagine scrollata, da lì in poi torno indietro diminuendo la posizione dello scroll
     */
    function scrollGalleria() {

        if (avanti) {
            if (elementi_passati <= elementi - 1) {
                scrollPosizione += imgWidth;
                elementi_passati += 1;
            } else {
                avanti = false;
                scrollPosizione -= imgWidth;
                elementi_passati -= 1;
            }

        } else {
            if (elementi_passati > 0) {
                scrollPosizione -= imgWidth;
                elementi_passati -= 1;

            } else {
                scrollPosizione += imgWidth;
                elementi_passati += 1;
                avanti = true;
            }
        }


        // applico la nuova posizione
        galleria.scrollTo({
            left: scrollPosizione,
            behavior: 'smooth'
        });
    }

    setInterval(scrollGalleria, 6000);

}

window.addEventListener("load", scrollGalleriaLoader);
// Devo chiamare la funzione quando la pagina è caricata altrimenti non "trovo" gli elementi
