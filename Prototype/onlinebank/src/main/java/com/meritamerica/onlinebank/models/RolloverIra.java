package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public class RolloverIra extends IraAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8395839170601401733L;
	
	public RolloverIra(Long num, double bal, double rate, User u) {
		super(num, AccountType.ROLLIRA, bal, rate, u);
	}
}
