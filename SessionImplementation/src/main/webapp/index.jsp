<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="false"%>
<html>
<head>
    <title>My JSP</title>
</head>
<body>
<h1>Hello World!</h1>
<br/>
<% if ( (request.getAttribute("formNeeded") != null) && (request.getAttribute("updated") == null)) { %>
<%@ include file="/CookieBanner.html" %>
<% } %>
</body>
</html> 