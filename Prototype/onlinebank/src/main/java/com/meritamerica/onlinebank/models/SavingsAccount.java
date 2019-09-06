package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public class SavingsAccount extends Account {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2365020447976085700L;
	
	public SavingsAccount() {}
	public SavingsAccount(Long num, double bal, double rate, User u) {
		super(num, AccountType.SAVINGS, bal, rate, u);
	}

	@Override
	public double deposit(Transaction t) {
		acctBalance += t.getAmount();
		saveTransaction(t);
		return acctBalance;
	}

	@Override
	public double withdraw(Transaction t) {
		acctBalance -= t.getAmount();
		saveTransaction(t);
		return acctBalance;
	}

}
