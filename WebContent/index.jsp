<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>vehicle controlling with wifi</title>
<link rel="stylesheet" href="<%=application.getContextPath()%>/css/controlWifi.css">
<link rel="stylesheet" href="<%=application.getContextPath()%>/css/login-form.css">
<link rel="stylesheet" href="<%=application.getContextPath()%>/css/header.css">
<script type="text/javascript" src="<%=application.getContextPath()%>/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/js/emailvalidation.js"></script>

<script type="text/javascript" src="./js/BackButton.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#login").click(function(){
		
	var email =	$("#email")
var status = emailValidation(email);
	$("#email").change(function(){
		
		$('#un-msg').html("");
			
	});
	if(!status){
		$('#un-msg').html("INVALID EMAIL");
	
	return false;
	}else{
		$('#un-msg').html("");
	}
	var p = $("#password").val();
	
	if(p.length<3){
		$('#pwd-msg').html("EXPECTING MININUM 3 CHARECTERS");
		
		return false;
	}
	
	
	
	
	return true;
	
	});
	
	
});


</script>
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

<%

if(session.getAttribute("SUCCESS")!=null)
{
%>

<div ><span style='color:green; font-size: large;'> ${SUCCESS}</span></div>

<%}else if(session.getAttribute("FAIL")!=null){ %>
<div ><span style='color:red; font-size: large;'> ${FAIL}</span></div>


<%
}
session.removeAttribute("SUCCESS");
session.removeAttribute("FAIL");

 %>

<div class='login-logo'>LOGIN FORM</div>
<form action="LoginAction">
<div class='label'>
EMAIL
</div>
<div class='input-wrapper'>
<input type="text" name='email' id='email'placeholder="email"  required="required">
<label  class='err-msg' id='un-msg'></label>
</div>

<div class='label'>
PASSWORD
</div>
<div class='input-wrapper'>
<input type="password" name='password'id='password' placeholder="password" required="required">
<label  class='err-msg' id='pwd-msg'></label>
</div><!-- 
<div class='rem-me'>
<input type="checkbox" id='rm'>
Remember Me
</div> -->
<div class='btn-wrapper'>
<input type="submit" id='login' value="LOGIN">
</div>
<div class='input-wrapper fp'>
<div class='fp-wrapper'>
<a href="ForgotPassword.jsp">Forgot Password ?</a>
</div>
<div class='nu-wrapper'>
<a href="Registration.jsp">New User</a>
</div>
</div>

</form>
</div>
</div>

<div class='footer-wrapper'>
</div>

</div>
</body>
</html>