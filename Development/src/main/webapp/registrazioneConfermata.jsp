<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <script src="scripts/base.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>

<h1>Registrazione confermata</h1>
<h2>Ora puoi andare nella sezione login ed effettuare l'accesso</h2>


<!-- Aggiunta condizionale del Cookie Banner VA SPOSTATO DA QUI-->
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.html" %>
</body>
</html>