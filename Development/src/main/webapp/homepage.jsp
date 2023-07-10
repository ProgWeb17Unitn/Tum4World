<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/homepage.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/homepage.js"></script>
</head>

<body>

<%@ include file="header.jsp" %>

<main>
    <h2 id="index">Benvenuto a Tum4World</h2>
    <p>
        Tum4World è un'organizzazione <span class="coloredText">senza scopo di lucro</span> impegnata nella
        conservazione e nella protezione di diverse
        specie di pappagalli osservabili in tutto il globo.<br><br>
        Ci dedichiamo alla conservazione dei loro habitat naturali ed al benessere di queste maestose creature. La nostra
        organizzazione mira ad affrontare le sfide critiche con cui questi uccelli devono scontrarsi, tra cui la
        <span class="coloredText">distruzione dell'habitat, il commercio illegale e il cambiamento climatico.</span><br><br>
        La nostra speranza consiste nel <span class="coloredText">costruire partnership</span> con comunità locali,
        governi e altre organizzazioni per
        promuovere la conservazione sostenibile dei pappagalli e migliorare le loro possibilità di sopravvivenza.<br><br>
        La nostra missione è <span class="coloredText">salvaguardare e ripristinare</span> le popolazioni e gli habitat
        dei pappagalli in modo che le
        generazioni future possano continuare a meravigliarsi della loro <span class="coloredText">bellezza e intelligenza.</span>
    </p>

    <img id="logo" src="assets/images/logo.svg" alt="logo">

    <h2>Il nostro logo</h2>
    <p>
        Il logo della nostra associazione raffigura un'<span class="coloredText">ara scarlatta.</span><br><br>
        La scelta di utilizzare proprio questo volatile come immagine principale del nostro logo deriva dalla storia
        dell'associazione, che in passato si occupava principalmente della tutela di questa specie.<br><br>
        Tuttavia, grazie alle nostre attività, la compagnia si è fatta conoscere dal pubblico e si è potuta, <span
            class="coloredText">grazie al
        suo aiuto</span>, espandere ad altre specie di pappagalli, mantenendo sempre la stessa passione e il medesimo
        impegno
        nella loro salvaguardia.<br><br>
        Il logo rappresenta dunque l'anima della nostra associazione, <span class="coloredText">fondata sulla collaborazione, la dedizione e
        l'amore per gli animali che da sempre l'hanno accompagnata.</span>
    </p>

    <img id="mainImg" src="assets/images/homepage.png" alt="image">

    <h2>I nostri clienti</h2>
    <p>
        La nostra associazione ha come cliente <span class="coloredText">unico ed esclusivo</span> gli animali che
        proteggiamo, mettendo al primo posto
        la loro tutela e il loro benessere.<br><br>
        L'<span class="coloredText">80%</span> dei fondi che raccogliamo viene utilizzato direttamente per proteggere
        gli animali e i loro habitat,
        mentre il restante <span class="coloredText">20%</span> è destinato a investimenti per migliorare sempre di più
        il nostro lavoro.<br><br>
        Se vuoi saperne di più sulle nostre attività e su come puoi contribuire alla salvaguardia dei pappagalli, visita
        la sezione
        <a href="<%= response.encodeURL("./ChiSiamo") %>" class="coloredText">'chi siamo'</a> o scarica il
        <span class="coloredText">nostro volantino</span> esplicativo cliccando sul pulsante qui sotto.<br><br>
        <span class="coloredText">Grazie per il tuo sostegno!</span>
    </p>

    <a href="assets/download.pdf" download="volantino" id="download">Scarica volantino</a>

</main>

<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="backToTop.html" %>
<%@ include file="footer.html" %>

</body>
</html>
