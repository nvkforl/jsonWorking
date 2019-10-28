package com.jsonJPA.jsonWorking.valueObj;

public class Imaging {
	private String name;

	private String location;

	private String time;

	private Shipping_address[] shipping_address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Shipping_address[] getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(Shipping_address[] shipping_address) {
		this.shipping_address = shipping_address;
	}

	@Override
	public String toString() {
		return "ClassPojo [name = " + name + ", location = " + location + ", time = " + time + ", shipping_address = "
				+ shipping_address + "]";
	}
}