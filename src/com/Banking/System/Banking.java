package com.Banking.System;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Banking.Database.DbConnection;
import com.Banking.Service.AccountService;
import com.Banking.Service.CustomerService;

public class Banking {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		AccountService accountService = new AccountService();
		CustomerService customerService = new CustomerService();
		
		boolean done = false;

		try {

			do {

				try {

					System.out.println("Enter your choice");
					System.out.println("1. Create Acccount");
					System.out.println("2. Login");
					System.out.println("3. Exit");

					switch (sc.nextInt()) {
					case 1:
						accountService.createAccount();
						break;

					case 2:
						customerService.logIn();
						break;

					case 3:
						System.out.println("-------------------Exiting the Application----------------------");
						done = true;
						break;

					default:
						System.out.println("Please enter Valid choice(1/2)");

					}
				} catch (InputMismatchException e) {
					System.out.println("Please enter Valid Choice(1/2)");
				}
				sc.nextLine();

			} while (!done);

		} catch (Exception e) {

			System.out.println("Error in main block");
			e.printStackTrace();

		}

		finally {

			sc.close();
			DbConnection.close();
		}

	}

}
