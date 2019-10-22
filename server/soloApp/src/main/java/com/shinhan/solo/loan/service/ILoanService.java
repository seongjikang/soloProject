package com.shinhan.solo.loan.service;

import java.util.List;

import com.shinhan.solo.loan.Loan;

public interface ILoanService {
	List<Loan> loanApplySearch();
	int loanApply(Loan loan);
	String loanApprove(String loanResult,String idNumber);
	Loan loanStatus(String idNumber);
}
