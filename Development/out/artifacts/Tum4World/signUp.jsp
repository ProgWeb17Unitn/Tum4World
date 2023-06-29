<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/layouts/base.css">
    <script src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/validateSignUpForm.js"></script>
</head>

<body>
<%@ include file="static/header.jsp" %>

<!--- form brutto per provare, poi prendiamo qualche design da codepen --->

<form method="POST" name="formSignUp">
    <p>nome</p>
    <input type="text" name="nome">
    <p>cognome</p>
    <input type="text" name="cognome">
    <p>data di nascita</p>
    <input type="date" name="dataNascita"> <!--- TODO data dovrebbe essere nel formato GG/MM/AAAA, allora bisogna usare un campo text e validarlo? --->
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
    <p>conferma password</p>
    <input type="text" name="passwordConferma">
    <br><br>
    <input type="button" value="registrati" onclick="validateData()">
    <br><br>
    <input type="button" value="reset" onclick="resetForm()">
    <br><br>
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
