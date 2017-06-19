package com.vehiclecontroler.util;


public class SQLQueryConstants {
	


	private SQLQueryConstants(){
		
	}
public static final String REGISTER_QUERY="INSERT INTO tbl_user  (email,name,mobileno,password,age) VALUES(?,?,?,?,?)"; 
public static final String LOGIN_QUERY="select  tbl_user.userId,tbl_user.email,tbl_user.password,tbl_user.name,tbl_user.mobileno,tbl_user.age from tbl_user where tbl_user.email=? and tbl_user.password=?";
public static final String FORGOT_PASSWORD = "select password from tbl_user where email=?";

}