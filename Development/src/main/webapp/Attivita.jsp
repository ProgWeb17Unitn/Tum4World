<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/Attivita.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/Attivita.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>
    <div class="flexbox-container" id="index">

        <div class="title-grande">Le nostre Attivita'</div>


        <div class="attivita" id="attivita1">
            <div class="Rectangle" id="Rectangle1">
                <div class="title-attivita" id="title-attivita1">Salvataggio e Riabilitazione</div>
                <p> Il nostro principale obiettivo e quello da cui la nostra associazione è nata è quello di salvare i
                    nostri amici
                    volanti! Grazie a segnalazioni dei locali identifichiamo gli esemplari che hanno bisogno di aiuto e
                    li portiamo nel nostro rifugio pià vicino dove riceveranno le cure mediche adeguate.</p>
            </div>
            <a class="fotoAttivita" href="<%= response.encodeURL("./Attivita1") %>"><img class="images"
                                                                                         src="assets/images/Attivita/Attivita1.jpg"
                                                                                         alt="Salvataggio e Riabilitazione"
                                                                                         id="Attivita-img1"></a>
        </div>

        <div class="attivita" id="attivita2">
            <a class="fotoAttivita" href="<%= response.encodeURL("./Attivita2") %>"><img class="images"
                                                                                         src="assets/images/Attivita/Attivita2.jpg"
                                                                                         alt="Educazione e Sensibilizzazione"
                                                                                         id="Attivita-img2"></a>
            <div class="Rectangle" id="Rectangle2">
                <div class="title-attivita" id="title-attivita2">Educazione e Sensibilizzazione</div>
                <p> Negli anni abbiamo capito che non è possibile salvare il mondo da soli, abbiamo bisogno di aiuto!
                    Educare i bambini che un giorno diventeranno i cittadini ed i politici di questo mondo è
                    fondamentale
                    per mostrargli l'impatto delle nostre azioni sui nostri amici animali. Ma le nostre attività di
                    sensibilizzazione non
                    si fermano qua...</p>
            </div>
        </div>

        <div class="attivita" id="attivita3">
            <div class="Rectangle" id="Rectangle3">
                <div class="title-attivita" id="title-attivita3">Prevenzione e Salvaguardia</div>
                <p>Uno dei problemi più imporanti che i pappagalli stanno affrontando in questi anni è la distruzione
                    del
                    loro
                    habitat naturale. Deforestazione, inquinamento e cambiamenti climatici stanno mettendo a dura prova
                    la
                    proliferazione
                    della specie, per questo motivo molti dei nostri volontari, aiutati da abitanti ed associazioni
                    amiche,
                    si impegnano
                    nella cura dell'ambiente.</p>
            </div>
            <a class="fotoAttivita" href="<%= response.encodeURL("./Attivita3") %>"><img class="images"
                                                                                         src="assets/images/Attivita/Attivita3.jpg"
                                                                                         alt="Prevenzione e Salvaguardia"
                                                                                         id="Attivita-img3"></a>

        </div>


    </div>
</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.jsp" %>
</body>
</html>