package com.vehiclecontroler.service;

import java.io.IOException;

import com.vehiclecontroler.dao.DAO;
import com.vehiclecontroler.exception.DatabaseException;
import com.vehiclecontroler.exception.InvalidUserException;
import com.vehiclecontroler.exception.MailException;
import com.vehiclecontroler.exception.RegistrationFailureException;
import com.vehiclecontroler.formbean.LoginBean;
import com.vehiclecontroler.formbean.RegistrationBean;
import com.vehiclecontroler.util.IPUTILS;
import com.vehiclecontroler.util.SendMailUtil;

public class Services {
	/**
	 * 
	 * @param rb
	 * @throws DatabaseException
	 * @throws RegistrationFailureException
	 */
	public void registerUser(RegistrationBean rb) throws DatabaseException, RegistrationFailureException {
		DAO dao = new DAO();
		dao.registerUser(rb);
	}
	
	
	
/**
 * 
 * @param lb
 * @return
 * @throws DatabaseException
 * @throws InvalidUserException
 */
public RegistrationBean login(LoginBean lb) throws DatabaseException, InvalidUserException{
DAO dao = new DAO();
return dao.login(lb);
}



public Boolean testConn() {
	// TODO Auto-generated method stub
	ClientUDP clientUDP = new ClientUDP();
	return clientUDP.startCommanding("TEST",IPUTILS.ip,IPUTILS.portInt);
		
}



public String controler(String input) {
	// TODO Auto-generated method stub
	

	ClientUDP clientUDP = new ClientUDP();
	clientUDP.startCommanding(input,IPUTILS.ip, IPUTILS.portInt);
	
	return clientUDP.getStatusController();
}


/**
 * 
 * @param email
 * @return
 * @throws DatabaseException
 * @throws InvalidUserException
 * @throws MailException 
 */
public Boolean forgotPassword(String email) throws DatabaseException, InvalidUserException, MailException {
	// TODO Auto-generated method stub

	DAO dao = new DAO();
	String password = dao.forgotPassword(email);
	System.out.println(password);
SendMailUtil mailUtil= new SendMailUtil();
	mailUtil.sendGmail(email, "VEHICLE CONTROLLER : FORGOT PASSWORD REQUEST", "YOUR PASSWORD IS : "+password);
	return true;

	
	
}

}
