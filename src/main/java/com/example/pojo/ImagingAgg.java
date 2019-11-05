package com.example.pojo;

import java.util.Set;

public class ImagingAgg {

	private String policyNumber;

	private Set<String> name;

	private Set<String> time;

	private Set<String> location;

	private Set<Clm_fldsAgg> clm_flds;

	public ImagingAgg() {
	}

	public ImagingAgg(String policyNumber, Set<String> name, Set<String> time, Set<String> location,
			Set<Clm_fldsAgg> clm_flds) {
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

	public Set<String> getName() {
		return name;
	}

	public void setName(Set<String> name) {
		this.name = name;
	}

	public Set<String> getTime() {
		return time;
	}

	public void setTime(Set<String> time) {
		this.time = time;
	}

	public Set<String> getLocation() {
		return location;
	}

	public void setLocation(Set<String> location) {
		this.location = location;
	}

	public Set<Clm_fldsAgg> getClm_flds() {
		return clm_flds;
	}

	public void setClm_flds(Set<Clm_fldsAgg> clm_flds) {
		this.clm_flds = clm_flds;
	}

	@Override
	public String toString() {
		return "ImagingAgg [policyNumber=" + policyNumber + ", name=" + name + ", time=" + time + ", location="
				+ location + ", clm_flds=" + clm_flds + "]";
	}

}
