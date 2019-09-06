package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public abstract class IraAccount extends Account {

	/**
	 * Base class for IRA accounts
	 */
	private static final long serialVersionUID = 7490901450866126887L;
	
	public IraAccount() {}
	public IraAccount(Long num, AccountType type, double bal, double rate, User u) {
		super(num, type, bal, rate, u);
	}
}
