package com.jsonJPA.jsonWorking.valueObj.aggregated;

import java.util.Set;

public class AggregatedPayload {
	private String firstName;

	private String lastName;

	private Set<String> address;

	private String gender;

	private Set<String> name;

	private Set<String> lamp;

	private Set<String> company;

	private Set<String> dragon;

	private String email;

	private Set<String> fulfillmentText;

	private Set<String> tags;
	
	private Set<ImagingAgg> imaging;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getAddress() {
		return address;
	}

	public void setAddress(Set<String> address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Set<String> getName() {
		return name;
	}

	public void setName(Set<String> name) {
		this.name = name;
	}

	public Set<String> getLamp() {
		return lamp;
	}

	public void setLamp(Set<String> lamp) {
		this.lamp = lamp;
	}

	public Set<String> getCompany() {
		return company;
	}

	public void setCompany(Set<String> company) {
		this.company = company;
	}

	public Set<String> getDragon() {
		return dragon;
	}

	public void setDragon(Set<String> dragon) {
		this.dragon = dragon;
	}

	public Set<ImagingAgg> getImaging() {
		return imaging;
	}

	public void setImaging(Set<ImagingAgg> imaging) {
		this.imaging = imaging;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getFulfillmentText() {
		return fulfillmentText;
	}

	public void setFulfillmentText(Set<String> fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "AggregatedPayload [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", gender=" + gender + ", name=" + name + ", lamp=" + lamp + ", company=" + company + ", dragon="
				+ dragon + ", imaging=" + imaging + ", email=" + email + ", fulfillmentText=" + fulfillmentText
				+ ", tags=" + tags + "]";
	}
	
}
