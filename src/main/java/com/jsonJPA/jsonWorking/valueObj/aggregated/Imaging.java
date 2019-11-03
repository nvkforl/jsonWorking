package com.jsonJPA.jsonWorking.valueObj.aggregated;

import java.util.Set;

public class Imaging {
	
	private String policyNumber;

	private String name;

	private String location;

	private String time;
	
	private Set<Clm_flds> clm_flds;

	public Set<Clm_flds> getClm_flds() {
		return clm_flds;
	}

	public void setClm_flds(Set<Clm_flds> clm_flds) {
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

	@Override
	public String toString() {
		return "Imaging [clm_flds=" + clm_flds + ", policyNumber=" + policyNumber + ", name=" + name + ", location="
				+ location + ", time=" + time + "]";
	}

}