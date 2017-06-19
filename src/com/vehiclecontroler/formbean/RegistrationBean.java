package com.vehiclecontroler.formbean;

import java.io.Serializable;

public class RegistrationBean implements Serializable{

	private Integer uId;
	private String name;
	private String email;
	private String password;
	private String mobileNo;
	private Integer age;
	
	
	public Integer getuId() {
		return uId;
	}
	public void setuId(Integer uId) {
		this.uId = uId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "RefistrationBean [uId=" + uId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", mobileNo=" + mobileNo + ", age=" + age + "]";
	}
	
	
	
	
}
