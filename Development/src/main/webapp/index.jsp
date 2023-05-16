<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="">
    <link rel="stylesheet" href="static/styles/home.css">
    <script src="static/scripts/tmp.js"></script>
    <style></style>
</head>


<body>

<header>
    <h1 class="coloredText">Tum4World</h1>
    <nav class="coloredText">
        <a href="index.html">Home</a>
        <a href="chi_siamo.html">Chi siamo</a>
        <a href="attivita.html">Attività</a>
        <a href="contatti.html">Contatti</a>
        <a href="sign_in.html">Sign-in</a>
        <a href="log_in.html">Log-in</a>
    </nav>

    <img src="" alt="☰" id="hamburger">
</header>


<main>


    <h2 class="coloredText" id="index">Benvenuto a Tum4World</h2>
    <p class="coloredText">
        Tum4World è un'organizzazione senza scopo di lucro impegnata nella conservazione e nella protezione di diverse specie di pappagalli osservabili attraverso tutto il globo<br><br>
        Ci dedichiamo alla conservazione dei loro habitat naturali e al benessere di queste maestose creature. La nostra organizzazione mira ad affrontare le sfide critiche con cui questi uccelli devono scontrarsi, tra cui la distruzione dell'habitat, il commercio illegale e il cambiamento climatico<br><br>
        La nostra speranza consiste nel costruire partnership con comunità locali, governi e altre organizzazioni per promuovere la conservazione sostenibile dei pappagalli e migliorare le loro possibilità di sopravvivenza<br><br>
        La nostra missione è salvaguardare e ripristinare le popolazioni e gli habitat dei pappagalli in modo che le generazioni future possano continuare a meravigliarsi della loro bellezza e intelligenza
    </p>

    <img id="logo" src="" alt="logo">

    <h2 class="coloredText">Il nostro logo</h2>
    <p class="coloredText">
        Il logo della nostra associazione raffigura un'ara scarlatta <br><br>
        La scelta di utilizzare proprio questo volatile come immagine principale del nostro logo deriva dalla storia dell'associazione, che in passato si occupava principalmente della tutela di questa specie<br><br>
        Tuttavia, grazie alle nostre attività, la compagnia si è fatta conoscere dal pubblico e si è potuta, grazie al suo aiuto, espandere ad altre specie di pappagalli, mantenendo sempre la stessa passione e il medesimo impegno nella loro salvaguardia<br><br>
        Il logo rappresenta dunque l'anima della nostra associazione, fondata sulla collaborazione, la dedizione e l'amore per gli animali che da sempre l'hanno accompagnata
    </p>

    <img id="mainImg" src="static/assets/images/homepage.png" alt="image">

    <h2 class="coloredText">I nostri clienti</h2>
    <p class="coloredText">
        La nostra associazione ha come cliente unico ed esclusivo gli animali che proteggiamo, mettendo al primo posto la loro tutela e il loro benessere<br><br>
        L'80% dei fondi che raccogliamo viene utilizzato direttamente per proteggere gli animali e i loro habitat, mentre il restante 20% è destinato a investimenti per migliorare sempre di più il nostro lavoro<br><br>
        Se vuoi saperne di più sulle nostre attività e su come puoi contribuire alla salvaguardia dei pappagalli, scarica il nostro volantino esplicativo cliccando sul pulsante qui sotto<br><br>
        Grazie per il tuo sostegno!
    </p>

    <button class="coloredText" id="download" type="button">Scarica volantino</button>

</main>

<footer class="coloredText">
    <!-- Aggiunta condizionale del Cookie Banner-->
    <% if ( (request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
    <%@ include file="static/assets/cookies/CookieBanner.html" %>
    <% } %>
    <p>Tum4World<br>
        Via temporanea, 156<br>
        Trento (17000), Italia</p>
    <div id="quotes">
        <p>This is a quote!</p>
        <img id="helper" alt="helper">
        <audio>
            <source src="static/assets/sounds/parrot.ogg">
            <source src="static/assets/sounds/parrot.mp3">
        </audio>
    </div>
    <img src="" alt="switch theme" id="themeSwitcher">
</footer>

</body>


<script>
    page.load();
    quotes.init();
</script>

</html>