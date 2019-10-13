package com.shinhan.solo.house.controller;

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

import com.shinhan.solo.house.House;
import com.shinhan.solo.house.service.HouseService;

@Controller
@RequestMapping("/v1")
public class HouseController {
	
	@Autowired
	HouseService service;
	
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
	
	@RequestMapping(value = "/house/listup", method = RequestMethod.POST, produces="application/json;charset=utf-8")
	public @ResponseBody JSONObject furnitureListUp() {
		JSONObject jsonMain = new JSONObject();
		
		List<House> houses = service.houseAllSearch();
		JSONArray jArray = new JSONArray();
		for(int i=0 ; i<houses.size(); i++) {
			House house = houses.get(i);
			JSONObject jObject = new JSONObject();
			jObject.put("houseNo", house.getHouseNo());
			jObject.put("houseName", house.getHouseName());
			jObject.put("houseSize", house.getHouseSize());
			jObject.put("address", house.getAddress());
			jObject.put("addressDetail", house.getAddressDetail());
			jObject.put("houseViewUrl", house.getHouseViewUrl());
			jObject.put("houseFloorPlanUrl", house.getHouseFloorPlanUrl());
			jObject.put("bedroom", house.getBedRoom());
			jObject.put("restroom", house.getRestRoom());
			jObject.put("balcony", house.getBalcony());
			jObject.put("kitchen", house.getKitchen());
			jObject.put("livingroom", house.getLivingRoom());
			jObject.put("hall", house.getHall());
			
			jArray.add(i,jObject);
		}
		jsonMain.put("houseList", jArray);
		return jsonMain;
	}

}
