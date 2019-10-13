package com.shinhan.solo.furniture.dao;

import java.util.List;

import com.shinhan.solo.furniture.Furniture;

public interface IFurnitureDao {
	int furnitureInsert(Furniture furniture);
	Furniture furnitureSelect(Furniture furniture);
	int furnitureUpdate(Furniture furniture);
	int furnitureDelete(Furniture furniture);
	List<Furniture> furnitureAllSelect();
}
