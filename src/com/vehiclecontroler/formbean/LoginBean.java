package com.vehiclecontroler.formbean;

public class LoginBean {
	private String email;
	private String password;
	
	public LoginBean() {
		// TODO Auto-generated constructor stub

	}

	public String getEmail() {

		
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginBean [email=" + email + ", password=" + password + "]";
	}

	
}
