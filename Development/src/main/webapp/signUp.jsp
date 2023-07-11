<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/formSignUp.css">

    <script src="scripts/base.js"></script>
    <script type="text/javascript" src="scripts/validateSignUpForm.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>

    <h1 id="index">Crea un account</h1>

    <div class="error-box" id="error">
        <h1 id="errorText"></h1>
    </div>
    <form method="POST" name="formSignUp" id="signup">
        <div class="form-content">
        <div class="form-row">
            <div class="form-element">
                <label for="nome">Nome</label>
                <input type="text" name="nome" id="nome">
            </div>
            <div class="form-element">
                <label for="cognome">Cognome</label>
                <input type="text" name="cognome" id="cognome">
            </div>
        </div>

        <div class="form-row">
            <div class="form-element">
                <label for="email">Email</label>
                <input type="text" name="email" id="email">
            </div>

            <div class="form-element">
                <label for="telefono">Numero di telefono</label>
                <input type="tel" name="telefono" id="telefono">
            </div>
        </div>

        <div class="form-row">
            <div class="form-element">
                <label for="username">Username</label>
                <input type="text" name="username" id="username">
            </div>

            <div class="form-element">
                <label for="dataNascita">Data di nascita</label>
                <input type="date" name="dataNascita" id="dataNascita">
            </div>
        </div>

        <div class="form-row">
            <div class="form-element">
                <label for="password">Password</label>
                <input type="password" name="password" id="password">
            </div>

            <div class="form-element">
                <label for="passwordConferma">Conferma password</label>
                <input type="password" name="passwordConferma" id="passwordConferma">
            </div>
        </div>

        <div class="form-row">
            <div class="form-element">
                <label for="tipo">Tipo utente</label>
                <select name="tipo" id="tipo">
                    <option value="">-- selezionare --</option>
                    <option value="aderente">Aderente</option>
                    <option value="simpatizzante">Simpatizzante</option>
                </select>
            </div>

            <div class="form-element buttons">
                <input type="button" value="Registrati!" class="submit" onclick="validateData()">
                <input type="button" value="🗑️" class="reset" onclick="resetForm()">
            </div>
        </div>
        </div>
    </form>

</main>
<!-- Aggiunta condizionale del Cookie Banner RIMUOVERE SOLO SE NECESSATIO-->
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.jsp" %>
</body>
</html>
