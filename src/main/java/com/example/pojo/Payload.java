package com.example.pojo;

import java.util.Set;

public class Payload {

	private String firstname;
	
	private String lastname;
	
	private String ssn;
	
	private Set<String> relatives;
	
	private Set<String> namesOfRelatives;
	
	private Set<Imaging> imaging;

	public Payload(String firstname, String lastname, String ssn, Set<String> relatives, Set<String> namesOfRelatives,
			Set<Imaging> imaging) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.ssn = ssn;
		this.relatives = relatives;
		this.namesOfRelatives = namesOfRelatives;
		this.imaging = imaging;
	}

	public void setRelatives(Set<String> relatives) {
		this.relatives = relatives;
	}

	public void setNamesOfRelatives(Set<String> namesOfRelatives) {
		this.namesOfRelatives = namesOfRelatives;
	}

	public void setImaging(Set<Imaging> imaging) {
		this.imaging = imaging;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Set<String> getRelatives() {
		return relatives;
	}

	public Set<String> getNamesOfRelatives() {
		return namesOfRelatives;
	}

	public Set<Imaging> getImaging() {
		return imaging;
	}

	@Override
	public String toString() {
		return "Payload [firstname=" + firstname + ", lastname=" + lastname + ", ssn=" + ssn + ", relatives="
				+ relatives + ", namesOfRelatives=" + namesOfRelatives + ", imaging=" + imaging + "]";
	}

	

	
	
	
}
