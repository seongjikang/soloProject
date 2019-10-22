package com.shinhan.solo.loan.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.solo.loan.Loan;
import com.shinhan.solo.loan.LoanString;
import com.shinhan.solo.loan.service.LoanService;
import com.shinhan.solo.user.User;

@Controller
@RequestMapping("/v1")
public class LoanController {
	
	@Autowired
	LoanService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
	
	@RequestMapping(value = "/loan/apply", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject loanApply(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		Loan loan = new Loan();
		
		loan.setName(request.get("name").toString());
		loan.setIdNumber(request.get("id_number").toString());
		loan.setMaxMoney(Integer.parseInt(request.get("max_money").toString()));
		loan.setRequestMoney(Integer.parseInt(request.get("request_money").toString()));
		loan.setLoanStatus(request.get("loan_request").toString());
		
		int result = service.loanApply(loan);
		
		if (result == 0) {
			jsonMain.put("result","fail");
		} else {
			jsonMain.put("result","success");
		}
		
		return jsonMain;
	}
	
	@RequestMapping(value= "/loan/approve", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject loanApprove(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		
		String idNum = request.get("id_number").toString();
		String loanResult = request.get("loan_result").toString();
		
		String result = service.loanApprove(loanResult, idNum);
			
		jsonMain.put("loan_result", result);
		
		return jsonMain;
	}
	
	@RequestMapping(value = "/loan/loanstatus", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject loanStatus(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		
		String idNum = request.get("id_number").toString();
		
		Loan loan = service.loanStatus(idNum);
		
		if (loan == null) {
			jsonMain.put("result","fail");
		} else {
			jsonMain.put("name", loan.getName());
			jsonMain.put("id_number", loan.getIdNumber());
			jsonMain.put("max_money", loan.getMaxMoney());
			jsonMain.put("request_money", loan.getRequestMoney());
			jsonMain.put("loan_status", loan.getLoanStatus());
			jsonMain.put("result","success");
		}
		
		return jsonMain;
	}

	@RequestMapping(value = "/loan/loanInfo", method = RequestMethod.GET)
	public ModelAndView loanInfo(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Loan> loans = service.loanApplySearch();
		List<LoanString> list = new ArrayList<LoanString>();
		for(int i=0;i<loans.size(); i++) {
			LoanString item = new LoanString();
			item.setName(loans.get(i).getName());
			item.setIdNumber(loans.get(i).getIdNumber());
			item.setMaxMoney(loans.get(i).getMaxMoney()+"¿ø");
			item.setRequestMoney(loans.get(i).getRequestMoney()+"¿ø");
			item.setLoanStatus(loans.get(i).getLoanStatus());
			list.add(item);	
		}
		
		session.setAttribute("loans", loans);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("/loan/loanInfo");
		
		return mav;
	}
}
