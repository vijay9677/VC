/**
 * 
 */
function mobValidation( obj ) {
 var mob=	obj.val();
var MOB_REGEXP = new RegExp(/^[7-9]{1}[0-9]{9}$/); 	

 return MOB_REGEXP.test(mob); 
}