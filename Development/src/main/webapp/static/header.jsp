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
        <a href="<%= response.encodeURL("./ChiSiamo") %>">Chi Siamo</a>
        <a href="<%= response.encodeURL("./Attivita") %>">Attività</a>
        <a href="<%= response.encodeURL("./Contatti") %>">Contatti</a>

        <% HttpSession session = request.getSession(false);
            String type;
            if (session != null && session.getAttribute("tipo") != null)
                type = (String) session.getAttribute("tipo");
            else
                type = "none";

            if (type.equals("aderente")) { %>
        <a href="<%= response.encodeURL("./Aderente") %>">SignUp</a>
        <a href="<%= response.encodeURL("./logOut") %>">LogIn</a>
        <% } else if (type.equals("simpatizzante")) { %>
        <a href="<%= response.encodeURL("./Simpatizzante") %>">SignUp</a>
        <a href="<%= response.encodeURL("./logOut") %>">LogIn</a>
        <% } else if (type.equals("admin")) { %>
        <a href="<%= response.encodeURL("./Admin") %>">SignUp</a>
        <a href="<%= response.encodeURL("./logOut") %>">LogIn</a>
        <% } else { %>
        <a href="<%= response.encodeURL("./signUp") %>">SignUp</a>
        <a href="<%= response.encodeURL("./logIn") %>">LogIn</a>
        <% } %>
    </nav>

    <img src="assets/themes/dark/hamburger.svg" alt="☰" id="hamburger">
</header>