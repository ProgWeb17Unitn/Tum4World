<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/Amministratore.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/Amministratore.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>
    <div class="flexbox-container" id="index">

        <div class="Dashboard">
            <div class="Dashboard-text">
                <p class="Dashboard-title">Dashboard</p>
                <p class="Dashboard-description">
                    Di seguito le operazioni consentite in quanto amministratore:<br>
                    <br>- Elencare tutti gli utenti registrati;
                    <br>- Elencare tutti i simpatizzanti;
                    <br>- Elencare tutti gli aderenti;
                    <br>- Visualizzare il numero di visite che il sito ha ricevuto e l’istogramma delle visite per ogni pagina;
                    <br>- Resettare i contatori riguardo il numero di visite;
                    <br>- Visualizzare le donazioni ricevute mese per mese.</p>
            </div>
            <div class="Dashboard-image">
                <img src="assets/images/S/settingsicon2.svg" id="dashboardimg" alt="settings">
            </div>
        </div>

        <div class="Bottoni">
            <div class="bottone-elemento" id="btn1" onclick="visualizzaRegistrati()">
                <img src="assets/images/S/eyeCLOSED.svg" id="eyeicon1">
                <p>Visualizza Utenti Registrati</p>
            </div>
            <div class="bottone-elemento" id="btn2" onclick="visualizzaSimpatizzanti()">
                <img src="assets/images/S/eyeCLOSED.svg" id="eyeicon2">
                <p>Visualizza Simpatizzanti</p>
            </div>
            <div class="bottone-elemento" id="btn3" onclick="visualizzaAderenti()">
                <img src="assets/images/S/eyeCLOSED.svg" id="eyeicon3">
                <p>Visualizza Aderenti</p>
            </div>
            <div class="bottone-elemento" id="btn4" onclick="visualizzaVisite()">
                <img src="assets/images/S/eyeCLOSED.svg" id="eyeicon4">
                <p>Visualizza Visite</p>
            </div>
            <div class="bottone-elemento" id="btn5" onclick="resetContatori()">
                <img src="assets/images/S/bin.svg" id="bin">
                <p>Reset Contatori</p>
            </div>
            <div class="bottone-elemento" id="btn6" onclick="visualizzaDonazioni()">
                <img src="assets/images/S/eyeCLOSED.svg" id="eyeicon6">
                <p>Visualizza Donazioni</p>
            </div>
        </div>

    </div>
</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.html" %>
</body>
</html>