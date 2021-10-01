package com.Banking.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.Banking.Model.Customer;

public class CustomerDao {

	public void addCustomer(Customer customer) {

		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement(
					"insert into customer(name, dob, mobileNo, panCard , address,password) values(?,?,?,?,?,?)");

			pst.setString(1, customer.getName());
			pst.setString(2, customer.getDob());
			pst.setString(3, customer.getMobileNo());
			pst.setString(4, customer.getPanCard());
			pst.setString(5, customer.getAddress());
			pst.setString(6, customer.getPassword());

			pst.executeUpdate();
			// System.out.println(rows + " rows updated");
			con.commit();

		} catch (Exception e) {
			System.out.println("Error in adding customer");
			e.printStackTrace();
		}
	}

	public long findCustomerId() {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from customer order by customerId desc limit 1");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				long customerId = rs.getLong("customerId");
				return customerId;
			}

		} catch (Exception e) {

			System.out.println("Error in finding customerId");
			e.printStackTrace();
		}
		return 0;

	}

	public void updatePassword(long customerId, String password) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("update customer set  password = ?  where customerId =? ");
			pst.setString(1, password);
			pst.setLong(2, customerId);

			pst.executeUpdate();
			// System.out.println(rows + " rows updated");
			con.commit();

		} catch (Exception e) {

			System.out.println("Error in Updating Password");
			e.printStackTrace();
		}
	}

	public Customer findCustomer(long customerId) {

		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("select * from customer  where customerId =? ");
			pst.setLong(1, customerId);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				String password = rs.getString("password");
				String name = rs.getString("name");
				String dob = rs.getString("dob");
				String mobileNo = rs.getString("mobileNo");
				String panCard = rs.getString("panCard");
				String address = rs.getString("address");

				Customer customer = new Customer(customerId, password, name, dob, mobileNo, panCard, address);
				return customer;
			}

			con.commit();

		} catch (Exception e) {

			System.out.println("Error in Finding Customer Details");
			e.printStackTrace();
		}
		return null;

	}

}
