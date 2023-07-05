<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/Attivita123.css">
    <link rel="stylesheet" href="styles/Attivita1.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/Attivita123.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>
    <div class="flexbox-container" id="index">

        <div class="title"><p>Salvataggio e Riabilitazione</p></div>

        <div class="descrizione" id="introduzione">
            <span class="titoletto">Obbiettivo:</span>
            <p>
                La nostra associazione è nata con un obiettivo chiaro:
                <span class="evidenziato">aiutare i pappagalli in difficoltà</span>.
                Con entusiasmo e determinazione, collaboriamo con i cittadini locali e monitoriamo attentamente gli
                habitat
                naturali per
                <span class="evidenziato">individuare </span>
                e
                <span class="evidenziato"> salvare </span>
                i nostri amici piumati.

            </p>
        </div>

        <div class="descrizione" id="salvataggio">
            <span class="titoletto">Salvataggio:</span>
            <p>
                Equipaggiati con furgoni e gabbie, ci dirigiamo tempestivamente verso le
                <span class="evidenziato"> zone </span>
                segnalate per salvare i pappagalli bisognosi. Muniti di
                <span class="evidenziato">retini appositi</span> e prestando attenzione estrema,
                li prendiamo con noi e li
                <span class="evidenziato"> portiamo al sicuro </span>
                nel rifugio più vicino.
            </p>
        </div>


        <div class="galleria">
            <img class="img-element" src="assets/images/Attivita1/attivita1_1.jpg" alt="Pappagallo in riabilitazione">
            <img class="img-element" src="assets/images/Attivita1/attivita1_2.jpg" alt="Veterinario">
            <img class="img-element" src="assets/images/Attivita1/attivita1_3.jpg" alt="Pappagalli che giocano">
            <img class="img-element" src="assets/images/Attivita1/attivita1_4.jpg" alt="Pappagalli in riabilitazione">
            <img class="img-element" src="assets/images/Attivita1/attivita1_5.jpg" alt="Pappagalli liberati">
        </div>

        <div class="descrizione" id="cura">
            <span class="titoletto">Riabilitazione:</span>
            <p>
                Al nostro rifugio, offriamo
                <span class="evidenziato"> cure veterinarie specializzate </span>
                e
                <span class="evidenziato">vaccinazioni preventive </span>
                ai pappagalli appena arrivati. Ogni paziente riceve un
                <span class="evidenziato"> microchip </span>
                per monitorare i loro progressi e garantire il loro benessere.
                Inoltre, promuoviamo la convivenza tra diverse specie, creando un
                <span class="evidenziato"> ambiente stimolante </span>
                che accelera il processo di riabilitazione.
            </p>
        </div>

        <div class="descrizione" id="liberazione">
            <span class="titoletto">Liberazione:</span>
            <p>
                Quando i pappagalli sono
                <span class="evidenziato">pronti per affrontare nuovamente il volo</span>
                , sperimentiamo la gioia più intensa:
                <span class="evidenziato">la liberazione!</span>
                Grazie alla loro incredibile intelligenza e al forte senso dell'orientamento, se li rilasciamo
                nel loro habitat originale, sono in grado di riconoscerlo e
                <span class="evidenziato">reintegrarsi rapidamente nella natura</span>.
            </p>
        </div>

        <div class="prossimaAttivita">
            <p>Prossima Attività</p>
            <a href="<%= response.encodeURL("./Attivita2") %>"><img src="assets/images/Attivita1/frecciaDestra.svg"
                                                                    alt="Prossima Attività" id="freccia"></a>
        </div>

    </div>
</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.html" %>
</body>
</html>