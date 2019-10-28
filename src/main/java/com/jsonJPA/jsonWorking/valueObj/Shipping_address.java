package com.jsonJPA.jsonWorking.valueObj;

public class Shipping_address {
	private String street_address;

	private String city;

	private String state;

	private String type;

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "ClassPojo [street_address = " + street_address + ", city = " + city + ", state = " + state + ", type = "
				+ type + "]";
	}
}