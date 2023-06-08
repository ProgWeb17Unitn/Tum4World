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
  <link rel="stylesheet" href="styles/layouts/Attivita1.css">
  <script src="scripts/base.js"></script>
  <script src="scripts/ChiSiamo.js"></script>

</head>

<body>
<%@ include file="static/header.html" %>

<div class="galleria">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
  <img class="img-element" src="assets/images/Attivita/Attivita1.jpg">
</div>
</body>

<!-- Aggiunta condizionale del Cookie Banner RIMUOVERE SOLO SE NECESSARIO-->
<% if ( (request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="static/cookieBanner.html" %>
<% } %>

<script>
  page.load();
</script>
</html>