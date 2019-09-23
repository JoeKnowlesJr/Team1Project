package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public class CdAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3216320281308730492L;
	
	public CdAccount() {}
	public CdAccount(Long num, double bal, double rate, User u) {
		super(num, AccountType.CD, bal, rate, u);
	}
}
