package com.Banking.System;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldValidation {

	Scanner sc = new Scanner(System.in);

	Pattern p;
	Matcher m;

	public String nameFieldValidation(String name) {

		p = Pattern.compile("^[a-zA-z]+([\\s]+[a-zA-Z]+)*$");

		boolean nameMatcher = false;
		while (!nameMatcher) {

			m = p.matcher(name);

			if (name.length() <= 25 && name.length() >= 5) {

				if (!m.matches()) {

					System.out.println("Please enter Valid Name Ex: Vivek Kota");
					name = sc.nextLine();

				} else {

					nameMatcher = true;
				}

			} else {

				System.out.println("UserName must be 5 and 25 characters long. Please enter valid Name:");
				name = sc.nextLine();

			}
		}
		return name;

	}

	public String dobFieldValidation(String dob) {

		p = Pattern.compile("[0-9]{2}[/][0-9]{2}[/][0-9]{4}");

		String date = LocalDate.now().toString();
		int ddNow = Integer.parseInt(date.substring(8, 10));
		int mmNow = Integer.parseInt(date.substring(5, 7));
		int yyNow = Integer.parseInt(date.substring(0, 4));

		boolean dateFormat = false;
		while (!dateFormat) {

			m = p.matcher(dob);

			if (m.matches()) {

				int dd = Integer.parseInt(dob.substring(0, 2));
				int mm = Integer.parseInt(dob.substring(3, 5));
				int yy = Integer.parseInt(dob.substring(6, 10));

				if (yy >= 1900 && yy <= yyNow && dd >= 1 && dd <= 31 && mm >= 1 && mm <= 12) {

					if ((yy == yyNow && mm > mmNow) || (yy == yyNow && mm == mmNow && dd > ddNow)

							|| ((dd == 31 && mm == 2) || (dd == 31 && mm == 4) || (dd == 31 && mm == 6)
									|| (dd == 31 && mm == 9) || (dd == 31 && mm == 11) || (dd == 30 && mm == 2))

							|| (dd == 29 && mm == 2 && (!((yy % 4 == 0) && ((yy % 400 == 0) || (yy % 100 != 0)))))) {

						System.out.println("Please enter valid date(DD/MM/YYYY)");
						dob = sc.nextLine();

					} else {

						dateFormat = true;
					}

				} else {

					System.out.println("Please enter valid date(DD/MM/YYYY)");
					dob = sc.nextLine();

				}

			} else {

				System.out.println("Please enter valid date(DD/MM/YYYY)");
				dob = sc.nextLine();
			}

		}
		return dob;

	}

	public String mobileNoValidation(String mobileNo) {
		p = Pattern.compile("[6-9]{1}[0-9]{9}");

		boolean mobileFormat = false;
		while (!mobileFormat) {

			m = p.matcher(mobileNo);
			if (!m.matches()) {

				System.out.println("Please enter valid 10 digit mobile Number");
				mobileNo = sc.nextLine();

			} else {
				mobileFormat = true;
			}
		}

		return mobileNo;
	}

	public String panCardValidation(String panCard) {
		p = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

		boolean panFormat = false;
		while (!panFormat) {

			m = p.matcher(panCard);
			if (!m.matches()) {

				System.out.println("Please enter valid PAN number Ex:ABCDE0000F");
				panCard = sc.nextLine();

			} else {
				panFormat = true;
			}
		}

		return panCard;
	}

	public String addressValidation(String address) {

		p = Pattern.compile("^[a-zA-z0-9#.,-/]+([\\s]+[a-zA-z0-9#.,-/]+)*$");

		boolean addressMatcher = false;
		while (!addressMatcher) {

			m = p.matcher(address);

			if (address.length() <= 100) {

				if (!m.matches()) {

					System.out.println("Please enter Valid Address");
					address = sc.nextLine();

				} else {

					addressMatcher = true;
				}

			} else {

				System.out.println("Max Length for Address is 100. Please enter valid Address:");
				address = sc.nextLine();

			}
		}

		return address;
	}

	public String passwordValidation(String password) {

		p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$");

		boolean passwordMatcher = false;
		while (!passwordMatcher) {

			m = p.matcher(password);

			if (!m.matches()) {

				System.out.println(
						"Please enter Valid Password( Use 8 min, 16 max characters with a mix of letters, numbers & symbols )");
				password = sc.nextLine();

			} else {

				passwordMatcher = true;
			}

		}

		return password;

	}

	public double amountValidation() {

		double amount = 0;
		boolean value = false;

		do {

			try {

				amount = Double.parseDouble(sc.nextLine());
				value = true;

			} catch (Exception e) {

				System.out.println("Please enter valid amount");
			}

		} while (!value);

		return amount;
	}

	public long customerIdValidation() {

		long customerId = 0;
		boolean value = false;

		do {

			try {

				customerId = Long.parseLong(sc.nextLine());
				value = true;

			} catch (Exception e) {

				System.out.println("Enter valid CustomerID(6 digit)");
			}

		} while (!value);

		return customerId;
	}

	public long accountNoValidation() {

		long accountNo = 0;
		boolean value = false;

		do {

			try {

				accountNo = Long.parseLong(sc.nextLine());
				value = true;

			} catch (Exception e) {
				System.out.println("Enter valid accountNo(9 digit)");
			}

		} while (!value);

		return accountNo;
	}

	public int choiceValidation() {

		int choice = 0;
		boolean value = false;

		do {

			try {

				choice = Integer.parseInt(sc.nextLine());
				value = true;

			} catch (Exception e) {
				System.out.println("Enter valid choice");
			}

		} while (!value);

		return choice;
	}

}
