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

    <div class="descrizione" id="introduzione">
        <span class="titoletto">Obbiettivo:</span>
      <p>
        La nostra associazione è nata con un obiettivo chiaro: aiutare i pappagalli in difficoltà.
        Con entusiasmo e determinazione, collaboriamo con i cittadini locali e monitoriamo attentamente gli habitat
        naturali per individuare e salvare i nostri amici piumati.

      </p>
    </div>

    <div class="descrizione" id="salvataggio">
      <span class="titoletto">Salvataggio:</span>
      <p>
        Equipaggiati con furgoni e gabbie, ci dirigiamo tempestivamente verso le zone segnalate per salvare i
        pappagalli bisognosi. Muniti di retini appositi e di attenzione estrema,
        li prendiamo con noi e li portiamo al sicuro nel rifugio più vicino.
      </p>
    </div>


    <div class="galleria">
      <img class="img-element" src="assets/images/Attivita1/attivita1_1.jpg">
      <img class="img-element" src="assets/images/Attivita1/attivita1_2.jpg">
      <img class="img-element" src="assets/images/Attivita1/attivita1_3.jpg">
      <img class="img-element" src="assets/images/Attivita1/attivita1_4.jpg">
      <img class="img-element" src="assets/images/Attivita1/attivita1_5.jpg">
    </div>

    <div class="descrizione" id="cura">
      <span class="titoletto">Riabilitazione:</span>
      <p>
        Al nostro rifugio, offriamo cure veterinarie specializzate e vaccinazioni preventive ai pappagalli appena
        arrivati. Ogni paziente riceve un microchip per monitorare i loro progressi e garantire il loro benessere.
        Inoltre, promuoviamo la convivenza tra diverse specie, creando un ambiente stimolante che accelera il processo
        di riabilitazione.
      </p>
    </div>

    <div class="descrizione" id="liberazione">
      <span class="titoletto">Liberazione:</span>
      <p>
        Quando i pappagalli sono pronti per affrontare nuovamente il volo, sperimentiamo la gioia più intensa:
        la liberazione! Grazie alla loro incredibile intelligenza e al forte senso dell'orientamento, se li rilasciamo
        nel loro habitat originale, sono in grado di riconoscerlo e reintegrarsi rapidamente nella natura.
      </p>
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