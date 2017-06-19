package com.vehiclecontroler.util;



/**
 * This class contains declaration of all the constants , which are going
 * to be used in Data Access Object classes.
 */
public class DBConstants {
	private DBConstants(){
		
	}

	/*
	 *The name of the properties file which contains 
	 *the database connection parameters. 
	 */
	public static final String PROP_PATH_MYSQL= "mysql.properties";
	
	/*
	 * Properties file key for database user name.
	 */
	public static final String PROP_USERNAME = "username";
	
	/*
	 * Properties file key for database password.
	 */
	public static final String PROP_PASSWORD = "password";
	
	/*
	 * Properties file key for database driver class name.
	 */
	public static final String PROP_DRIVER = "driver";
	
	/*
	 * Properties file key for database connection url name.
	 */
	public static final String PROP_URL = "url";
	
	/*
	 * Error message to be logged in logger when unable to load driver class.
	 */
	public static final String DRIVER_LOAD_ERR_MSG = "Unable to load Driver class";
	
	/*
	 * Error message to be logged in logger when failed to load 
	 * the properties file containing the database connection parameters.
	 */
	public static final String PROP_LOAD_ERR_MSG = "Unable to read database properties file";
	
	/*
	 * Error message to be logged in logger when failed to get 
	 * the database connection.
	 */
	public static final String GET_CONN_ERR = "Unable to create database connection";
	
	/*
	 * Error message to be logged in logger when failed to close 
	 * the database connection.
	 */
	public static final String CONN_CLOSE_ERR = "unable to close connection";
	
	/*
	 * Error message to be logged in logger when failed to close 
	 * the statement.
	 */
	public static final String STATEMENT_CLOSE_ERR = "Statement Closing Error in ConnectionUtil";
	
	/*
	 * Error message to be logged in logger when failed to close
	 * the ResultSet
	 */
	public static final String RESULTSET_CLOSE_ERR = "Error in closing the resultset";
	
	/*
	 * Error message to be logged in logger when failed to close
	 * the ResultSet
	 */
	public static final String DATABASE_INTERNAL_ERR = "Error in Database Server. Please try Later";
	
}


