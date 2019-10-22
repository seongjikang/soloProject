package com.shinhan.solo.loan;

public class Loan {
	private String name;
	private String idNumber;
	private int maxMoney;
	private int requestMoney;
	private String loanStatus;
	
	public Loan(String name, String idNumber, int maxMoney, int requestMoney, String loanStatus) {
		super();
		this.name = name;
		this.idNumber = idNumber;
		this.maxMoney = maxMoney;
		this.requestMoney = requestMoney;
		this.loanStatus = loanStatus;
	}
	
	public Loan() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public int getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(int maxMoney) {
		this.maxMoney = maxMoney;
	}

	public int getRequestMoney() {
		return requestMoney;
	}

	public void setRequestMoney(int requestMoney) {
		this.requestMoney = requestMoney;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
}
