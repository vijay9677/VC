<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>

<link rel="stylesheet" href="./css/controlWifi.css">

<link rel="stylesheet" href="./css/header.css">

<link rel="stylesheet" href="./css/menu.css">

<link rel="stylesheet" href="./css/login-form.css">
<script type="text/javascript" src="./js/BackButton.js"></script>
</head>
<body>

<div class='main-wrapper'>


<jsp:include page="header.jsp"></jsp:include>

<div class='content-wrapper'>
<div class='form-wrapper'>
<form action="ConnectionTestAction">
<div class='tc'>
<button class='test-button'> TEST CONNECTION</button>
</div>
<div class='testConnection-msg'>
<div class='status'>STATUS:</div> <div class='status-content'>${SUCCESS} ${FAIL}</div>
<%
session.removeAttribute("FAIL");
session.removeAttribute("SUCCESS");
%>
</div>
</form>
</div>
</div>
<div class='footer-wrapper'>
</div>
</div>

</body>
</html>