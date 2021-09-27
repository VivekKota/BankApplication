package com.Banking.Model;

public interface Bank {
	
	void balance(long accountNo);
	void deposit(long accountNo, double amount);
	void withdraw(long accountNo, double amount);
	void transfer(long accountNo, long accountTransferNo, double amount);
	void transactions(long accountNo);
	void accountDetails(long accountNo, long customerId);

}
