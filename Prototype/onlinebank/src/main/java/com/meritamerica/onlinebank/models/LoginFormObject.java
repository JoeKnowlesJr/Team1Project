package com.meritamerica.onlinebank.models;

public class LoginFormObject {
	private String email;
	private String password;
	private boolean failed;
	
	public LoginFormObject() {}
	public LoginFormObject(String e, String p) {
		email = e;
		password = p;
		failed = false;
	}
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public boolean isFailed() { return failed; }
	public void setFailed(boolean failed) { this.failed = failed; }
}
