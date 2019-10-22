package com.shinhan.solo.loan.dao;

import java.util.List;

import com.shinhan.solo.loan.Loan;
import com.shinhan.solo.member.Member;

public interface ILoanDao {
	int loanInsert(Loan loan);
	Loan loanSelectForApply(String idNumber);
	int loanUpdateForExcute(String loanStatus, String idNumber);
	List<Loan> laonApplySelect();
	Loan loanSelectForResult(String idNumber);
}