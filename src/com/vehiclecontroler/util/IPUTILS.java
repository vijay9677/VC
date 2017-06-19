package com.vehiclecontroler.util;

import java.io.IOException;
import java.util.Properties;

public class IPUTILS {
public static String ip;
public static Integer portInt;
	private IPUTILS() {
		// TODO Auto-generated constructor stub
	
	
	}
	static {
		
		Properties p = new Properties();
		try {
			p.load(DbUtil.class.getClassLoader().getResourceAsStream("UDP.properties"));
			ip = p.getProperty("ipaddress");
			String PORT = p.getProperty("port");
	 portInt = Integer.parseInt(PORT);
		} catch (IOException e) {
			// dblogger.error(DBConstants.PROP_LOAD_ERR_MSG);
		}

		
	}
	public static void main(String[] args) {
		System.out.println(ip);
	}
	
}
