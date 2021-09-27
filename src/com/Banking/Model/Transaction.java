package com.Banking.Model;

public class Transaction {

	private long transactionId;
	private long accountNo;
	private String date;
	private String description;
	private String type;
	private double amount;

	public Transaction() {
		super();
	}

	public Transaction(long transactionId, long accountNo, String date, String description, String type,
			double amount) {
		super();
		this.transactionId = transactionId;
		this.accountNo = accountNo;
		this.date = date;
		this.description = description;
		this.type = type;
		this.amount = amount;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
