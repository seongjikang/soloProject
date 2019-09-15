package com.shinhan.solo.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.solo.member.Member;
import com.shinhan.solo.member.service.MemberService;

@Controller
@RequestMapping("/v1")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@ModelAttribute("serverTime")
	public String getServerTime(Locale locale) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		return dateFormat.format(date);
	}
	
	@RequestMapping(value = "/memJoin", method = RequestMethod.POST)
	public String memJoin(Member member) {
		
		service.memberRegister(member);
		
		return "memberJoinOK";
	}
	
	@RequestMapping(value = "/memLogin", method = RequestMethod.POST)
	public String memLogin(Member member, HttpServletRequest request) {
		
		Member mem = service.memberSearch(member);
		HttpSession session = request.getSession();
		session.setAttribute("member", mem);
		
		return "memberLoginOK";
	}
	
	@RequestMapping(value = "/memRemove", method = RequestMethod.POST)
	public String memRemove(@ModelAttribute("mem") Member member, HttpServletRequest request) {
		
		service.memberRemove(member);
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "memberRemoveOK";
	}
	
	/*
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public String memModify(Model model, Member member) {
		
		Member[] members = service.memberModify(member);
		
		model.addAttribute("memBef", members[0]);
		model.addAttribute("memAft", members[1]);
		
		return "memModifyOk";
	}
	*/
	
	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public ModelAndView memModify(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member befMem =(Member) session.getAttribute("member");		
		Member aftMem = service.memberModify(member);
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memBef", befMem);
		mav.addObject("memAft", aftMem);
		
		session.setAttribute("member", aftMem);
		
		mav.setViewName("memberModifyOK");
		
		return mav;
	}
}
