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
    <link rel="stylesheet" href="styles/layouts/ChiSiamo.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/ChiSiamo.js"></script>

</head>

<body>
<%@ include file="static/header.html" %>

    <div class="vertical-line"></div>
    <div class="cerchio"></div>

    <div class="flexbox-container">

        <div class="flex-Container-element" id="flex-element1">

            <div class="elementoSX" id="elementoSX1">
                <img src="assets/images/ChiSiamo/imgSX1.png" class="images" id="imgSX1">
            </div>

            <div class="elementoDX" id="colonnaDX1">
                <div class="cartoon" id="cartoonDX1">
                    <div class="Triangle"></div>
                    <div class="Rectangle">
                        <p>La nostra storia inizia nel 2001, con 2 protagonisti ed 1 città: Betty Powell, John e Manaus.
                            Betty, una vivace signora di 84 anni, si trasferisce nel 1990 a Manaus, città alle porte della
                            maestosa Foresta Amazzonica. Il suo amore per la natura e le escursioni l'ha fatta incontrare con
                            John, un esemplare maestoso di Ara Scarlatta ferito ad un'ala. Decise così di portarlo a casa e costruire
                            un recinto per curarlo. Da lì in poi il numero di amici che hanno trovato un rifugio è sempre aumentato... </p> </div>
                </div>
            </div>

        </div>

        <div class="flex-Container-element" id="flex-element2">

            <div class="elementoSX" id="elementoSX2">
                <img src="assets/images/ChiSiamo/imgSX1.png" class="images" id="imgSX2">
            </div>

            <div class="elementoDX" id="colonnaDX2">
                <div class="cartoon" id="cartoonDX2">
                    <div class="Triangle"></div>
                    <div class="Rectangle">
                        <p>La nostra storia inizia nel 2001, con 2 protagonisti ed 1 città: Betty Powell, John e Manaus.
                            Betty, una vivace signora di 84 anni, si trasferisce nel 1990 a Manaus, città alle porte della
                            maestosa Foresta Amazzonica. Il suo amore per la natura e le escursioni l'ha fatta incontrare con
                            John, un esemplare maestoso di Ara Scarlatta ferito ad un'ala. Decise così di portarlo a casa e costruire
                            un recinto per curarlo. Da lì in poi il numero di amici che hanno trovato un rifugio è sempre aumentato... </p> </div>
                </div>
            </div>

        </div>

    </div>





<%@ include file="static/footer.html" %>
</body>

<!-- Aggiunta condizionale del Cookie Banner RIMUOVERE SOLO SE NECESSARIO-->
<% if ( (request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="static/cookieBanner.html" %>
<% } %>

<script>
    page.load();
</script>
</html>