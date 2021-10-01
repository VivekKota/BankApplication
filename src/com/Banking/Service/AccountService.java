package com.Banking.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Banking.Database.AccountDao;
import com.Banking.Database.CustomerAccountDao;
import com.Banking.Database.CustomerDao;
import com.Banking.Model.Customer;
import com.Banking.System.FieldValidation;

public class AccountService {

	Scanner sc = new Scanner(System.in);

	FieldValidation fieldValidation = new FieldValidation();
	
	SavingsAccountService savingsAccountService = new SavingsAccountService();
	CurrentAccountService currentAccountService = new CurrentAccountService();
	
	AccountDao accountDao = new AccountDao();
	CustomerDao customerDao = new CustomerDao();
	CustomerAccountDao customerAccountDao = new CustomerAccountDao();

	public void createAccount() {

		

		long customerId, accountNo;
		String password, name, dob, panCard, address, mobileNo;

		System.out.println("Enter your Name");
		name = fieldValidation.nameFieldValidation(sc.nextLine());

		System.out.println("Enter your Date Of Birth(DD/MM/YYYY)");
		dob = fieldValidation.dobFieldValidation(sc.nextLine());

		System.out.println("Enter your Mobile Number");
		mobileNo = fieldValidation.mobileNoValidation(sc.nextLine());

		System.out.println("Enter your PAN Card Number");
		panCard = fieldValidation.panCardValidation(sc.nextLine());

		System.out.println("Enter your address");
		address = fieldValidation.addressValidation(sc.nextLine());

		System.out.println("Dear customer please create password for your account");
		password = fieldValidation.passwordValidation(sc.nextLine());

		Customer customer = new Customer(password, name, dob, mobileNo, panCard, address);

		System.out.println("Selct account type and enter your choice");
		System.out.println("1. Savings");
		System.out.println("2. Current");

		boolean create = false;
		do {

			try {

				switch (sc.nextInt()) {

				case 1:
					savingsAccountService.createSavingsAccount(customer);
					create = true;
					break;

				case 2:
					currentAccountService.createCurrentAccount(customer);
					create = true;
					break;

				default:
					System.out.println("Invalid Choice...Please enter valid choice (1/2)");

				}

			} catch (InputMismatchException e) {
				System.out.println("Please enter Valid Choice(1/2)");
			}
			sc.nextLine();

		} while (!create);

		accountNo = accountDao.findAccountNo();
		customerId = customerDao.findCustomerId();
		customerAccountDao.addCustomerAccount(customerId, accountNo);

		System.out.println("Dear customer Please make a note of below for future use:");
		System.out.println("Customer Id    : " + customerId);
		System.out.println("Account Number : " + accountNo);
	}

}
