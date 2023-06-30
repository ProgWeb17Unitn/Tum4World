<!DOCTYPE html>

<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/layouts/base.css">
    <script src="scripts/base.js"></script>
    <script src="https://smtpjs.com/v3/smtp.js"></script>   <!-- Library for SMTP protocol --->
    <script type="text/javascript" src="scripts/validateContattiForm.js"></script>
</head>

<body>
<%@ include file="static/header.jsp" %>

<h3>I nostri contatti:</h3>
<p><b>Indirizzo:</b> Via temporanea 156, Trento (17000), Italia<br>
    <b>Numero di telefono:</b> (+39) 300 300 9999<br>
    <b>Email: </b>tum4world@nessunonoluogonoesiste.com</p><br>

<h3>Contattaci direttamente da qui compilando il seguente form:</h3>
<form method="GET" id="formContatti" name="formContatti">

    <label for="nome">Nome e Cognome</label><br>
    <input type="text" id="nome" name="nome" size="30"><br><br>

    <label for="email">Email</label><br>
    <input type="text" id="email" name="email" size="30"><br><br>

    <label>Motivo</label><br>
    <input type="radio" id="problema" name="motivo" value="problema">
    <label for="problema">Segnalazione problema</label><br>
    <input type="radio" id="informazioni" name="motivo" value="informazioni">
    <label for="informazioni">Richiesta ulteriori informazioni</label><br>
    <input type="radio" id="altro" name="motivo" value="altro">
    <label for="altro">Altro</label><br><br>

    <label for="dettagli">Ulteriori dettagli</label><br>
    <textarea id="dettagli" name="dettagli" rows="6" cols="30"></textarea><br><br>

    <input type="button" value="INVIO" onclick="validateData()">
    <input type="button" value="RESET" onclick="resetData()">

</form>


<%@ include file="static/footer.html" %>
</body>

<script>
    page.load();
</script>

</html>
