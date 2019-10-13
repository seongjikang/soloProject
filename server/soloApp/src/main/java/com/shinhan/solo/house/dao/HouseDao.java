package com.shinhan.solo.house.dao;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.shinhan.solo.house.House;

@Repository
public class HouseDao implements IHouseDao {
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://solodatabase.caeplcbbax6v.ap-northeast-2.rds.amazonaws.com:3306/solodb?useSSL=false";
	private final static String USER_ID = "admin";
	private final static String USER_PW = "shinhan!1";
	
	private final static String HOUSE_ALL_SELECT = "SELECT * FROM house_tb";

	private ComboPooledDataSource dataSource;

	private JdbcTemplate template;
	
	public HouseDao() {
		dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(DRIVER);
			dataSource.setJdbcUrl(URL);
			dataSource.setUser(USER_ID);
			dataSource.setPassword(USER_PW);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		
		template = new JdbcTemplate();
		template.setDataSource(dataSource);
	}

	@Override
	public int houseInsert(House house) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public House houseSelect(House house) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int houseUpdate(House house) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int houseDelete(House house) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<House> houseAllSelect() {
		List<House> houses = null;
		
		houses = template.query(HOUSE_ALL_SELECT, new RowMapper<House>() {
			@Override
			public House mapRow(ResultSet rs, int rowNum) throws SQLException {
				House house = new House();

				house.setHouseNo(rs.getInt("house_no"));
				house.setHouseName(rs.getString("house_name"));
				house.setHouseSize(rs.getFloat("house_size"));
				house.setAddress(rs.getString("address"));
				house.setAddressDetail(rs.getString("address_detail"));
				house.setHouseViewUrl(rs.getString("house_floor_plan_url"));
				house.setHouseFloorPlanUrl(rs.getString("house_floor_plan_url"));
				house.setBedRoom(rs.getInt("bedroom"));
				house.setRestRoom(rs.getInt("restroom"));
				house.setBalcony(rs.getInt("balcony"));
				house.setKitchen(rs.getInt("kitchen"));
				house.setLivingRoom(rs.getInt("livingroom"));
				house.setHall(rs.getInt("hall"));
				
				return house;
			}
		});

		if (houses.isEmpty())
			return null;

		return houses;
	}
	
	

}
