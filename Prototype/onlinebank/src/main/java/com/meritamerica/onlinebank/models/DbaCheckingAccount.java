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
	public double deposit(Transaction t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double withdraw(Transaction t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
