package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public class RothIra extends IraAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1961405593280143224L;

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
