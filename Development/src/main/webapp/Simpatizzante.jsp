<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1.0">
  <title>Tum4World</title>
  <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
  <link rel="stylesheet" href="styles/layouts/base.css">
  <link rel="stylesheet" href="styles/layouts/Simpatizzante.css">
  <script src="scripts/base.js"></script>


</head>

<body>
<%@ include file="static/header.jsp" %>

<div class="flexbox-container" id="index">
  <div class="Title"> Dashboard</div>
  <div class="Attivita-container">
    <div class="Attivita">
      <div class="Attivita-title">Salvataggio e Riabilitazione</div>
      <img src="assets/images/Attivita/Attivita1.jpg" alt="Salvataggio e Riabilitazione">
    </div>

    <div class="Attivita">
      <div class="Attivita-title">Educazione e Sensibilizzazione</div>
      <img src="assets/images/Attivita/Attivita2.jpg" alt="Educazione e Sensibilizzazione">
    </div>

    <div class="Attivita">
      <div class="Attivita-title">Prevenzione e Salvaguardia</div>
      <img src="assets/images/Attivita/Attivita3.jpg" alt="Prevenzione e Salvaguardia">
    </div>


  </div>
  <div class="Bottoni">Bottoni
    <div class="Informazioni"></div>
    <div class="Cancellazione"></div>
  </div>

</div>
<%@ include file="static/footer.html" %>
</body>


<script>
  page.load();
</script>
</html>