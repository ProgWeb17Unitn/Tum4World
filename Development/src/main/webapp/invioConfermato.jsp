<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/layouts/base.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/skel.js"></script> <!-- usa questo format per il tuo js-->
    <!--- Inserisci qui i tuoi file css--->

</head>

<body>
<%@ include file="static/header.jsp" %>

<h3>Grazie di averci contatto!<br>
    Riceverà il prima possibile una risposta al suo indirizzo email</h3>


<%@ include file="static/footer.html" %>
</body>

<!-- Aggiunta condizionale del Cookie Banner VA SPOSTATO DA QUI-->
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="static/cookieBanner.html" %>
<% } %>

<script>
    page.load();
</script>
</html>