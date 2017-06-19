<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>CONTROLER</title>

<link rel="stylesheet" href="./css/controlWifi.css">

<link rel="stylesheet" href="./css/login-form.css">
<link rel="stylesheet" href="./css/header.css">

<link rel="stylesheet" href="./css/menu.css">
<script type="text/javascript" src="./js/jquery-2.1.1.js"></script>
<script type="text/javascript">
$("document").ready(function(){
	
	$("#status").html("WAITING FOR REQUEST");
	
	
	
});



document.onkeydown = function(e) {
   
	//debugger;
	switch (e.keyCode) {
        case 37:
            //alert('left');
        
	console.log("LEFT");
            makeRequest("LEFT");
            break;
        case 38:

        	console.log("UP");
            //alert('up');
            makeRequest("FORWARD");
            
            break;
        case 39:

        	console.log("RIGHT");
        	makeRequest("RIGHT");
            
            //alert('right');
            break;
        case 40:

        	console.log("REVERSE");
        	makeRequest("REVERSE");
            
            //alert('down');
            break;
    }
};

setInterval(function(){ 
makeRequest("STOP");
}, 1000);

 

var n = new Date().getTime();


function makeRequest(cmd){

	var time= new Date().getTime();
	//alert("TIME : "+time);
//	alert("n : "+n);
	var diff = time-n;
	//alert(diff);

	if(diff>2000 || cmd =="STOP"){
		n=time;
		$.ajax({
		"url":"ControllerAction",
		data:{"command":cmd},
		"async": false,
		
		"success":function(data){
			//alert(data);

	
var div = $("<div>");
			if(data!='null'){
			div.append(data);
			}else{
				div.append("DEEVICE IS NOT CONNECTED PLEASE TRY LATER");
					div.css("color","#f96767");
			}
			$("#status").html(div);
		},
		"error": function(){
			alert("Please try later");
		}
		
	});
	}
	
}
</script>

</head>
<body>
<div class='main-wrapper'>

<!-- <div class='header-wrapper'>
<div class='header-content'>
<div class='logo-wrapper'><img class='float-logo'alt="" src="./image/V-LOGO.png" width="60px">
<font class='EHICLE'>EHICLE &nbsp</font>
</div>
<div class='logo-wrapper'>
<img  class='float-logo'alt="" src="./image/C.png" height="60px"> </div>
<font class='EHICLE'>ONTROLLING</font>
</div>

</div><div class='extra-padding'>
</div>

<div class='menu-wrapper'>
<div class='nav-wrapper'>
<ul class='main-menu'>
<li><a href="#">HOME</a></li>
<li><a href="#">ABOUT US</a></li>
<li><a href="#">CONTACT US</a></li>
</ul>
</div>
<div class='logout'>
<ul class='main-menu'>
<li><a href="#">LogOut</a></li>
</ul>
</div>
</div> -->


<jsp:include page="header.jsp"></jsp:include>
<div class='content-wrapper'>

<div class='distance-msg'>
<div class='status' id='status'>DISTANCE FROM OBSTACLE:</div> <!-- <div class='status-content'>0.1 m from obstacle</div> -->
</div>
<div class='movement-wraper'>
<!-- <div class='form-wrapper'>
<button class='front-button'>MOVE FRONT</button>
<button class='left-button'>MOVE LEFT</button>
<button class='right-button'>MOVE RIGHT</button>
<button class='back-button'>MOVE BACK</button>
</div> -->
 <div class='form-wrapper'>
<div id="joystick" style="width:80%">
  <svg width="100%" height="100%" viewBox="0 0 100 100">
    <defs>
      <linearGradient id="grad1" x1="0%" y1="0%" x2="0%"y2="100%">
        <stop offset="0%" style="stop-color:rgb(16,16,16);stop-opacity:1" />
        <stop offset="100%" style="stop-color:rgb(240,240,240);stop-opacity:1" />
      </linearGradient>
      <linearGradient id="grad2" x1="0%" y1="0%" x2="0%" y2="100%">
        <stop offset="0%" style="stop-color:rgb(240,240,240);stop-opacity:1" />
        <stop offset="100%" style="stop-color:rgb(16,16,16);stop-opacity:1" />
      </linearGradient>
      <linearGradient id="grad3" x1="0%" y1="0%" x2="0%" y2="100%">
        <stop offset="0%" style="stop-color:rgb(168,168,168);stop-opacity:1" />
        <stop offset="100%" style="stop-color:rgb(239,239,239);stop-opacity:1" />
      </linearGradient>
    </defs>
    <circle cx="50" cy="50" r="50" fill="url(#grad1)" />
    <circle cx="50" cy="50" r="47" fill="url(#grad2)" stroke="black" stroke-width="1.5px" />
    <circle cx="50" cy="50" r="44" fill="url(#grad3)" />
    <circle cx="50" cy="50" r="20" fill="#cccccc" stroke="black" stroke-width="1px" onclick="makeRequest('STOP')" />
    <path d="M50,14 54,22 46,22Z" fill="rgba(0,0,0,0.8)" onclick="makeRequest('FORWARD');" />
    <path d="M50,86 54,78 46,78Z" fill="rgba(0,0,0,0.8)" onclick="makeRequest('REVERSE');" />
    <path d="M14,50 22,54 22,46Z" fill="rgba(0,0,0,0.8)" onclick="makeRequest('LEFT');" />
    <path d="M86,50 78,54 78,46Z" fill="rgba(0,0,0,0.8)" onclick="makeRequest('RIGHT');" />
  </svg>
</div>
</div> 
</div>
</div>


<div class='footer-wrapper'>
</div>

</div>
</body>
</html>

