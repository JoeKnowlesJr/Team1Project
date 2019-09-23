package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public class DbaCheckingAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = 650757587410348209L;
	
	public DbaCheckingAccount() {}
	public DbaCheckingAccount(Long num, double bal, double rate, User u) {
		super(num, AccountType.DBACHECK, bal, rate, u);
	}
	
	@Override
	public String toString() {
		String s = String.format("%s (%04d)", "DbaChk", acctNumber % 10000); 
		return s;
	}
}
