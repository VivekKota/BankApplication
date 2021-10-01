package com.Banking.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.Banking.Model.Transaction;

public class TransactionDao {

	public void addTransaction(Transaction transaction) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement(
					"insert into transaction(accountNo, date, description, type, amount) values(?,?,?,?,?)");

			pst.setLong(1, transaction.getAccountNo());
			pst.setString(2, transaction.getDate());
			pst.setString(3, transaction.getDescription());
			pst.setString(4,transaction.getType());
			pst.setDouble(5, transaction.getAmount());

			pst.executeUpdate();
			// System.out.println(rows + " rows updated");

			con.commit();

		} 
		catch (Exception e) 
		{
			System.out.println("Error in adding account");
			e.printStackTrace();
		}

	}

	public List<Transaction> findTransactions(long accountNo) {
		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con
					.prepareStatement("Select * from transaction where accountNo = ? order by transactionId desc Limit 20");
			pst.setLong(1, accountNo);
			ResultSet rs = pst.executeQuery();

			List<Transaction> transactions = new ArrayList<Transaction>();
			while (rs.next()) {

				long transactionId = rs.getLong("transactionId");
				String date = rs.getString("date");
				String description = rs.getString("description");
				String type = rs.getString("type");
				double amount = rs.getDouble("amount");

				Transaction transaction = new Transaction(transactionId, accountNo, date, description, type, amount);
				transactions.add(transaction);
			}
			return transactions;

		} catch (Exception e) {

			System.out.println("Error in finding List of Transactions ");
			e.printStackTrace();
		}
		return null;
	}

	public double findTransactionsAmount(long accountNo, String date , String type) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement(
					"select SUM(amount) as Amount from (select amount from transaction where accountNo = ? and date =? and type = ?) as TransactionsAmount");

			pst.setLong(1, accountNo);
			pst.setString(2, date);
			pst.setString(3, type);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				double amount = rs.getDouble("amount");
				return amount;

			}

		} catch (Exception e) {

			System.out.println("Error in finding Total Transaction amoount in a day ");
			e.printStackTrace();
		}
		return 0;
	}

}
