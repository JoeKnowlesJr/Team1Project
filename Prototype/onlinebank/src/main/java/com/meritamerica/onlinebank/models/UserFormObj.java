package com.meritamerica.onlinebank.models;

public class UserFormObj {
	
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String line1;
	private String line2;
	private String city;
	private String state;
	private String zip;
	
	public UserFormObj() {}
	public UserFormObj(String firstName, String lastName, String email, String password, String line1, String line2, String city, String state, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.line1 = line1;
		this.line2 = line2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getLine1() { return line1; }
	public void setLine1(String line1) { this.line1 = line1; }
	public String getLine2() { return line2; }
	public void setLine2(String line2) { this.line2 = line2; }
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	public String getState() { return state; }
	public void setState(String state) { this.state = state; }
	public String getZip() { return zip; }
	public void setZip(String zip) { this.zip = zip; }
	public User getUser() { return new User(firstName, lastName, email, password, new Address(line1, line2, city, state, zip)); }
}
