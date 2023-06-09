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
  <script src="scripts/Attivita1.js"></script>

</head>

<body>
<%@ include file="static/header.html" %>

  <div class="flexbox-container" id="index">

    <div class="title"><p>Salvataggio e Riabilitazione</p></div>
    <div class="descrizione">
      <p> Testo 1 ejfnsajkdjkdwasjksnafkjnsfdakjn</p>
    </div>
    <div class="galleria">
      <img class="img-element" src="assets/images/Attivita1/1.png" id="img1">
      <img class="img-element" src="assets/images/Attivita1/2.png">
      <img class="img-element" src="assets/images/Attivita1/3.png">
      <img class="img-element" src="assets/images/Attivita1/4.png">
      <img class="img-element" src="assets/images/Attivita1/5.png">
      <img class="img-element" src="assets/images/Attivita1/6.png">
      <img class="img-element" src="assets/images/Attivita1/7.png">
      <img class="img-element" src="assets/images/Attivita1/1.png">
      <img class="img-element" src="assets/images/Attivita1/2.png">
      <img class="img-element" src="assets/images/Attivita1/3.png">
      <img class="img-element" src="assets/images/Attivita1/4.png">
      <img class="img-element" src="assets/images/Attivita1/5.png">
      <img class="img-element" src="assets/images/Attivita1/6.png">
      <img class="img-element" src="assets/images/Attivita1/7.png">
    </div>

    <div class="descrizione">
      <p> Testo 2 ejfnsajkdjkdwasjksnafkjnsfdakjn</p>
    </div>
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