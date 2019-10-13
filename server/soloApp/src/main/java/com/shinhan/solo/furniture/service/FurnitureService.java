package com.shinhan.solo.furniture.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.solo.furniture.Furniture;
import com.shinhan.solo.furniture.dao.FurnitureDao;

@Service
public class FurnitureService implements IFurnitureService{

	@Autowired
	FurnitureDao dao;

	@Override
	public void furnitureRegister(Furniture furniture) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Furniture furnitureSearch(Furniture furniture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Furniture furnitureModify(Furniture furniture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int furnitureRemove(Furniture furniture) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Furniture> furnitureAllSearch() {
		List<Furniture> furnitures = dao.furnitureAllSelect();
		
		if (furnitures == null || furnitures.size() == 0) {
			System.out.println("ListUp Fail!!");
		} else {
			System.out.println("ListUp Success!!");
		}
		
		return furnitures;
	}
	
	
}
