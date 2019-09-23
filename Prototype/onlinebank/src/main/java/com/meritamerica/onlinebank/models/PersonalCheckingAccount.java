package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public class PersonalCheckingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = -939724507990212066L;
	
	public PersonalCheckingAccount() {}
	public PersonalCheckingAccount(Long num, double bal, double rate, User u) {
		super(num, AccountType.CHECKING, bal, rate, u);
	}
}
