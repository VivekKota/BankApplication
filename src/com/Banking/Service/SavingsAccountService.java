package com.Banking.Service;

import java.time.LocalDate;
import java.util.Scanner;

import com.Banking.Database.AccountDao;
import com.Banking.Database.CustomerAccountDao;
import com.Banking.Database.CustomerDao;
import com.Banking.Database.TransactionDao;
import com.Banking.Model.Account;
import com.Banking.Model.Customer;
import com.Banking.Model.SavingsAccount;
import com.Banking.Model.Transaction;
import com.Banking.System.FieldValidation;

public class SavingsAccountService 
{
	Scanner sc=new Scanner(System.in);
	
	FieldValidation fieldValidation = new FieldValidation();
	
	Account account=new Account();
	Transaction transaction=new Transaction();
	SavingsAccount savingsAccount=new SavingsAccount();
	
	CustomerDao customerDao=new CustomerDao();
	AccountDao accountDao=new AccountDao();
	TransactionDao transactionDao=new TransactionDao();
	CustomerAccountDao customerAccountDao=new CustomerAccountDao();
	
	
	public void createSavingsAccount(Customer customer)
	{
		System.out.println("Please enter Minimum deposit amount 3000 RS/- in your Account");
		
		boolean deposit = false;
		
		while (!deposit) 
		{
				
			double amount = fieldValidation.amountValidation() ;
	
			if (amount >= 3000) 
			{
	
				account.setBalance(amount);
				account.setAccountType("SavingsAccount");
				customerDao.addCustomer(customer);
				accountDao.addAccount(account);
	
				transaction.setAccountNo(accountDao.findAccountNo());
				transaction.setDescription("Amount Deposited");
				transaction.setType("Credit");
				transaction.setDate(LocalDate.now().toString());
				transaction.setAmount(amount);
				transactionDao.addTransaction(transaction);
	
				deposit = true;
	
			}
			else
			{
				System.out.println("Please enter Minimum deposit amount 3000 RS/- in your Account");
			}
	
		} 
	}
	
	public void savingsAccount(long customerId) 
	{

		long accountNo = customerAccountDao.findCustomerAcccount(customerId);
		account = accountDao.findAccount(accountNo);
		Customer customer = customerDao.findCustomer(customerId);

		System.out.println();
		System.out.println("Welcome to KOTAK BanK");
		System.out.println();

		System.out.println("Savings Account");
		System.out.println(accountNo);
		System.out.println("-------------------------------------------------------");
		System.out.println("Available Balance");
		System.out.println("Rs " + account.getBalance() + " /-");

		boolean service = false;
		while (!service) 
		{

			System.out.println();
			System.out.println("Dear Customer! Select your choice from our services");

			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. Transfer Money");
			System.out.println("4. Show Account Details");
			System.out.println("5. Show Recent Transactions");
			System.out.println("6. Show Balance");
			System.out.println("7. Log Out");

			int choice = fieldValidation.choiceValidation();
			switch (choice) 
			{

			case 1:

				System.out.println("Enter amount to be deposited");
				double amount = fieldValidation.amountValidation();

				savingsAccount.deposit(account, amount);

				break;

			case 2:

				System.out.println("Enter amount to be withdraw");
				amount = fieldValidation.amountValidation();

				savingsAccount.withdraw(account, amount);

				break;

			case 3:

				System.out.println("Enter Account Number");
				long accountTransferNo = fieldValidation.accountNoValidation();

				System.out.println("Re-enter Account Number");
				long reaccountTransferNo = fieldValidation.accountNoValidation();

				if (accountTransferNo == reaccountTransferNo) {

				
					System.out.println("Enter recipient name");
					fieldValidation.nameFieldValidation(sc.nextLine());

					Account receiverAccount = accountDao.findAccount(accountTransferNo);

					if (receiverAccount != null) {

						System.out.println("Enter amount to be transfered");
						amount = fieldValidation.amountValidation();

						savingsAccount.transfer(account, receiverAccount, amount);

					} else {
						System.out.println("Can't find Account Number");

					}

				} else {
					System.out.println("Account Number does not match");
				}

				break;

			case 4:

				System.out.println();
				System.out.println("Account Holder  : " + customer.getName());
				System.out.println("Account Type    : " + account.getAccountType());
				System.out.println("Account Balance : " + account.getBalance());
				System.out.println("Required Monthly Average Balance : RS 3000/-");

				System.out.println("-----------------------------------------------------------");

				break;

			case 5:

				System.out.println();
				savingsAccount.transactions(accountNo);

				break;

			case 6:

				System.out.println();
				System.out.println("Available Balance : "+account.getBalance());

				break;

			case 7:

				System.out.println();
				System.out.println("Thank you for using KOTAK Bank");
				System.out.println("-----------------------------------------------------------");

				service = true;
				
			default :
				System.out.println("Please Select one of choices above");
				System.out.println("-----------------------------------------------------------");
			}

		}
	}


}
