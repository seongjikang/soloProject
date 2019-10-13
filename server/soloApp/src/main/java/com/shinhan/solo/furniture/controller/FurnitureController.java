package com.shinhan.solo.furniture.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinhan.solo.furniture.Furniture;
import com.shinhan.solo.furniture.service.FurnitureService;

@Controller
@RequestMapping("/v1")
public class FurnitureController {

	@Autowired
	FurnitureService service;
	
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
	
	@RequestMapping(value = "/furniture/listup", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject furnitureListUp() {
		JSONObject jsonMain = new JSONObject();
		
		List<Furniture> furnitures = service.furnitureAllSearch();
		JSONArray jArray = new JSONArray();
		for(int i=0 ; i<furnitures.size(); i++) {
			Furniture furniture = furnitures.get(i);
			JSONObject jObject = new JSONObject();
			jObject.put("furnitureNo", furniture.getFurnitureNo());
			jObject.put("category", furniture.getCategory());
			jObject.put("model", furniture.getModel());
			jObject.put("price", furniture.getPrice());
			jObject.put("furnitureName", furniture.getFurnitureName());
			jObject.put("furnitureUrl", furniture.getFurnitureUrl());
			jObject.put("direction", furniture.getDirection());
			jObject.put("brand", furniture.getBrand());	
			
			jArray.add(i,jObject);
		}
		jsonMain.put("furnitureList", jArray);
		return jsonMain;
	}
}
