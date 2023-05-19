<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false"%>
<!-- Le sessioni sono gestite tramite filtri e servlets --->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/layouts/base.css">
    <script src="scripts/base.js"></script>
    <!--- Inserisci qui i tuoi file css e i tuoi file jsp--->

</head>

<body>
<%@ include file="static/header.html" %>
<!--- Inserisci qui il tuo codice HTML--->







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