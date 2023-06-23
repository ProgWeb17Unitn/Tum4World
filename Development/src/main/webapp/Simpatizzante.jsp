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
  <div class="Title">
    <p class="BigTitle">Dashboard</p>
    <div class="dashboard">
    <p class="Explanation">  E' bello rivederti! Qui trovi la tua area privata
      da qui puoi modificare il tuo profilo. Puoi cliccare sulle attivita'
      per iscriverti ad esse. Inoltre in basso puoi visualizzare i dati del tuo profilo e cancellarlo in
      caso tu lo voglia...
    </p>
    <img src="assets/images/S/settingsicon.svg" id="dashboardimg" alt="settings">
    </div>
  </div>



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

  <div class="Bottoni">
    <div class="bottone-elemento" id="btn1">
      <img src="assets/images/S/eyeCLOSED.svg" id="eyeicon"><p>Visualizza Dati</p>
    </div>
    <div class="bottone-elemento" id="btn2">
      <img src="assets/images/S/bin.svg" id="bin"> <p>Cancella Iscrizione</p>
    </div>
  </div>

</div>
<%@ include file="static/footer.html" %>
</body>


<script>
  page.load();
</script>
</html>