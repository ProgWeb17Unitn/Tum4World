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
    <script src="scripts/Aderente.js"></script>
    <script src="scripts/Simpatizzante.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>
    <div class="flexbox-container" id="index">
        <%@ include file="SimpatizzanteAderente.html" %>

        <div class="Bottoni">
            <div class="bottone-elemento" id="btn1" onclick="visualizzaDati()">
                <img src="assets/images/S/eyeCLOSEDWHITE.svg" id="eyeicon">
                <p>Visualizza Dati</p>
            </div>
            <div class="bottone-elemento" id="btn2" onclick="cancellaIscrizione()">
                <img src="assets/images/S/binWHITE.svg" id="bin">
                <p>Cancella Iscrizione</p>
            </div>
        </div>

    </div>
</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.jsp" %>
</body>
</html>