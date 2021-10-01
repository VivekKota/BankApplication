package com.Banking.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Banking.Model.Account;

public class AccountDao 
{
	public void addAccount(Account account) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("insert into account(balance, accountType) values(?,?)");

			pst.setDouble(1, account.getBalance());
			pst.setString(2, account.getAccountType());

			pst.executeUpdate();
			//System.out.println(rows + " rows updated");

			con.commit();

		} 
		catch (Exception e) 
		{
			System.out.println("Error in adding account");
			e.printStackTrace();
		}

	}

	public long findAccountNo() {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from account order by accountNo desc limit 1");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				long accountNo = rs.getLong("accountNo");
				return accountNo;
			}

		} catch (Exception e) {

			System.out.println("Error in finding Account Number");
			e.printStackTrace();
		}
		return 0;

	}

	public void updateBalance(Account account) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("update account set  balance = ?  where accountNo =? ");
			pst.setDouble(1, account.getBalance());
			pst.setLong(2, account.getAccountNo());

			pst.executeUpdate();
			//System.out.println(rows + " rows updated");
			con.commit();

		} catch (Exception e) {

			System.out.println("Error in depositing Amount");
			e.printStackTrace();
		}

	}

	public void withdraw(Account account) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("update account set  balance = ?  where accountNo =? ");
			pst.setDouble(1, account.getBalance());
			pst.setLong(2, account.getAccountNo());

			pst.executeUpdate();
			//System.out.println(rows + " rows updated");
			con.commit();

		} catch (Exception e) {

			System.out.println("Error in Withdrawing Amount");
			e.printStackTrace();
		}

	}

	public double balance(long accountNo) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from  account where accountNo = ?");
			pst.setLong(1, accountNo);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				double balance = rs.getLong("balance");
				return balance;
			}

		} catch (Exception e) {

			System.out.println("Error in Showing Balance");
			e.printStackTrace();
		}
		return 0;
	}

	public Account findAccount(long accountNo) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from  account where accountNo = ?");
			pst.setLong(1, accountNo);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				double balance = rs.getLong("balance");
				String accountType = rs.getString("accountType");

				Account account = new Account(accountNo, balance, accountType);
				return account;
			}

		} catch (Exception e) {

			System.out.println("Error in fetching Account Details");
			e.printStackTrace();
		}

		return null;
	}

}
