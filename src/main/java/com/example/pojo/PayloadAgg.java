package com.example.pojo;

import java.util.Set;

public class PayloadAgg {
	private Set<String> firstname;

	private Set<String> lastname;

	private Set<String> ssn;

	private Set<String> relatives;

	private Set<String> namesOfRelatives;

	private Set<ImagingAgg> imaging;

	public PayloadAgg() {
	}
	

	public PayloadAgg(Set<String> firstname, Set<String> lastname, Set<String> ssn, Set<String> relatives,
			Set<String> namesOfRelatives, Set<ImagingAgg> imaging) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.ssn = ssn;
		this.relatives = relatives;
		this.namesOfRelatives = namesOfRelatives;
		this.imaging = imaging;
	}



	public Set<String> getFirstname() {
		return firstname;
	}

	public void setFirstname(Set<String> firstname) {
		this.firstname = firstname;
	}

	public Set<String> getLastname() {
		return lastname;
	}

	public void setLastname(Set<String> lastname) {
		this.lastname = lastname;
	}

	public Set<String> getSsn() {
		return ssn;
	}

	public void setSsn(Set<String> ssn) {
		this.ssn = ssn;
	}

	public Set<String> getRelatives() {
		return relatives;
	}

	public void setRelatives(Set<String> relatives) {
		this.relatives = relatives;
	}

	public Set<String> getNamesOfRelatives() {
		return namesOfRelatives;
	}

	public void setNamesOfRelatives(Set<String> namesOfRelatives) {
		this.namesOfRelatives = namesOfRelatives;
	}

	public Set<ImagingAgg> getImaging() {
		return imaging;
	}

	public void setImaging(Set<ImagingAgg> imaging) {
		this.imaging = imaging;
	}

	@Override
	public String toString() {
		return "PayloadAgg [firstname=" + firstname + ", lastname=" + lastname + ", ssn=" + ssn + ", relatives="
				+ relatives + ", namesOfRelatives=" + namesOfRelatives + ", imaging=" + imaging + "]";
	}

}
