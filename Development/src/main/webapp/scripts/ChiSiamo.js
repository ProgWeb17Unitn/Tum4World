var scrollingTimer;

window.addEventListener("scroll", function handleScroll() {
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
    circle.style.backgroundColor = "#F49E4C";


    clearTimeout(scrollingTimer);
    scrollingTimer = setTimeout(function () {
        circle.style.backgroundColor = "#F5C675";
    }, 200);
    var scrollPosition = window.scrollY;
    circle.style.transform = "translate(-50%, calc(50% + " + scrollPosition + "px))";
});