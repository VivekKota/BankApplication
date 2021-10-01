package com.Banking.Model;

public class Customer {

	private long customerId;
	private String password;
	private String name;
	private String dob;
	private String mobileNo;
	private String panCard;
	private String address;

	public Customer() {
		super();
	}

	public Customer(long customerId, String password, String name, String dob, String mobileNo, String panCard,
			String address) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.mobileNo = mobileNo;
		this.panCard = panCard;
		this.address = address;
	}
	
	public Customer(String password, String name, String dob, String mobileNo, String panCard,
			String address) {
		super();
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.mobileNo = mobileNo;
		this.panCard = panCard;
		this.address = address;
	}
	

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
