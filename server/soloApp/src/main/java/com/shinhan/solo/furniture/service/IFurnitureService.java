package com.shinhan.solo.furniture.service;

import java.util.List;

import com.shinhan.solo.furniture.Furniture;

public interface IFurnitureService {
	void furnitureRegister(Furniture furniture);
	Furniture furnitureSearch(Furniture furniture);
	Furniture furnitureModify(Furniture furniture);
	int furnitureRemove(Furniture furniture);
	List<Furniture> furnitureAllSearch();
}
