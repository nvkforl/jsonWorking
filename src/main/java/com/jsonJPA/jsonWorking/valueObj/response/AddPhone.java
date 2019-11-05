package com.jsonJPA.jsonWorking.valueObj.response;

public class AddPhone {

	private String phone;
	private String cfg = "R";
	
	public AddPhone(String phone) {
		this.phone = phone;
		this.cfg = cfg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCfg() {
		return cfg;
	}

	public void setCfg(String cfg) {
		this.cfg = cfg;
	}

	
}
