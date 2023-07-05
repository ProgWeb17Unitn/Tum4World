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
<main>
    <h3>Grazie di averci contatto!<br>
        Riceverà il prima possibile una risposta al suo indirizzo email</h3>

</main>
<%@ include file="footer.html" %>
</body>

<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>
</html>