<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/Simpatizzante.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/Simpatizzante.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>

<div class="flexbox-container" id="index">
    <div class="Dashboard">
        <div class="Dashboard-text">
            <p class="Dashboard-title">Dashboard</p>
            <p class="Dashboard-description"><span class="highlight">E' bello rivederti!</span>
                Qui trovi la tua area privata tramite la quale puoi compiere diverse azioni:
                <br>
                <br>- Puoi cliccare sulle attivita' per iscriverti ad esse (in verde quelle a cui sei iscritto);
                <br>- Puoi visualizzare i dati del tuo profilo;
                <br>- Puoi cancellare il tuo profilo. Solo in caso tu lo voglia davvero...</p>
        </div>
        <div class="Dashboard-image">
            <img src="assets/images/S/settingsicon2.svg" id="dashboardimg" alt="settings">
        </div>
    </div>


    <div class="Attivita-container">
        <div class="Attivita" id="Attivita1" onclick="iscriviAttivita(1)">
            <div class="Attivita-title">Salvataggio e Riabilitazione</div>
            <img src="assets/images/Attivita/Attivita1.jpg" alt="Salvataggio e Riabilitazione">
        </div>

        <div class="Attivita" id="Attivita2" onclick="iscriviAttivita(2)">
            <div class="Attivita-title">Educazione e Sensibilizzazione</div>
            <img src="assets/images/Attivita/Attivita2.jpg" alt="Educazione e Sensibilizzazione">
        </div>

        <div class="Attivita" id="Attivita3" onclick="iscriviAttivita(3)">
            <div class="Attivita-title">Prevenzione e Salvaguardia</div>
            <img src="assets/images/Attivita/Attivita3.jpg" alt="Prevenzione e Salvaguardia">
        </div>

    </div>

    <div class="Bottoni">
        <div class="bottone-elemento" id="btn1"  onclick="visualizzaDati()">
            <img src="assets/images/S/eyeCLOSED.svg" id="eyeicon">
            <p>Visualizza Dati</p>
        </div>
        <div class="bottone-elemento" id="btn2">
            <img src="assets/images/S/bin.svg" id="bin">
            <p>Cancella Iscrizione</p>
        </div>
    </div>

</div>

<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.html" %>
</body>
</html>