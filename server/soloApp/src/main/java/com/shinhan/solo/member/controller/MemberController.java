package com.shinhan.solo.member.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shinhan.solo.member.Member;
import com.shinhan.solo.member.service.MemberService;

/*
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
	

	@RequestMapping(value = "/memModify", method = RequestMethod.POST)
	public String memModify(Model model, Member member) {
		
		Member[] members = service.memberModify(member);
		
		model.addAttribute("memBef", members[0]);
		model.addAttribute("memAft", members[1]);
		
		return "memModifyOk";
	}
	
	
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
*/
@Controller
@RequestMapping("/v1")
public class MemberController {

	@Autowired
	MemberService service;
	
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
	
	// Join
	@RequestMapping("/joinForm")
	public String joinForm(Member member) {
		return "/member/joinForm";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinReg(Member member) {
		
		service.memberRegister(member);
		
		return "/member/joinOk";
	}
	
	// Login
	@RequestMapping("/loginForm")
	public String loginForm(Member member) {
		return "/member/loginForm";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(Member member, HttpSession session) {
		
		Member mem = service.memberSearch(member);
		
		session.setAttribute("member", mem);
		
		return "/member/loginOk";
	}
	
	// Logout
	@RequestMapping("/logout")
	public String memLogout(Member member, HttpSession session) {
		
		session.invalidate();
		
		return "/member/logoutOk";
	}
	
	// Modify
	@RequestMapping(value = "/modifyForm")
	public String modifyForm(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		
		if(null == member) {
			return "redirect:/index.jsp";
		} else {
			model.addAttribute("member", service.memberSearch(member));
		}
		
		return "/member/modifyForm";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public ModelAndView modify(Member member, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		Member mem = service.memberModify(member);
		session.setAttribute("member", mem);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("memAft", mem);
		mav.setViewName("/member/modifyOk");
		
		return mav;
	}
	
	// Remove
	@RequestMapping("/removeForm")
	public ModelAndView removeForm(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session =  request.getSession();
		Member member = (Member) session.getAttribute("member");
		
		if(null == member) {
			mav.setViewName("redirect:/index.jsp");
		} else {
			mav.addObject("member", member);
			mav.setViewName("/member/removeForm");
		}
		
		
		return mav;
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String memRemove(Member member, HttpServletRequest request) {
		
		service.memberRemove(member);
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "/member/removeOk";
	}
	
	@RequestMapping(value = "/listup", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject memListUp() {
		JSONObject jsonMain = new JSONObject();
		
		List<Member> members = service.memberAllSearch();
		JSONArray jArray = new JSONArray();
		for(int i=0 ; i<members.size(); i++) {
			Member member = members.get(i);
			JSONObject jObject = new JSONObject();
			jObject.put("memId", member.getMemId());
			jObject.put("memPw", member.getMemPw());
			jObject.put("memMail", member.getMemMail());
			jObject.put("memPurcNum", member.getMemPurcNum());
			
			jArray.add(i,jObject);
		}
		jsonMain.put("sendData", jArray);
		return jsonMain;
	}
	
}
