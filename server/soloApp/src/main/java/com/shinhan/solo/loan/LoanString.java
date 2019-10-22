package com.shinhan.solo.loan;

public class LoanString {
	private String name;
	private String idNumber;
	private String maxMoney;
	private String requestMoney;
	private String loanStatus;
	public LoanString(String name, String idNumber, String maxMoney, String requestMoney, String loanStatus) {
		super();
		this.name = name;
		this.idNumber = idNumber;
		this.maxMoney = maxMoney;
		this.requestMoney = requestMoney;
		this.loanStatus = loanStatus;
	}
	public LoanString() {
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
	public String getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(String maxMoney) {
		this.maxMoney = maxMoney;
	}
	public String getRequestMoney() {
		return requestMoney;
	}
	public void setRequestMoney(String requestMoney) {
		this.requestMoney = requestMoney;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
}
