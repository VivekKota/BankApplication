package com.Banking.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerAccountDao {

	public void addCustomerAccount(long customerId, long accountNo) {
	
		try {
			Connection con = DbConnection.connect();

			PreparedStatement pst = con.prepareStatement("insert into customerAccount(customerId, accountNo) values(?,?)");

			pst.setLong(1, customerId);
			pst.setLong(2, accountNo);

			pst.executeUpdate();
			//System.out.println(rows + " rows updated");
			con.commit();

		} catch (Exception e) {
			System.out.println("Error in adding Customer Account");
			e.printStackTrace();
		}
	}

	public long findCustomerAcccount(long customerId) {
		
		try {

			Connection con = DbConnection.connect();
			PreparedStatement pst = con.prepareStatement("Select * from  customerAccount where customerId = ?");
			pst.setLong(1, customerId);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				long accountNo = rs.getLong("accountNo");
				return accountNo;
			}

		} catch (Exception e) {

			System.out.println("Error in fetching AccountNo");
			e.printStackTrace();
		}

		return 0;
	}
	

}
