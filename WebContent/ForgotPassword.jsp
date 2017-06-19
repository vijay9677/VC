
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>vehicle controlling with wifi</title>
<link rel="stylesheet" href="./css/controlWifi.css">
<link rel="stylesheet" href="./css/login-form.css">
<link rel="stylesheet" href="./css/header.css">
<style type="text/css">
#goback{
color:white;
margin-top:5px;
border: 1px solid white;
border-radius:5px;
background-color: gray; 
width:100%;
height:40px;
}

</style>

</head>
<body>
<div class='main-wrapper'>

<div class='header-wrapper'>
<div class='header-content'>
<div class='logo-wrapper'><img class='float-logo'alt="" src="./image/V-LOGO.png" width="60px">
<font class='EHICLE'>EHICLE &nbsp</font>
</div>
<div class='logo-wrapper'>
<img  class='float-logo'alt="" src="./image/C.png" height="60px"> </div>
<font class='EHICLE'>ONTROLLING</font>
</div>

</div>

<div class='menu-wrapper'>
</div>

<div class='content-wrapper'>
<div class='form-wrapper'>
<div class='login-logo'>Forgot Password Form</div>
<form action="ForgotPasswordAction">
<div class='label'>
EMAIL
</div>
<div class='input-wrapper'>
<input type="text" name='email' placeholder="email"  required="required">
<label  class='err-msg' id='un-msg'></label>
</div>
<div class='btn-wrapper'>
<input type="submit"  value="forgot Password">

<input type="button" id='goback'  value="GO TO LOGIN">

</div>
</form>
</div>
</div>

<div class='footer-wrapper'>
</div>

</div>
</body>
</html>