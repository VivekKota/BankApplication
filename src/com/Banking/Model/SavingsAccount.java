package com.Banking.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Banking.Database.AccountDao;
import com.Banking.Database.CustomerDao;
import com.Banking.Database.TransactionDao;

public class SavingsAccount implements Bank {

	Account account = new Account();
	Customer customer = new Customer();

	AccountDao accountDao = new AccountDao();
	CustomerDao customerDao = new CustomerDao();
	TransactionDao transactionDao = new TransactionDao();

	LocalDate localdate;
	double amount, balance, transactionAmount;
	String description, type, date;

	@Override
	public void deposit(long accountNo, double amount) {

		if (amount > 0) {

			account = accountDao.findAccount(accountNo);
			balance = account.getBalance();

			balance += amount;
			accountDao.deposit(accountNo, balance);

			System.out.printf("Amount %.2f deposited%n", amount);
			System.out.printf("Current Balance is: %.2f%n", balance);

			description = "Amount Deposited";
			type = "Credit";
			localdate = LocalDate.now();
			date = localdate.toString();
			transactionDao.addTransaction(account.getAccountNo(), date, description, type, amount);

		} else {
			System.out.println("A negative amount cannot be deposited!");
		}

		System.out.println("-----------------------------------------------------------");
	}

	@Override
	public void withdraw(long accountNo, double amount) {

		localdate = LocalDate.now();
		date = localdate.toString();
		type = "Debit";

		if (amount > 0) {

			if (amount <= 25000) {

				transactionAmount = transactionDao.findTransactionsAmount(accountNo, date, type);

				if (transactionAmount + amount <= 200000) {

					account = accountDao.findAccount(accountNo);
					balance = account.getBalance();

					if ((amount) <= balance) {

						balance -= amount;
						accountDao.withdraw(accountNo, balance);

						System.out.printf("Amount of %.2f withdrawn from Account%n", amount);
						System.out.printf("Current Balance is: %.2f%n", balance);

						description = "Amount Withdrawn";
						type = "Debit";
						localdate = LocalDate.now();
						date = localdate.toString();
						transactionDao.addTransaction(account.getAccountNo(), date, description, type, amount);

					} else {

						System.out.println("Insufficients funds in your bank account");
						System.out.printf("Current Balance is: %.2f%n", balance);
					}

				} else {

					System.out.println("Transaction Limit per day : 200000 rs/- only");
				}

			} else {

				System.out.println("Limit Per one Transaction : 25000 Rs/- only");
			}

		} else {
			System.out.println("A Negative amount cannot be withdrawn!");

		}

		System.out.println("-----------------------------------------------------------");
	}

	@Override
	public void transfer(long accountNo, long accountTransferNo, double amount) {

		localdate = LocalDate.now();
		date = localdate.toString();
		type = "Debit";

		if (amount > 0) {

			if (amount <= 25000) {

				transactionAmount = transactionDao.findTransactionsAmount(accountNo, date, type);

				if (transactionAmount + amount <= 200000) {

					account = accountDao.findAccount(accountNo);
					balance = account.getBalance();

					if ((amount) <= balance) {

						balance -= amount;
						accountDao.withdraw(accountNo, balance);

						System.out.printf("Amount of %.2f transferred from your Account%n", amount);
						System.out.printf("Current Balance is: %.2f%n", balance);

						description = "Amount Transferred to "+ accountTransferNo ;
						type = "Debit";
						localdate = LocalDate.now();
						date = localdate.toString();
						transactionDao.addTransaction(account.getAccountNo(), date, description, type, amount);

						account = accountDao.findAccount(accountTransferNo);
						balance = account.getBalance();

						balance += amount;
						accountDao.deposit(accountTransferNo, balance);

						description = "Amount Transferred by " + accountNo;
						type = "Credit";
						localdate = LocalDate.now();
						date = localdate.toString();
						transactionDao.addTransaction(account.getAccountNo(), date, description, type, amount);

					} else {

						System.out.println("Insufficients funds in your bank account");
						System.out.printf("Current Balance is: %.2f%n", balance);
					}

				} else {

					System.out.println("Transaction Limit per day : 200000 rs/- only");
				}

			} else {

				System.out.println("Limit Per one Transaction : 25000 Rs/- only");
			}

		} else {
			System.out.println("A Negative amount cannot be withdrawn!");
		}

		System.out.println("-----------------------------------------------------------");

	}

	@Override
	public void transactions(long accountNo) {

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions = transactionDao.findTransactions(accountNo);

		System.out.println("TransactionId     Date          Description           Type    Amount");

		for (int i = 0; i < transactions.size(); i++) {

			System.out.println(transactions.get(i).getTransactionId() + "              "
					+ transactions.get(i).getDate() + "   " + transactions.get(i).getDescription() + "    "
					+ transactions.get(i).getType() + "    " + transactions.get(i).getAmount());
		}

		System.out.println("-----------------------------------------------------------");
	}

	@Override
	public void accountDetails(long accountNo, long customerId) {

		customer = customerDao.findCustomer(customerId);
		account = accountDao.findAccount(accountNo);

		System.out.println("Account Holder :" + customer.getName());
		System.out.println("Account Type : " + account.getAccountType());
		System.out.println("Account Balance : " + account.getBalance());

		if (account.getAccountType().equalsIgnoreCase("SavingsAccount")) {
			System.out.println("Required Monthly Average Balance : RS 3000/-");

		} else {
			System.out.println("Required Monthly Average Balance : RS 10000/-");
		}

		System.out.println("-----------------------------------------------------------");
	}

	@Override
	public void balance(long accountNo) {

		account = accountDao.findAccount(accountNo);
		System.out.println("Account Balance : " + account.getBalance());

		System.out.println("-----------------------------------------------------------");

	}

}
