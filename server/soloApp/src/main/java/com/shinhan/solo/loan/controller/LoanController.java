package com.shinhan.solo.loan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shinhan.solo.member.Member;

@Controller
@RequestMapping("/v1")
public class LoanController {

	@RequestMapping("/loan/loanInfo")
	public String loginForm(Member member) {
		return "/loan/loanInfo";
	}
}
