<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>Tum4World</title>
    <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
    <link rel="stylesheet" href="styles/layouts/base.css">
    <script src="scripts/base.js"></script>
    <script src="scripts/skel.js"></script> <!-- usa questo format per il tuo js-->
    <script src="scripts/validateLoginForm.js"></script>
    <!--- Inserisci qui i tuoi file css--->

</head>

<body>
<%@ include file="static/header.jsp" %>
<!--- Inserisci qui il tuo codice HTML
      NOTA BEME: il div esterno deve avere un id="index"--->


<form action="GET" name="formLogin">
    <p>username</p>
    <input type="text" name="username">
    <p>password</p>
    <input type="text" name="password"><br><br>
    <input type="button" value="Log In" onclick="validateData()"><br><br>
</form>

<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="static/cookieBanner.html" %>
<% } %>

<%@ include file="static/footer.html" %>
</body>

<script>
    page.load();
</script>
</html>