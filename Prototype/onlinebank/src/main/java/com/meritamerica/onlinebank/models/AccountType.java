package com.meritamerica.onlinebank.models;


public enum AccountType {
	Closed,
	Savings,
	Checking,
	DbaChecking,
	CD,
	RegIra,
	RothIra,
	RollIra,
	NonAcct;
	
    private AccountType() {}
	
	public static String[] getTypes() {
		return new String[] {
			"Savings",
			"Per Chk",
			"Dba Chk",
			"  C D  ",
			"Reg Ira",
			"RothIra",
			"RollIra"
		};			      
	}
}
