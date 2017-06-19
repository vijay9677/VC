/**
 * 
 */

function emailValidation( obj ) {
 var email=	obj.val();
var EMAIL_REGEXP = new RegExp(/^[a-z]{1}[a-z0-9._-]{3,}[@]{1}[a-z 0-9]{2,}[.]{1}[a-z0-9]{2,}$/); 	

 return EMAIL_REGEXP.test(email); 
}