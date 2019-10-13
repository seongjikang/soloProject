package com.shinhan.solo.house.service;

import java.util.List;

import com.shinhan.solo.house.House;

public interface IHouseService {
	void houseRegister(House house);
	House houseSearch(House house);
	House houseModify(House house);
	int houseRemove(House house);
	List<House> houseAllSearch();
}
