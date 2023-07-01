<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<!-- Le sessioni sono gestite tramite filtri e servlets --->
<link rel="stylesheet" href="styles/layouts/header.css">
<link rel="stylesheet" href="styles/themes/dark/header.css">
<script src="scripts/base.js"></script>
<header>
    <h1 class="coloredText">Tum4World</h1>
    <nav class="coloredText">
        <a href="<%= response.encodeURL("./homepage") %>">Home</a>
        <a href="<%= response.encodeURL("./Attivita") %>">Attività</a>
        <a href="<%= response.encodeURL("./contatti") %>">Contatti</a>
        <a href="<%= response.encodeURL("./signUp") %>">SignUp</a>
        <a href="<%= response.encodeURL("./login") %>">LogIn</a>
    </nav>

    <img src="assets/themes/dark/hamburger.svg" alt="☰" id="hamburger">
</header>