<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/Contatti.css">
    <script src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/validateContattiForm.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>
    <h1 id="index">I nostri contatti</h1>
    <div class="container">
        <p><span class="contatti">Indirizzo:</span> Via temporanea 156, Trento (17000), Italia<br>
            <span class="contatti">Numero di telefono:</span> (+39) 300 300 9999<br>
            <span class="contatti">Email:</span> tum4world@nessunonoluogonoesiste.com</p><br>

        <h2>Contattaci direttamente da qui compilando il seguente form:</h2>
        <form method="GET" id="formContatti" name="formContatti">

            <div class="NomeCognomeLabel">
                <label for="nome">Nome e Cognome:</label><br>
                <label for="email">Email:</label><br>
            </div>

            <div class="NomeCognomeArea">
                <input type="text" id="nome" name="nome" size="30"><br><br>
                <input type="text" id="email" name="email" size="30"><br><br>
            </div>

            <label>Motivo:</label><br><br>
            <div class="Motivi">
                <div class="MotivoElement">
                    <input type="radio" id="problema" name="motivo" value="problema">
                    <label for="problema" class="MotivoLabel">Segnalazione problema</label><br>
                </div>
                <div class="MotivoElement">
                    <input type="radio" id="informazioni" name="motivo" value="informazioni">
                    <label for="informazioni" class="MotivoLabel">Richiesta ulteriori informazioni</label><br>
                </div>
                <div class="MotivoElement">
                    <input type="radio" id="altro" name="motivo" value="altro">
                    <label for="altro" class="MotivoLabel">Altro</label>
                </div>
            </div>

            <label for="dettagli" >Ulteriori dettagli:</label><br><br>
            <textarea id="dettagli" name="dettagli" rows="6" cols="30"></textarea><br><br>

            <input type="button" class="rect-bottone" id="form-btn1" value="INVIO" onclick="validateData()">
            <input type="button" class="rect-bottone" id="form-btn2" value="RESET" onclick="resetData()">

        </form>
    </div>
</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.html" %>
</body>
</html>
