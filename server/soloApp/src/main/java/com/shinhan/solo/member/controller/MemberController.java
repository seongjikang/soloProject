package com.shinhan.solo.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shinhan.solo.member.Member;
import com.shinhan.solo.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping(value="/memJoin.do", method=RequestMethod.POST)
	public String memJoin(Model model, HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		String memMail = request.getParameter("memMail");
		String memPhone1 = request.getParameter("memPhone1");
		String memPhone2 = request.getParameter("memPhone2");
		String memPhone3 = request.getParameter("memPhone3");
		
		service.memberRegister(memId, memPw, memMail, memPhone1, memPhone2, memPhone3);
		
		model.addAttribute("memId",memId);
		model.addAttribute("memPw", memPw);
		model.addAttribute("memPhone", memPhone1 + " - " + memPhone2 + " - " + memPhone3);
		
		return "memJoinOk";		
	}
	
	public String memLogin(Model model, HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String memPw = request.getParameter("memPw");
		
		Member member = service.memberSearch(memId, memPw);
		
		try {
			model.addAttribute("memId", member.getMemId());
			model.addAttribute("memPw", member.getMemPw());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return "memLoginOK";
	}
}
