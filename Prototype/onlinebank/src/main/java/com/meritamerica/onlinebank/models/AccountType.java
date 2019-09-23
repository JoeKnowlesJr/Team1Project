package com.meritamerica.onlinebank.models;


public enum AccountType {
	CLOSED("Closed"),
	SAVINGS("Savings"),
	CHECKING("Checking"),
	DBACHECK("DbaChecking"),
	CD("CD"),
	REGIRA("RegIra"),
	ROTHIRA("RothIra"),
	ROLLIRA("RollIra"),
	NONACCT("NonAcct");
	
    private final String typeName;

    private AccountType(String value) {
        typeName = value;
    }

    public String getTypeName() {
        return typeName;
    }
	
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
