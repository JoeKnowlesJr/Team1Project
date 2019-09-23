package com.meritamerica.onlinebank.models;

public enum TransactionType {
	WITHDRAWL("Withdrawl"),
	DEPOSIT("Deposit"),
	TRANSFER("Transfer"),
	ACCRUE("Accrue"),
	INVALID("Invalid");
	
    private final String typeName;

    private TransactionType(String value) {
        typeName = value;
    }

    public String getTypeName() {
        return typeName;
    }
    
    public String[] getUserTypes() {
    	return new String[]{"Withdrawl", "Deposit", "Transfer"};
    }
}		      
