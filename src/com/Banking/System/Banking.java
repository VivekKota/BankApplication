package com.Banking.System;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Banking.Database.AccountDao;
import com.Banking.Database.CustomerAccountDao;
import com.Banking.Database.CustomerDao;
import com.Banking.Database.DbConnection;
import com.Banking.Database.TransactionDao;
import com.Banking.Model.Account;
import com.Banking.Model.CurrentAccount;
import com.Banking.Model.Customer;
import com.Banking.Model.SavingsAccount;

public class Banking {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		try {

			Account account = new Account();
			Customer customer = new Customer();
			CurrentAccount currentAccount = new CurrentAccount();
			SavingsAccount savingsAccount = new SavingsAccount();

			AccountDao accountDao = new AccountDao();
			CustomerDao customerDao = new CustomerDao();
			TransactionDao transactionDao = new TransactionDao();
			CustomerAccountDao customerAccountDao = new CustomerAccountDao();

			int choice;
			LocalDate localdate;
			double amount, balance = 0;
			long customerId, mobileNo, accountNo, accountTransferNo, reaccountTransferNo;
			String password, name, dob, panCard, address, accountType, description, type, date;

			boolean done = false;
			while (!done) {

				System.out.println("Enter your choice");
				System.out.println("1. Create Acccount");
				System.out.println("2. Login");

				choice = sc.nextInt();
				if (choice == 1) {

					System.out.println("Enter your Name");
					name = sc.next();

					System.out.println("Enter your Date Of Birth(DD/MM/YYYY)");
					dob = sc.next();

					System.out.println("Enter your Mobile Number");
					mobileNo = sc.nextLong();
					Pattern p = Pattern.compile("[0-9]{10}");
					Matcher m;

					boolean mobileFormat = false;
					while (!mobileFormat) {

						m = p.matcher(Long.toString(mobileNo));
						if (!m.matches()) {

							System.out.println("Please enter  valid mobile Number");
							mobileNo = sc.nextLong();

						} else {
							mobileFormat = true;
						}
					}

					System.out.println("Enter your PAN Card Number");
					panCard = sc.next();
					p = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

					boolean panFormat = false;
					while (!panFormat) {

						m = p.matcher(panCard);
						if (!m.matches()) {

							System.out.println("Please enter valid PAN number Ex:ABCDE0000F");
							panCard = sc.next();

						} else {
							panFormat = true;
						}
					}

					System.out.println("Enter your address");
					address = sc.next();

					System.out.println("Selct account type and enter your choice");
					System.out.println("1. Savings");
					System.out.println("2. Current");
					choice = sc.nextInt();

					boolean deposit = false;
					while (!deposit) {

						if (choice == 1) {

							System.out.println("Please enter Minimum deposit amount 3000 RS/- in your Account");
							amount = sc.nextDouble();

							if (amount >= 3000) {

								balance = amount;
								accountType = "SavingsAccount";
								customerDao.addCustomer(name, dob, mobileNo, panCard, address);
								accountDao.addAccount(balance, accountType);

								accountNo = accountDao.findAccountNo();
								description = "Amount Deposited";
								type = "Credit";
								localdate = LocalDate.now();
								date = localdate.toString();
								transactionDao.addTransaction(accountNo, date, description, type, amount);

								deposit = true;

							} else {
								System.out.println("Enter valid amount to create an Account");
							}

						} else {

							System.out.println("Please enter Minimum deposit amount 10000 RS/- in your Account");
							amount = sc.nextDouble();

							if (amount >= 10000) {

								balance = amount;
								accountType = "CurrentAccount";
								customerDao.addCustomer(name, dob, mobileNo, panCard, address);
								accountDao.addAccount(balance, accountType);

								accountNo = accountDao.findAccountNo();
								description = "Amount Deposited";
								type = "Credit";
								localdate = LocalDate.now();
								date = localdate.toString();
								transactionDao.addTransaction(accountNo, date, description, type, amount);

								deposit = true;

							} else {
								System.out.println("Enter valid amount to create an Account");
							}
						}
					}

					accountNo = accountDao.findAccountNo();
					customerId = customerDao.findCustomerId();
					customerAccountDao.addCustomerAccount(customerId, accountNo);

					System.out.println("Dear customer Please make a note of below for future use:");
					System.out.println("Customer Id    : " + customerId);
					System.out.println("Account Number : " + accountNo);

					System.out.println("Dear customer please create password for your account");
					password = sc.next();

					customerDao.updatePassword(customerId, password);
					System.out.println("Thank you for creating an account in KOTAK Bank");

					System.out.println("Do you want to Continue(Y/N)");
					String exit = sc.next();

					if (exit.equalsIgnoreCase("N")) {
						done = true;
					}

				} else if (choice == 2) {

					boolean login = false;
					while (!login) {

						System.out.println("Enter Customer Id");
						customerId = sc.nextLong();
						customer = customerDao.findCustomer(customerId);

						if (customer != null) {

							System.out.println("Enter Password");
							password = sc.next();

							if (password.equals(customer.getPassword())) {

								login = true;
								accountNo = customerAccountDao.findCustomerAcccount(customerId);
								account = accountDao.findAccount(accountNo);
								balance = account.getBalance();

								if (account.getAccountType().equalsIgnoreCase("SavingsAccount")) {

									System.out.println();
									System.out.println("Welcome to KOTAK BanK");
									System.out.println();

									System.out.println("Savings Account");
									System.out.println(accountNo);
									System.out.println("-------------------------------------------------------");
									System.out.println("Available Balance");
									System.out.println("Rs " + balance + " /-");

									boolean service = false;
									while (!service) {

										System.out.println();
										System.out.println("Dear Customer! Select your choice from our services");

										System.out.println("1. Deposit");
										System.out.println("2. Withdraw");
										System.out.println("3. Transfer Money");
										System.out.println("4. Show Account Details");
										System.out.println("5. View Transactions");
										System.out.println("6. Show Balance");
										System.out.println("7. Log Out");

										choice = sc.nextInt();
										switch (choice) {

										case 1:

											System.out.println("Enter amount to be deposited");
											amount = sc.nextDouble();

											savingsAccount.deposit(accountNo, amount);

											break;

										case 2:

											System.out.println("Enter amount to be withdraw");
											amount = sc.nextDouble();

											savingsAccount.withdraw(accountNo, amount);

											break;

										case 3:

											System.out.println("Enter Account Number");
											accountTransferNo = sc.nextLong();

											System.out.println("Re-enter Account Number");
											reaccountTransferNo = sc.nextLong();

											if (accountTransferNo == reaccountTransferNo) {

												System.out.println("Enter recipient name");
												name = sc.next();

												account = accountDao.findAccount(accountTransferNo);

												if (account != null) {

													System.out.println("Enter amount to be transfered");
													amount = sc.nextDouble();

													savingsAccount.transfer(accountNo, accountTransferNo, amount);

												} else {
													System.out.println("Can't find Account Number");

												}

											} else {
												System.out.println("Account Number does not match");
											}

											break;

										case 4:

											System.out.println();
											savingsAccount.accountDetails(accountNo, customerId);

											break;

										case 5:

											System.out.println();
											savingsAccount.transactions(accountNo);

											break;

										case 6:

											System.out.println();
											savingsAccount.balance(accountNo);

											break;

										case 7:

											System.out.println();
											System.out.println("Thank you for using KOTAK Bank");

											service = true;
											done = true;

											break;

										}

									}

								} else {

									System.out.println();
									System.out.println("Welcome to KOTAK BanK");
									System.out.println();

									System.out.println("Current Account");
									System.out.println(accountNo);
									System.out.println("-------------------------------------------------------");
									System.out.println("Available Balance");
									System.out.println("Rs " + balance + " /-");

									boolean service = false;
									while (!service) {

										System.out.println();
										System.out.println("Dear Customer! Select your choice from our services");

										System.out.println("1. Deposit");
										System.out.println("2. Withdraw");
										System.out.println("3. Transfer Money");
										System.out.println("4. Show Account Details");
										System.out.println("5. View Transactions");
										System.out.println("6. Show Balance");
										System.out.println("7. Log Out");

										choice = sc.nextInt();
										switch (choice) {

										case 1:

											System.out.println("Enter amount to be deposited");
											amount = sc.nextDouble();

											currentAccount.deposit(accountNo, amount);

											break;

										case 2:

											System.out.println("Enter amount to be withdraw");
											amount = sc.nextDouble();

											currentAccount.withdraw(accountNo, amount);

											break;

										case 3:

											System.out.println("Enter Account Number");
											accountTransferNo = sc.nextLong();

											System.out.println("Re-enter Account Number");
											reaccountTransferNo = sc.nextLong();

											if (accountTransferNo == reaccountTransferNo) {

												System.out.println("Enter recipient name");
												name = sc.next();

												account = accountDao.findAccount(accountTransferNo);

												if (account != null) {

													System.out.println("Enter amount to be transfered");
													amount = sc.nextDouble();

													currentAccount.transfer(accountNo, accountTransferNo, amount);

												} else {
													System.out.println("Can't find Account Number");

												}

											} else {
												System.out.println("Account Number does not match");
											}

											break;

										case 4:

											System.out.println();
											currentAccount.accountDetails(accountNo, customerId);

											break;

										case 5:

											System.out.println();
											currentAccount.transactions(accountNo);

											break;

										case 6:

											System.out.println();
											savingsAccount.balance(accountNo);

											break;

										case 7:

											System.out.println();
											System.out.println("Thank you for using KOTAK Bank");

											service = true;
											done = true;

											break;

										}

									}
								}

							} else {
								System.out.println("Entered Credentils are Invalid");
							}

						} else {
							System.out.println("Enter Valid Customer Id");
						}
					}

				} else {
					System.out.println("Please Enter Correct Choice ");
				}
			}

		} catch (Exception e) {

			System.out.println("Error in main block");
			e.printStackTrace();

		} finally {

			sc.close();
			DbConnection.close();
		}

	}

}
