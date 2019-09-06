package com.meritamerica.onlinebank.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashModel {
	private NewAccountFormObject nafo;
	private TransactionFormObject tfo;
	private User user;
	private Account account;
	private boolean failed;
	private String error;
	
	public DashModel() {
		nafo = new NewAccountFormObject();
		tfo = new TransactionFormObject();
		user = new User();
		account = new SavingsAccount();
		failed = false;
		error = "";
	}
	
	public NewAccountFormObject getNafo() { return nafo; }
	public void setNafo(NewAccountFormObject nafo) { this.nafo = nafo; }
	public TransactionFormObject getTfo() { return tfo; }
	public void setTfo(TransactionFormObject tfo) { this.tfo = tfo; }
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	public Account getAccount() { return account; }
	public void setAccount(Account account) { this.account = account; }
	public boolean isFailed() { return failed; }
	public void setFailed(boolean failed) { this.failed = failed; }
	public String getError() { return error; }
	public void setError(String error) { this.error = error; }
	
	public List<String> getTransactionTypes() {
		return new ArrayList<String>(Arrays.asList(
			"Withdrawl",
			"Deposit",
			"Transfer"
		));
	}
}
