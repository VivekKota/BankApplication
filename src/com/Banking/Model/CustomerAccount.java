package com.Banking.Model;

public class CustomerAccount {

	private long customerId;
	private long accountNo;

	public CustomerAccount() {
		super();
	}

	public CustomerAccount(long customerId, long accountNo) {
		super();
		this.customerId = customerId;
		this.accountNo = accountNo;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

}
