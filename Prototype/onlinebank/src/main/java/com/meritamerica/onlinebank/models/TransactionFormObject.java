package com.meritamerica.onlinebank.models;

import com.meritamerica.onlinebank.models.TransactionType;

public class TransactionFormObject {
	private TransactionType type;
	private String tType;
	private Long tAcct;
	private Long oAcct;
	private Account account;
	private double amount;
	
	public TransactionType getType() { return type; }	
	public void setType(TransactionType type) { this.type = type; }
	public String gettType() { return tType; }
	public void settType(String tType) {
		this.tType = tType;
		switch (tType) {
			case "Withdrawl":
				type = TransactionType.WITHDRAWL;
				break;
			case "Deposit":
				type = TransactionType.DEPOSIT;
				break;
			case "Transfer":
				type = TransactionType.TRANSFER;
				break;
		}		
	}
	public Long gettAcct() { return tAcct; }
	public void settAcct(Long tAcct) { this.tAcct = tAcct; }
	public Long getoAcct() { return oAcct; }
	public void setoAcct(Long oAcct) { this.oAcct = oAcct; }
	public Account getAccount() { return account; }
	public void setAccount(Account account) { this.account = account; }
	public double getAmount() { return amount; }
	public void setAmount(double amount) { this.amount = amount; }
	public Transaction getTransaction() {
		if (type == TransactionType.DEPOSIT) {
			return new Transaction(type, amount, oAcct, tAcct, account);
		}
		return new Transaction(type, amount, tAcct, oAcct, account);
	}
}
