package com.meritamerica.onlinebank.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="accounts")
public abstract class Account implements Serializable {
	private static final long serialVersionUID = -6931308272159535229L;
	public static final Long CASH = -1111111L;
	public static final Long CHECK = -2222222L;

	/**
	 * Base class for all account types
	 */
	
	protected Long acctId;
	protected Long acctNumber;
	protected AccountType acctType;
	protected double acctBalance;
	protected double acctRate;	
	protected User user;
	private List<Transaction> transactions;
	protected Date acctCreated;
	protected Date acctUpdated;
	
	protected Account() {}
	protected Account(Long num, AccountType type, double bal, double rate, User u) {
		acctNumber = num;
		acctType = type;
		acctBalance = bal;
		acctRate = rate;
		user = u;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="acct_id", unique=true, nullable=false, updatable=false)
	public Long getId() { return acctId; }
	public void setId(Long l) { acctId = l; }
	
	@Column(name="acct_number", unique=true, nullable=false, updatable=false)
	public Long getAccountNumber() { return acctNumber; }
	public void setAccountNumber(Long num) { acctNumber = num; }
	
	@Column(name="acct_type")
	public AccountType getAccountType() { return acctType; }
	public void setAccountType(AccountType acctType) { this.acctType = acctType; }
	
	@Column(name="acct_balance")
	public double getBalance() { return acctBalance; }
	protected void setBalance(double amt) {
		// TODO make accessible to elevated permissions only
		acctBalance = amt;
	}
	
	@Column(name="acct_rate")
	public double getRate() { return acctRate; }
	public void setRate(double r) {
		// TODO make accessible to elevated permissions only
		acctRate = r; 
	}
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	public User getUser() { return user; }
	public void setUser(User user) { this.user = user; }
	
    @OneToMany(mappedBy="account", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Transaction> getTransactions() {
    	if (transactions == null) transactions = new ArrayList<>(); 
    	return transactions;
    }
	public void setTransactions(List<Transaction> transactions) {
		if (transactions == null) transactions = new ArrayList<>();
		this.transactions = transactions;
	}
	
	@Temporal(TemporalType.DATE)
    @Column(name = "acct_created")
    public Date getCreated() { return acctCreated; }
    public void setCreated(Date created) {this.acctCreated = created; }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "acct_updated")
    public Date getUpdated() { return acctUpdated; }
    public void setUpdated(Date updated) {this.acctUpdated = updated; }
    
	public double deposit(Transaction t) {		// Returns new balance
		if (t.getType() != TransactionType.DEPOSIT) return acctBalance;
		
		acctBalance += t.getAmount();
		return acctBalance;
	}
	public double withdraw(Transaction t) {		// "	"	"	"	"
		if (t.getType() != TransactionType.WITHDRAWL) return acctBalance;
		acctBalance -= t.getAmount();
		return acctBalance;		
	}
	
	@PrePersist
	protected void onCreate() { this.acctCreated = new Date(); }
	
	@PreUpdate
	protected void onUpdate() { this.acctUpdated = new Date(); }

	@Override
	public String toString() {
		String tName = acctType.getTypeName();
		String s = String.format("%s (%04d)", tName, acctNumber % 10000); 
		return s;
	}
	
	public static boolean isValid(Account a) {
		boolean retVal = true;
		if (a == null) return false;
		return retVal;
	}
	
	public boolean compareTo(Account a) {
		if (a == null) return false;
		Long a1 = a.getAccountNumber();
		Long a2 = this.acctNumber;
		return a1 == a2;
	}
	
	protected void saveTransaction(Transaction t) {
		getTransactions().add(t);
	}
	
}