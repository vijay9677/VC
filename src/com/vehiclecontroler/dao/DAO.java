package com.vehiclecontroler.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vehiclecontroler.exception.DatabaseException;
import com.vehiclecontroler.exception.InvalidUserException;
import com.vehiclecontroler.exception.RegistrationFailureException;
import com.vehiclecontroler.formbean.LoginBean;
import com.vehiclecontroler.formbean.RegistrationBean;
import com.vehiclecontroler.util.DbUtil;
import com.vehiclecontroler.util.SQLQueryConstants;
import com.vehiclecontroler.util.StringConstants;

public class DAO {
/**
 * 
 * @param rb
 * @throws DatabaseException
 * @throws RegistrationFailureException
 */
	public void registerUser(RegistrationBean rb) throws DatabaseException, RegistrationFailureException {
		
Connection con =	DbUtil.getConnection();
	try {
	PreparedStatement ps = con.prepareStatement(SQLQueryConstants.REGISTER_QUERY);
	ps.setString(1, rb.getEmail());	
	ps.setString(2, rb.getName());	
	ps.setString(3, rb.getMobileNo());	
	ps.setString(4, rb.getPassword());	
	ps.setInt(5, rb.getAge());
	int i = 	ps.executeUpdate();
	System.out.println("i");
if(i==0){
	throw new RegistrationFailureException(StringConstants.REGISTRATION_FAILED);
}

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new DatabaseException(e.getMessage());
	}
	


}


public RegistrationBean login(LoginBean lb) throws DatabaseException, InvalidUserException{
	

Connection con =	DbUtil.getConnection();
	PreparedStatement ps;
	try {
		ps = con.prepareStatement(SQLQueryConstants.LOGIN_QUERY);
		ps.setString(1, lb.getEmail());	
		ps.setString(2, lb.getPassword());
ResultSet rs=		ps.executeQuery();

	if(rs.next()){
		RegistrationBean bean= new RegistrationBean();
		bean.setuId(rs.getInt(1));
bean.setEmail(rs.getString(2));
bean.setPassword(rs.getString(3));
bean.setName(rs.getString(4));
bean.setMobileNo(rs.getString(5));
		bean.setAge(rs.getInt(6));
		return bean;
		
	}else{
		throw new InvalidUserException(StringConstants.INVALID_USER_MSG);
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	throw new DatabaseException(e.getMessage());
	}
	
}


public String forgotPassword(String email) throws DatabaseException, InvalidUserException {
	// TODO Auto-generated method stub

Connection con =	DbUtil.getConnection();
	try {
		PreparedStatement ps = con.prepareStatement(SQLQueryConstants.FORGOT_PASSWORD);
		ps.setString(1, email);
	ResultSet rs =	ps.executeQuery();
		
	if(rs.next()){
		return rs.getString(1);
	}else{
		
		throw new InvalidUserException(StringConstants.EMAIL_NOT_EXIST);
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	throw new DatabaseException(e.getMessage());
	}
	
	
}



}
