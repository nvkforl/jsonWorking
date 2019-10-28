package com.jsonJPA.jsonWorking.valueObj;

public class PayLoad {
	private String firstName;

	private String lastName;

	private String gender;

	private String phone;

	private String[] name;

	private String[] lamp;

	private String[] company;

	private String[] dragon;

	private Imaging[] imaging;

	private String email;

	private String[] fulfillmentText;

	private String[] tags;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public String[] getLamp() {
		return lamp;
	}

	public void setLamp(String[] lamp) {
		this.lamp = lamp;
	}

	public String[] getCompany() {
		return company;
	}

	public void setCompany(String[] company) {
		this.company = company;
	}

	public String[] getDragon() {
		return dragon;
	}

	public void setDragon(String[] dragon) {
		this.dragon = dragon;
	}

	public Imaging[] getImaging() {
		return imaging;
	}

	public void setImaging(Imaging[] imaging) {
		this.imaging = imaging;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String[] getFulfillmentText() {
		return fulfillmentText;
	}

	public void setFulfillmentText(String[] fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "ClassPojo [firstName = " + firstName + ", lastName = " + lastName + ", gender = " + gender
				+ ", phone = " + phone + ", name = " + name + ", lamp = " + lamp + ", company = " + company
				+ ", dragon = " + dragon + ", imaging = " + imaging + ", email = " + email + ", fulfillmentText = "
				+ fulfillmentText + ", tags = " + tags + "]";
	}
}