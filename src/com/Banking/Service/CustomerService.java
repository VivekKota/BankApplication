package com.Banking.Service;

import java.util.Scanner;

import com.Banking.Database.AccountDao;
import com.Banking.Database.CustomerAccountDao;
import com.Banking.Database.CustomerDao;
import com.Banking.Model.Account;
import com.Banking.Model.Customer;
import com.Banking.System.FieldValidation;

public class CustomerService {
	Scanner sc = new Scanner(System.in);

	FieldValidation fieldValidation = new FieldValidation();

	Account account = new Account();
	Customer customer = new Customer();

	SavingsAccountService savingsAccountService = new SavingsAccountService();
	CurrentAccountService currentAccountService = new CurrentAccountService();

	CustomerDao customerDao = new CustomerDao();
	CustomerAccountDao customerAccountDao = new CustomerAccountDao();
	AccountDao accountDao = new AccountDao();

	public void logIn() {

		boolean login = false;

		while (!login) {

			System.out.println("Enter Customer Id");
			long customerId = fieldValidation.customerIdValidation();

			customer = customerDao.findCustomer(customerId);

			if (customer != null) {

				System.out.println("Enter Password");
				String password = sc.next();

				if (password.equals(customer.getPassword())) {

					login = true;
					long accountNo = customerAccountDao.findCustomerAcccount(customerId);
					account = accountDao.findAccount(accountNo);

					if (account.getAccountType().equalsIgnoreCase("SavingsAccount")) {

						savingsAccountService.savingsAccount(customerId);
					}

					else {

						currentAccountService.currentAccount(customerId);
					}

				} else {

					System.out.println("Entered Credentils are Invalid");
				}

			} else {

				System.out.println("Customer Id not found");
			}
		}

	}

}
