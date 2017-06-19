<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>REGISTRATION</title>

<link rel="stylesheet" href="./css/controlWifi.css">

<link rel="stylesheet" href="./css/login-form.css">
<link rel="stylesheet" href="./css/header.css">
<script type="text/javascript" src="<%=application.getContextPath()%>/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/js/emailvalidation.js"></script>
<script type="text/javascript" src="<%=application.getContextPath()%>/js/mobNoValidation.js"></script>
<style type="text/css">
label {
	color:red;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
$("#email").change(function(){
		
		$('#email-msg').html("");
			
	});
	
$("#fn").change(function(){
	
	$('#fn-msg').html("");
		
});
$("#password").change(function(){
	
	$('#password-msg').html("");
		
});
$("#age").change(function(){
	
	$('#age-msg').html("");
		
});
$("#mob").change(function(){
	
	$('#mob-msg').html("");
		
});


	
	$("#reg").click(function(){
		
	var email =	$("#email")
var status = emailValidation(email);
	
	
	
	
	if(!status){
		$('#email-msg').html("INVALID EMAIL");
	
	return false;
	}else{
		$('#email-msg').html("");
	}
	var p = $("#password").val();
	
	if(p.length<3){
		$('#password-msg').html("EXPECTING MININUM 3 CHARECTERS");
		
		return false;
	}
	status = mobValidation($("#mob"));
	
	if(!status){
		$('#mob-msg').html("INVALID MOBILE NUMBER");
	
	return false;
	}else{
		$('#mob-msg').html("");
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

<div class='login-logo'>USER REGISTRATION</div>
<form action="RegistrationAction">

<%
/* 
System.out.print(session.getAttribute("ERR"));
System.out.print(session.getAttribute("FAIL"));

System.out.print(session.getAttribute("SUCCESS"));
System.out.print(session.getAttribute("reg"));
 */
if(session.getAttribute("ERR")!=null){
%>
<div style="color:red">${ERR }</div>
<%}else if(session.getAttribute("FAIL")!=null){ %>

<div style='color:red'>${FAIL }</div>
<%} else if (session.getAttribute("SUCCESS")!=null){%>

<div style='color:GREEN'>${SUCCESS}</div>
<%}
 
 
 
 session.removeAttribute("SUCCESS");

 session.removeAttribute("FAIL");
 session.removeAttribute("ERR");
 
 
 
 
 %>



<div class='label'>
FIRST NAME</div>
<div class='input-wrapper'>
<input type="text" name='first-name'  id='fn' placeholder="First Name"  required="required"  value='${reg.name }'>
<label id='fn-msg'></label>
</div>
<div class='label'>
AGE</div>
<div class='input-wrapper'>
<input type="number" name='age' id='age' placeholder="Age" min="15" max="100" required="required" value='${reg.age }'>
<label id='age-msg'></label>
</div>

<div class='label'>
Mobile Number</div>
<div class='input-wrapper'>
<input type="text" name='mobile-number' id='mob' placeholder="Mobile Number"  required="required" value='${reg.mobileNo}'>
<label  id='mob-msg'></label>
</div>



<div class='label'>
EMAIL
</div>
<div class='input-wrapper'>
<input type="text" name='email' id='email' placeholder="email" required="required" value='${reg.email }'>
<label id='email-msg'></label>
</div>

<div class='label'>
PASSWORD
</div>
<div class='input-wrapper'>
<input type="password" name='password' id='password'placeholder="Password" required="required" value='${reg.password}'>
<label id='password-msg'></label>
</div>



<div class='btn-wrapper' style="margin-bottom: 30px;">
<input type="submit"  value="SUBMIT" id='reg'>
</div>



<div class='eu-wrapper'>
<a href="index.jsp">Existing User</a>
</div>


</form>



</div>

</div>
<div class='footer-wrapper'>
</div>

</div>
</body>
</html>