package com.jsonJPA.jsonWorking.valueObj;

public class ResponceInnerStr {

	private String phone;
	private String cfg = "R";
	
	public ResponceInnerStr(String phone) {
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
