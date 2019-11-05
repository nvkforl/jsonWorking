package com.example.pojo;

import java.util.Set;

public class Imaging {

	private String policyNumber;

	private String name;

	private String time;

	private String location;

	private Set<Clm_flds> clm_flds;

	public Imaging() {
	}

	public Imaging(String policyNumber, String name, String time, String location, Set<Clm_flds> clm_flds) {
		this.policyNumber = policyNumber;
		this.name = name;
		this.time = time;
		this.location = location;
		this.clm_flds = clm_flds;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Set<Clm_flds> getClm_flds() {
		return clm_flds;
	}

	public void setClm_flds(Set<Clm_flds> clm_flds) {
		this.clm_flds = clm_flds;
	}

	@Override
	public String toString() {
		return "Imaging [policyNumber=" + policyNumber + ", name=" + name + ", time=" + time + ", location=" + location
				+ ", clm_flds=" + clm_flds + "]";
	}
}
