package com.shinhan.solo.user.controller;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.solo.member.Member;
import com.shinhan.solo.user.User;
import com.shinhan.solo.user.service.UserService;

@Controller
@RequestMapping("/v1")
public class UserController {

	@Autowired
	UserService service;
	
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
	
	@RequestMapping(value= "/user/update", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject userUpdate(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		
		String password = request.get("password").toString();
		String idNum = request.get("id_number").toString();
		
		int result = service.userModify(password, idNum);
		
		if (result == 0) {
			jsonMain.put("result","fail");
		} else {
			jsonMain.put("result","success");
		}
		
		return jsonMain;
	}
	
	@RequestMapping(value= "/user/delete", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject userDelete(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		
		String idNum = request.get("id_number").toString();
		
		int result = service.userRemove(idNum);
		
		if (result == 0) {
			jsonMain.put("result","fail");
		} else {
			jsonMain.put("result","success");
		}
		
		return jsonMain;
	}
	
	@RequestMapping(value= "/user/join", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject userJoin(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		User user = new User();
		
		user.setName(request.get("name").toString());
		user.setUuid(request.get("uuid").toString());
		user.setIdNumber(request.get("id_number").toString());
		user.setPassword(request.get("password").toString());
		
		int result = service.userRegister(user);
		
		if (result == 0) {
			jsonMain.put("result","fail");
		} else {
			jsonMain.put("result","success");
		}
		
		return jsonMain;
	}
	
	@RequestMapping(value = "/user/search/certificate", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject userSearchForCertificate(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		
		String name = request.get("name").toString();
		String idNum = request.get("id_number").toString();
		
		User user = service.userSearch(name, idNum);
		
		if (user == null) {
			jsonMain.put("result","fail");
		} else {
			jsonMain.put("result","success");
		}
		
		return jsonMain;
	}
	
	@RequestMapping(value = "/user/search/register", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject userSearchForRegister(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		
		String uuid = request.get("uuid").toString();
		
		User user = service.userSearch(uuid);
		
		if (user == null) {
			jsonMain.put("result","able");
		} else {
			jsonMain.put("result","unable");
		}
		
		return jsonMain;
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject userSearchForLogin(@RequestBody JSONObject request) {
		JSONObject jsonMain = new JSONObject();
		
		String uuid = request.get("uuid").toString();
		String password = request.get("password").toString();
		
		User user = service.userSearchForLogin(uuid,password);
		
		if (user == null) {
			jsonMain.put("result","fail");
		} else {
			jsonMain.put("result","success");
		}
		
		return jsonMain;
	}
	
	@RequestMapping(value = "/user/listup", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject userListUp() {
		JSONObject jsonMain = new JSONObject();
		
		List<User> users = service.userAllSearch();
		JSONArray jArray = new JSONArray();
		for(int i=0; i<users.size(); i++) {
			User user = users.get(i);
			JSONObject jObject = new JSONObject();
			jObject.put("name", user.getName());
			jObject.put("uuid", user.getUuid());
			jObject.put("id_number", user.getIdNumber());
			jObject.put("password", user.getPassword());
		
			jArray.add(i,jObject);
		}
		jsonMain.put("user_list", jArray);
		return jsonMain;
	}
	
	// Login
	@RequestMapping("/user/loginForm")
	public String loginForm(User user) {
		return "/user/loginForm";
	}
	
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public String memLogin(User user, HttpSession session) {
		
		User tmpUser = service.userSearchForLogin(user.getUuid(), user.getPassword());
		
		if (tmpUser != null )
		{
			session.setAttribute("user", tmpUser);
			return "/user/loginOk";
		} else {
			return "/user/loginFail";
		}
		
	}
	
	@RequestMapping("/user/logout")
	public String memLogout(User user, HttpSession session) {
		
		session.invalidate();
		
		return "/user/logoutOk";
	}
	
	
}
