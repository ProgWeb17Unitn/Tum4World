var scrollingTimer;

function handleScroll() {
    /*
        Durante lo scroll eseguo 2 operazioni:
            1) Cambio lo stile:
                -Ciò viene eseguito in 2 passaggi, quanto avviene lo scroll, viene cambiato immediatamente il colore
                 del bordo a rosso e viene eliminato il timeout precedente, viene impostato poi un timeout che lo
                 fa tornare al colore normale dopo 0.2 secondi
            2) Cambio la posizione:
                - utilizzo il valore in Y per cambiare la sua posizione;
    */
    var circle = document.getElementsByClassName("cerchio")[0];
    circle.style.border = "4px solid #AB3428";

    clearTimeout(scrollingTimer);
    scrollingTimer = setTimeout(function() {
        circle.style.border = "2px solid #F5EE9E";
    }, 200);
    var scrollPosition = window.scrollY;
    console.log("window.scrollY = " + scrollPosition);
    circle.style.transform = "translate(-50%, calc(50% + " + scrollPosition + "px))";
}
window.addEventListener("scroll", handleScroll);