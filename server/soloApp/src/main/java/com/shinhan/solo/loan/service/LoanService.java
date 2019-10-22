package com.shinhan.solo.loan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.solo.loan.Loan;
import com.shinhan.solo.loan.dao.LoanDao;
import com.shinhan.solo.user.User;

@Service
public class LoanService implements ILoanService {

	@Autowired
	LoanDao dao;
	
	@Override
	public List<Loan> loanApplySearch() {
		List<Loan> loans = dao.laonApplySelect();
		
		if (loans == null || loans.size() == 0) {
			System.out.println("ListUp Fail!!");
		} else {
			System.out.println("ListUp Success!!");
		}
		
		return loans;
	}

	@Override
	public int loanApply(Loan loan) {
		Loan tmpLoan = dao.loanSelectForApply(loan.getIdNumber());
		int result = 0;
		if (tmpLoan == null ) {
			result = dao.loanInsert(loan);
			if (result == 0) {
				System.out.println("Apply Fail!!");
			} else {
				System.out.println("Apply Success!!");
			}
			return result;
		} else {
			System.out.println("Apply Fail!!");
			return 0;
		}
	}

	@Override
	public String loanApprove(String loanResult, String idNumber) {

		int result = dao.loanUpdateForExcute(loanResult, idNumber);
	
		if(result == 0 ) {
			System.out.println("Fail!!");
			return "fail";
		} else {
			System.out.println("Success!!");
			return loanResult;
		}
		
	}

	@Override
	public Loan loanStatus(String idNumber) {
		Loan loan = dao.loanSelectForResult(idNumber);
		
		if (loan == null) {
			System.out.println("Select Fail!!");
		} else {
			System.out.println("Select Success!!");
		}
		
		return loan;
	}
	
}
