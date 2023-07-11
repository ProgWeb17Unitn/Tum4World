<link rel="stylesheet" href="styles/footer.css">
<script src="scripts/footer.js"></script>

<footer style="position: relative">
    <p>Tum4World<br>
        Via temporanea, 156<br>
        Trento (17000), Italia</p>
    <div id="quotes">
        <p><br><br></p>
        <img id="helper" alt="helper" src="assets/images/icon.svg">
        <audio>
            <source src="assets/sounds/parrot.ogg">
            <source src="assets/sounds/parrot.mp3">
        </audio>
    </div>
</footer>

<script>
    quotes.init();
    theme.switch("<%= (session != null && session.getAttribute("tipo") != null) ? session.getAttribute("tipo") : "none"%>");
</script>