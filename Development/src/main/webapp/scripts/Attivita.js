function handleScroll() {
    baseOnscroll();
}
window.addEventListener("scroll", handleScroll);


window.addEventListener('load', function() {
    /* Poteva essere fatto direttamente da jsp con una struttura tipo:
        <div class="img-container">
            <a href="./Attivitax.jsp"> <img src="assets/images/Attivita/Attivita1.jpg"> </a>
        </div>

        Ma in questo modo è stato possibile avere una struttura più leggibile del html/css ed è possibile aggiungere funzioni
        al on click se necessarie in futuro
     */
    var immagine1 = document.getElementById('Attivita-img1');
    immagine1.addEventListener('click', function() {
        window.location.href = './Attivita1';
    });
    var immagine2 = document.getElementById('Attivita-img2');
    immagine2.addEventListener('click', function() {
        window.location.href = './Attivita2';
    });
    var immagine3 = document.getElementById('Attivita-img3');
    immagine3.addEventListener('click', function() {
        window.location.href = './Attivita3';
    });
});
