package com.shinhan.solo.house.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.solo.house.House;
import com.shinhan.solo.house.dao.HouseDao;

@Service
public class HouseService implements IHouseService {

	@Autowired
	HouseDao dao;

	@Override
	public void houseRegister(House house) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public House houseSearch(House house) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public House houseModify(House house) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int houseRemove(House house) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<House> houseAllSearch() {
		List<House> houses = dao.houseAllSelect();
		
		if (houses == null || houses.size() == 0) {
			System.out.println("ListUp Fail!!");
		} else {
			System.out.println("ListUp Success!!");
		}
		
		return houses;
	}
	
}
