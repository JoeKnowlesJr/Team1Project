package com.meritamerica.onlinebank.models;

import javax.persistence.Entity;

@Entity
public class RothIra extends IraAccount {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1961405593280143224L;
	
	public RothIra(Long num, double bal, double rate, User u) {
		super(num, AccountType.ROTHIRA, bal, rate, u);
	}
}
