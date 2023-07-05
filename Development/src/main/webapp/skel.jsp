<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <script src="scripts/base.js"></script>
    <!--- Inserisci qui i tuoi file css--->

</head>

<body>
<%@ include file="header.jsp" %>

<main>
    <!--- Inserisci qui il tuo codice HTML
          NOTA BENE: il div esterno deve avere un id="index"--->
</main>

<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.html" %>
</body>
</html>