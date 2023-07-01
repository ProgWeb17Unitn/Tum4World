<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/layouts/base.css">
    <link rel="stylesheet" href="styles/layouts/Attivita123.css">
    <link rel="stylesheet" href="styles/layouts/Attivita2.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/Attivita123.js"></script>

</head>

<body>
<%@ include file="static/header.jsp" %>

<div class="flexbox-container" id="index">

    <div class="title"><p>Educazione e Sensibilizzazione</p></div>

    <div class="descrizione" id="introduzione">
        <span class="titoletto">Obbiettivo:</span>
        <p>
            Educare i bambini che un giorno diventeranno i cittadini ed i politici di questo mondo è fondamentale
            per mostrargli
            <span class="evidenziato">l'impatto delle nostre azioni</span>
            sui nostri amici animali.
        </p>
    </div>

    <div class="descrizione" id="salvataggio">
        <span class="titoletto">L'origine:</span>
        <p>
            La nostra fondatrice
            <span class="evidenziato"> Betty amava </span>
            solo una cosa più degli animali: i suoi
            <span class="evidenziato">nipoti</span>
            Cominciò sin da subito a capire come la
            <span class="evidenziato">vicinanza</span> tra
            <span class="evidenziato">pappagalli </span>e <span class="evidenziato">bambini</span>
            fosse un
            <span class="evidenziato">opportunità di crescita</span>
            e di gioco per entrambi. Così tutte le volte che si entrava in rifugio bisognava stare attenti a non
            far scappare non solo i pappagalli ma anche i numerosi bambini che giocavano con loro!
        </p>
    </div>


    <div class="galleria">
        <img class="img-element" src="assets/images/Attivita2/attivita2_1.jpg" alt="Bambina con pappagallo">
        <img class="img-element" src="assets/images/Attivita2/attivita2_2.jpg" alt="Bambina con pappagallo">
        <img class="img-element" src="assets/images/Attivita2/attivita2_3.jpg" alt="Attività a scuola">
        <img class="img-element" src="assets/images/Attivita2/attivita2_4.jpg" alt="Attività con bambini">
        <img class="img-element" src="assets/images/Attivita2/attivita2_5.jpg" alt="Attività con bambini">
        <img class="img-element" src="assets/images/Attivita2/attivita2_6.jpg" alt="Volontaria con Bambino">
    </div>

    <div class="descrizione" id="cura">
        <span class="titoletto">Le Scuole:</span>
        <p>
            Molti dei nostri volontari sono
            <span> chiamati nelle scuole
        </span>a raccontare quello che facciamo e come aiutiamo i nostri amici
            pappagalli. Spiegano ai bambini quanto sia bello
            <span class="evidenziato">prendersi cura </span>
            di chi ci sta attorno, animali inclusi!
            Grazie a foto, video e giochi di gruppo riusciamo a
            <span class="evidenziato"> condividere i nostri valori </span> anche a realtà lontane dai nostri rifugi;
            questo è importante perché ciò che cerchiamo di trasmettere non vale solo per i pappagalli ma per tutta la
            natura!
            <span class="evidenziato">L'anno scorso abbiamo raggiunto ben 400 scuole</span>!
        </p>
    </div>

    <div class="descrizione" id="liberazione">
        <span class="titoletto">Le attività:</span>
        <p>
            Ma il nostro lavoro non si ferma alle parole! Cerchiamo quando possibile di far venire i ragazzi a visitare
            il nostro rifugio e di
            <span class="evidenziato">fargli provare in prima persona </span> quanto è bello prendersi cura di questi
            splendidi animali.
            Quando scolaresche, gruppi parrocchiali o
            <span class="evidenziato">scout </span> arrivano, partecipano con divere attività: tra chi
            <span class="evidenziato">pulisce il rifugio </span>,
            chi <span class="evidenziato">prepara il mangiare </span>
            e chi
            <span class="evidenziato">assiste i veterinari</span>
            ognuno può trovare il suo modo per
            <span class="evidenziato">sentirsi parte di qualcosa di più grande</span>.
            Crediamo che questo sia il modo più efficacie per far sì che la nostra causa diventi una causa di tutti.
        </p>
    </div>

    <div class="prossimaAttivita">
        <p>Prossima Attività</p>
        <a href="<%= response.encodeURL("./Attivita3") %>"><img src="assets/images/Attivita1/frecciaDestra.svg"
                                                                alt="Prossima Attività" id="freccia"></a>
    </div>

</div>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="static/cookieBanner.html" %>
<% } %>

<%@ include file="static/footer.html" %>
</body>

<script>
    page.load();
</script>
</html>