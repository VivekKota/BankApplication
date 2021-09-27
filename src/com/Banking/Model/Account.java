package com.Banking.Model;

public class Account {

	private long accountNo;
	private double balance;
	private String accountType;

	public Account() {
		super();
	}

	public Account(long accountNo, double balance, String accountType) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.accountType = accountType;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
