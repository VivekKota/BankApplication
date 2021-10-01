package com.Banking.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Banking.Database.AccountDao;
import com.Banking.Database.CustomerDao;
import com.Banking.Database.TransactionDao;

public class CurrentAccount implements Bank {

	Account account = new Account();
	Customer customer = new Customer();
	Transaction transaction=new Transaction();

	AccountDao accountDao = new AccountDao();
	CustomerDao customerDao = new CustomerDao();
	TransactionDao transactionDao = new TransactionDao();

	LocalDate localdate;
	double amount, balance, transactionAmount;
	String description, type, date;

	@Override
	public void deposit(Account account, double amount) 
	{

		if (amount > 0) 
		{
			
			account.setBalance(account.getBalance()+amount);
			accountDao.updateBalance(account);

			System.out.printf("Amount %.2f deposited%n", amount);
			System.out.printf("Current Balance is: %.2f%n", account.getBalance());

			transaction.setAccountNo(account.getAccountNo());
			transaction.setDescription("Amount Deposited");
			transaction.setType("Credit");
			transaction.setDate(LocalDate.now().toString());
			transaction.setAmount(amount);
			transactionDao.addTransaction(transaction);

		} 
		else 
		{
			System.out.println("A negative amount cannot be deposited!");
		}

		System.out.println("-----------------------------------------------------------");
	}

	@Override
	public void withdraw(Account account, double amount) {

		localdate = LocalDate.now();
		date = localdate.toString();
		type = "Debit";

		if (amount > 0) {

			if (amount <= 200000) {

				transactionAmount = transactionDao.findTransactionsAmount(account.getAccountNo(), date, type);

				if (transactionAmount + amount <= 600000) {

					if ((amount) <= account.getBalance()) {

						account.setBalance(account.getBalance()-amount);
						accountDao.updateBalance(account);

						System.out.printf("Amount of %.2f withdrawn from Account%n", amount);
						System.out.printf("Current Balance is: %.2f%n",account.getBalance());

						transaction.setAccountNo(account.getAccountNo());
						transaction.setDescription("Amount Withdrawn");
						transaction.setType("Debit");
						transaction.setDate(LocalDate.now().toString());
						transaction.setAmount(amount);
						transactionDao.addTransaction(transaction);

					} else {

						System.out.println("Insufficients funds in your bank account");
						System.out.printf("Current Balance is: %.2f%n", balance);
					}

				} else {

					System.out.println("Transaction Limit per day : 600000 rs/- only");
				}

			} else {

				System.out.println("Limit Per one Transaction : 200000 Rs/- only");
			}

		} else {
			System.out.println("A Negative amount cannot be withdrawn!");

		}

		System.out.println("-----------------------------------------------------------");
	}

	@Override
	public void transfer(Account sender, Account receiver, double amount) {

		localdate = LocalDate.now();
		date = localdate.toString();
		type = "Debit";

		if (amount > 0) {

			if (amount <= 200000) {

				transactionAmount = transactionDao.findTransactionsAmount(sender.getAccountNo(), date, type);

				if (transactionAmount + amount <= 600000) {


					if ((amount) <= sender.getBalance()) {

						sender.setBalance(sender.getBalance()-amount);
						accountDao.updateBalance(sender);
						
						receiver.setBalance(receiver.getBalance()+amount);
						accountDao.updateBalance(receiver);

						System.out.printf("Amount of %.2f transferred from your Account%n", amount);
						System.out.printf("Current Balance is: %.2f%n", sender.getBalance());
						
						transaction.setAccountNo(sender.getAccountNo());
						transaction.setDescription("Amount Transferred to "+ receiver.getAccountNo());
						transaction.setType("Debit");
						transaction.setDate(LocalDate.now().toString());
						transaction.setAmount(amount);
						transactionDao.addTransaction(transaction);

						transaction.setAccountNo(receiver.getAccountNo());
						transaction.setDescription("Amount received from "+ sender.getAccountNo());
						transaction.setType("Credit");
						transaction.setDate(LocalDate.now().toString());
						transaction.setAmount(amount);
						transactionDao.addTransaction(transaction);
						
					} else {

						System.out.println("Insufficients funds in your bank account");
						System.out.printf("Current Balance is: %.2f%n", balance);
					}

				} else {

					System.out.println("Transaction Limit per day : 600000 rs/- only");
				}

			} else {

				System.out.println("Limit Per one Transaction : 200000 Rs/- only");
			}

		} else {
			System.out.println("A Negative amount cannot be Transferred!");
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

}
