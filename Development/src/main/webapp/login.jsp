<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/formLogin.css">

    <script src="scripts/base.js"></script>
    <script src="scripts/validateLoginForm.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>

    <div class="error-box" id="error">
        <h1 id="errorText"></h1>
    </div>
    <form action="GET" name="formLogin" id="index">
        <div class="form-content">
            <div class="left-side">
                <h1>Effettua il login</h1>
                <h3>Entra nell'area riservata</h3>
            </div>
            <div class="right-side">
                <div class="form-element">
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username">
                </div>
                <div class="form-element">
                    <label for="password">Password</label>
                    <input type="password" name="password" id="password">
                </div>
                <input type="button" value="Log In" onclick="validateData()" id="submit">
            </div>
        </div>
    </form>

</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.jsp" %>
</body>
</html>