<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/base.css">
    <link rel="stylesheet" href="styles/notAuthorized.css">
    <script src="scripts/base.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>
    <img src="assets/images/error.svg" alt="error" id="index">
    <h2>You do not have access to this page!</h2>
</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>

<%@ include file="footer_static.html" %>
</body>
</html>