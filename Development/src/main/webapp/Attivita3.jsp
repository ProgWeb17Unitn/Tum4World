<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/Attivita123.css">
    <link rel="stylesheet" href="styles/Attivita3.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/Attivita123.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>
    <div class="flexbox-container" id="index">

        <div class="title"><p>Prevenzione e Salvaguardia</p></div>

        <div class="descrizione" id="introduzione">
            <span class="titoletto">Obbiettivo:</span>
            <p>
                Noi volontari siamo numerosi e molto determinati ma non possiamo essere ovunque! È fondamentale riuscire
                a
                <span class="evidenziato">garantire l'esistenza di un ecosistema adatto ai nostri amici pappagalli</span>
                così che essi non abbiano più bisogno di noi!
            </p>
        </div>

        <div class="descrizione" id="salvataggio">
            <span class="titoletto">L'Inquinamento:</span>
            <p>
                Uno dei problemi più importanti per i pappagalli è l'inquinamento dei loro Habitat, molto spesso le
                foreste
                in cui vivono sono vere e proprie
                <span class="evidenziato">discariche a celo aperto</span>.
                Ciò danneggia la qualità dell'
                <span class="evidenziato">aria</span>
                che respirano,dell'
                <span class="evidenziato">acqua</span>
                che bevono e ciò che
                <span class="evidenziato"> mangiano</span>
                rendendoli
                <span class="evidenziato">deboli</span> e
                <span class="evidenziato">ammalati</span>.
                Per questo motivo collaboriamo con altre
                associazioni come Greenpeace o WWF per
                <span class="evidenziato">bonificare</span>
                le zone in cui abitano.
            </p>
        </div>


        <div class="galleria">
            <img class="img-element" src="assets/images/Attivita3/attivita3_1.jpg" alt="Inquinamento">
            <img class="img-element" src="assets/images/Attivita3/attivita3_2.jpg" alt="Deforestazione">
            <img class="img-element" src="assets/images/Attivita3/attivita3_3.jpg" alt="Bracconaggio">
            <img class="img-element" src="assets/images/Attivita3/attivita3_4.jpg" alt="Manifestazione">
            <img class="img-element" src="assets/images/Attivita3/attivita3_5.jpg" alt="Riserva Naturale">
        </div>

        <div class="descrizione" id="cura">
            <span class="titoletto">Clima e Foreste:</span>
            <p>
                Un altra sfida impegnativa è legata al cambio climatico e la deforestazione. I pappagalli sono poco
                resistenti ai cambi di temperatura.
                Inoltre in molte zone in cui vivono i governi stanno applicando politiche di
                <span class="evidenziato">deforestazione intensiva</span>. Così come per la lotta all'inquinamento ci
                uniamo
                alle associazioni che cercano di tutelare
                la natura ed inoltre abbiamo capito durante gli anni che la cosa più utile per i nostri pappagalli è
                <span class="evidenziato">creare delle aree protette</span>.
                Parchi nazionali e riserve ci permettono di avere il pieno controllo di cosa accade all'interno del loro
                ambiente e riusciamo ad
                applicare delle tecniche per contrastare i cambiamenti climatici.
            </p>
        </div>

        <div class="descrizione" id="liberazione">
            <span class="titoletto">Bracconaggio e Tecnologia:</span>
            <p>
                Uno dei problemi storici che siamo quasi riusciti ad estirpare è il bracconaggio. Negli anni 2000 il
                <span class="evidenziato">10% degli esemplari</span>
                <span class="evidenziato">moriva</span> o
                <span class="evidenziato">veniva ferito</span>
                a causa di
                <span class="evidenziato">cacciatori</span>
                che cercavano di catturare i pappagalli per poi rivenderli nei mercati di animali
                esotici. Collaborando con le autorità locali stiamo riuscendo ad eliminare il problema alla radice:
                <span class="evidenziato">bloccare e sanzionare con pene ingenti la vendita</span>.
                Inoltre applichiamo ad i pappagalli in cura un
                <span class="evidenziato">chip </span>
                il quale ci permette di raccogliere dati importanti sulla loro salute, su quella dell'habitat e
                scoraggia i
                bracconieri.
            </p>
        </div>

        <div class="prossimaAttivita">
            <p>Menù Attività</p>
            <a href="<%= response.encodeURL("./Attivita") %>"><img src="assets/images/Attivita1/frecciaDestra.svg"
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