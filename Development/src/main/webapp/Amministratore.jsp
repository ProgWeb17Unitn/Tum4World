<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/Amministratore.css">
    <link rel="stylesheet" href="styles/diagramma.css">
    <script src="scripts/base.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <script src="https://code.highcharts.com/modules/export-data.js"></script>
    <script src="https://code.highcharts.com/modules/accessibility.js"></script>
    <script src="scripts/Amministratore.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>

    <script>
        var my_JSON_array;

        let url = "visualizzaDonazioni";
        let xhttp = new XMLHttpRequest();
        xhttp.open("POST", url, true);
        xhttp.responseType = "json";

        // Callback
        xhttp.onreadystatechange = function () {
            let done = 4, ok = 200;
            if (xhttp.readyState === done && xhttp.status === ok) {
                my_JSON_array = this.response;

                // sostituisce gli 0 con null, per non farli vedere nel grafico
                my_JSON_array.forEach((element, index) => {
                    if (element === 0) {
                        my_JSON_array[index] = null;
                    }
                })

                const chart = Highcharts.chart('container', {
                    chart: {
                        type: 'line'
                    },
                    title: {
                        text: 'Donazioni nell ultimo anno'
                    },
                    subtitle: {
                        text: ''
                    },
                    xAxis: {
                        categories: [
                            'Jan',
                            'Feb',
                            'Mar',
                            'Apr',
                            'May',
                            'Jun',
                            'Jul',
                            'Aug',
                            'Sep',
                            'Oct',
                            'Nov',
                            'Dec'
                        ],
                        crosshair: true,
                        title: {
                            text: 'Mese'
                        }
                    },
                    yAxis: {
                        min: 0,
                        title: {
                            text: 'Valore in €'
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                            '<td style="padding:0"><b>{point.y} €</b></td></tr>',
                        footerFormat: '</table>',
                        shared: true,
                        useHTML: true
                    },
                    plotOptions: {
                        column: {
                            pointPadding: 0,
                            borderWidth: 0,
                            groupPadding: 0,
                            shadow: false
                        }

                    },
                    series: [{
                        name: 'Donazioni',
                        data: my_JSON_array
                    }]
                });

            }
        }
        xhttp.send();



        url = "visualizzaVisite";
        xhttp = new XMLHttpRequest();
        xhttp.open("POST", url, true);
        xhttp.responseType = "json";

        // Callback
        xhttp.onreadystatechange = function () {
            let done = 4, ok = 200;
            if (xhttp.readyState === done && xhttp.status === ok) {
                let my_JSON_array = this.response;
                if (my_JSON_array.length > 0) {

                    var visiteTot="Visite totali: "+my_JSON_array[0];
                    const pagine = [];
                    const visite = [];
                    var pagina=0;
                    for (let i = 1; i < my_JSON_array.length; i+=2){
                        pagine[pagina]=my_JSON_array[i];
                        pagine[pagina].replace(/"/g, "'");
                        visite[pagina]=parseInt(my_JSON_array[i+1]);
                        pagina++;
                    }

                    const chart = Highcharts.chart('container1', {
                        chart: {
                            type: 'column'
                        },
                        title: {
                            text: 'Visite per pagina'
                        },
                        subtitle: {
                            text: visiteTot
                        },
                        xAxis: {
                            categories: pagine,
                            crosshair: true,
                            title: {
                                text: 'Pagina'
                            }
                        },
                        yAxis: {
                            min: 0,
                            title: {
                                text: 'Numero di visite'
                            }
                        },
                        tooltip: {
                            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                                '<td style="padding:0"><b>{point.y} visite</b></td></tr>',
                            footerFormat: '</table>',
                            shared: true,
                            useHTML: true
                        },
                        plotOptions: {
                            column: {
                                pointPadding: 0,
                                borderWidth: 0,
                                groupPadding: 0,
                                shadow: false
                            }
                        },
                        series: [{
                            name: 'Visite',
                            data: visite

                        }]
                    });

                } else {

                }
            }
        }
        // Sending request
        xhttp.send();

    </script>

    <div class="flexbox-container" id="index">

        <div class="Dashboard">
            <div class="Dashboard-text">
                <p class="Dashboard-title">Dashboard</p>
                <p class="Dashboard-description">
                    Di seguito le operazioni consentite in quanto amministratore:<br>
                    <br>- Elencare tutti gli utenti registrati;
                    <br>- Elencare tutti i simpatizzanti;
                    <br>- Elencare tutti gli aderenti;
                    <br>- Visualizzare il numero di visite che il sito ha ricevuto e l’istogramma delle visite per ogni pagina;
                    <br>- Resettare i contatori riguardo il numero di visite;
                    <br>- Visualizzare le donazioni ricevute mese per mese.</p>
            </div>
            <div class="Dashboard-image">
                <img src="assets/images/S/AdminImage.svg" id="dashboardimg" alt="settings">
            </div>
        </div>

        <div class="Bottoni">
            <div class="bottone-elemento" id="btn1" onclick="visualizzaRegistrati()">
                <img src="assets/images/S/user-minus-svgrepo-com.svg" id="user1">
                <p>Visualizza Utenti Registrati</p>
            </div>
            <div class="bottone-elemento" id="btn2" onclick="visualizzaSimpatizzanti()">
                <img src="assets/images/S/user-minus-svgrepo-com.svg" id="user2">
                <p>Visualizza Simpatizzanti</p>
            </div>
            <div class="bottone-elemento" id="btn3" onclick="visualizzaAderenti()">
                <img src="assets/images/S/user-minus-svgrepo-com.svg" id="user3">
                <p>Visualizza Aderenti</p>
            </div>
            <div class="bottone-elemento" id="btn4" onclick="visualizzaVisite()">
                <img src="assets/images/S/bar-chart-diagram-2-svgrepo-com.svg" id="graphicon">
                <p>Visualizza Visite</p>
            </div>
            <div class="bottone-elemento" id="btn5" onclick="resetContatori()">
                <img src="assets/images/S/reset-svgrepo-com.svg" id="reset">
                <p>Reset Contatori</p>
            </div>
            <div class="bottone-elemento" id="btn6" onclick="visualizzaDonazioni()">
                <img src="assets/images/S/money-sign-money-dollar-sign-svgrepo-com.svg" id="moneyicon">
                <p>Visualizza Donazioni</p>
            </div>
        </div>

    </div>

    <figure class="highcharts-figure">
        <div id="container" style="min-width: 48vw; height: 50vh; margin: 0 auto; display: none"></div>
        <div id="container1" style="min-width: 48vw; height: 58vh; margin: 0 auto; display: none"></div>
    </figure>

</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.jsp" %>
</body>
</html>