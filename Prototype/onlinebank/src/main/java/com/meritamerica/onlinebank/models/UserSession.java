package com.meritamerica.onlinebank.models;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class UserSession {
	
	private User user;
	private Date login;
	
	public UserSession(User u, Date l) { user = u; login = l; }
	
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	public String getLogin() { return login.toString(); }
	public void setLogin(Date login) { this.login = login; } 
}
