package com.shinhan.solo.house.dao;

import java.util.List;

import com.shinhan.solo.house.House;

public interface IHouseDao {
	int houseInsert(House house);
	House houseSelect(House house);
	int houseUpdate(House house);
	int houseDelete(House house);
	List<House> houseAllSelect();
}
