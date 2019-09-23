package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public class RegularIra extends IraAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3937417131149123919L;
	
	public RegularIra() {}
	public RegularIra(Long num, double bal, double rate, User u) {
		super(num, AccountType.REGIRA, bal, rate, u);
	}
}
