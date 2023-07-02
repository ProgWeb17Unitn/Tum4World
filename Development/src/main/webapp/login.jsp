<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/validateLoginForm.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>


<form action="GET" name="formLogin" id="index">
    <p>username</p>
    <input type="text" name="username">
    <p>password</p>
    <input type="password" name="password"><br><br>
    <input type="button" value="Log In" onclick="validateData()"><br><br>
</form>

<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer.html" %>
</body>
</html>