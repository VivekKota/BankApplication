package com.Banking.Model;

public interface Bank {
	
	void deposit(Account account, double amount);
	void withdraw(Account account, double amount);
	void transfer(Account sender, Account receiver, double amount);
	void transactions(long accountNo);
}
