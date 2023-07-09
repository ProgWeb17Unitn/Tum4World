<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width,initial-scale=1.0">
  <title>Tum4World</title>
  <link rel="icon" type="image/x-icon" href="assets/images/icon.svg">
  <link rel="stylesheet" href="styles/base.css">
  <link rel="stylesheet" href="styles/logOut.css">
  <script src="scripts/base.js"></script>
  <script src="scripts/logOut.js"></script>
</head>

<body>
<%@ include file="header.jsp" %>
<main>

  <div class="flexbox-container" id="index">

    <div class="logOutRectangle">
      <div class="title"> Vuoi eseguire il LogOut?</div>
      <form class="scelta">
        <button type="button" name="opzione"  value="Si" id="Si" onclick="esci()">Si</button>
        <button type="button" name="opzione" value="No" id="No">No</button>
      </form>
    </div>

  </div>
</main>
<% if ((request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="cookieBanner.html" %>
<% } %>
<%@ include file="footer.html" %>
</body>
</html>