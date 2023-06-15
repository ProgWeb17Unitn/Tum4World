<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/layouts/base.css">
    <script src="scripts/base.js"></script>
    <!--- posizione file js sbagliata TODO creare cartella dedicata --->
    <script type="text/javascript" src="./scripts/validateSignUpForm.js"></script>


</head>

<body>
<%@ include file="static/header.jsp" %>

<!--- form brutto per provare, poi prendiamo qualche design da codepen --->

<form method="GET" name="formSignUp">
    <p>nome</p>
    <input type="text" name="nome">
    <p>cognome</p>
    <input type="text" name="cognome">
    <p>data di nascita</p>
    <input type="date" name="dataNascita">
    <p>email</p>
    <input type="text" name="email">
    <p>numero di telefono</p>
    <input type="text" name="telefono">
    <p>tipo</p>
    <select name="tipo">
        <option value="aderente">Aderente</option>
        <option value="simpatizzante">Simpatizzante</option>
    </select>
    <p>username</p>
    <input type="text" name="username">
    <p>password</p>
    <input type="text" name="password">
    <input type="text" name="passwordConferma"><br><br>
    <input type="button" value="registrati" onclick="validateData()">
</form>





<!-- Aggiunta condizionale del Cookie Banner RIMUOVERE SOLO SE NECESSATIO-->
<% if ( (request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="static/cookieBanner.html" %>
<% } %>

<%@ include file="static/footer.html" %>
</body>



<script>
    page.load();
</script>
</html>
