package com.shinhan.solo.furniture.dao;

import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.shinhan.solo.furniture.Furniture;

@Repository
public class FurnitureDao implements IFurnitureDao {

	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://solodatabase.caeplcbbax6v.ap-northeast-2.rds.amazonaws.com:3306/solodb?useSSL=false";
	private final static String USER_ID = "admin";
	private final static String USER_PW = "shinhan!1";

	private final static String FURNITURE_ALL_SELECT = "SELECT * FROM furniture_tb";

	private ComboPooledDataSource dataSource;

	private JdbcTemplate template;

	public FurnitureDao() {
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
	public int furnitureInsert(Furniture furniture) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Furniture furnitureSelect(Furniture furniture) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int furnitureUpdate(Furniture furniture) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int furnitureDelete(Furniture furniture) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Furniture> furnitureAllSelect() {
		List<Furniture> furnitures = null;

		// 1锅 掳 规过
		/*
		furnitures = template.query(FURNITURE_ALL_SELECT, new PreparedStatementSetter() {
		
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
			
			}
		}, new RowMapper<Furniture>() {

			@Override
			public Furniture mapRow(ResultSet rs, int rowNum) throws SQLException {
				Furniture furniture = new Furniture();

				furniture.setFurnitureNo(rs.getInt("furniture_no"));
				furniture.setCategory(rs.getString("category"));
				furniture.setModel(rs.getString("model"));
				furniture.setPrice(rs.getInt("price"));
				furniture.setFurnitureName(rs.getString("furniture_name"));
				furniture.setFurnitureUrl(rs.getString("furniture_url"));
				furniture.setDirection(rs.getInt("direction"));
				furniture.setBrand(rs.getString("brand"));

				return furniture;
			}
		});
		*/
		
		// 2锅 掳 规过
		/*
		furnitures = template.query(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn)
					throws SQLException {
				PreparedStatement pstmt = conn.prepareStatement(FURNITURE_ALL_SELECT);
				
				return pstmt;
			}
		}, new RowMapper<Furniture>() {
			@Override
			public Furniture mapRow(ResultSet rs, int rowNum) throws SQLException {
				Furniture furniture = new Furniture();
				
				furniture.setFurnitureNo(rs.getInt("furniture_no"));
				furniture.setCategory(rs.getString("category"));
				furniture.setModel(rs.getString("model"));
				furniture.setPrice(rs.getInt("price"));
				furniture.setFurnitureName(rs.getString("furniture_name"));
				furniture.setFurnitureUrl(rs.getString("furniture_url"));
				furniture.setDirection(rs.getInt("direction"));
				furniture.setBrand(rs.getString("brand"));				
				
				return furniture;
			}
		});
		*/

		// 3锅 掳 规过
		furnitures = template.query(FURNITURE_ALL_SELECT, new RowMapper<Furniture>() {
			@Override
			public Furniture mapRow(ResultSet rs, int rowNum) throws SQLException {
				Furniture furniture = new Furniture();

				furniture.setFurnitureNo(rs.getInt("furniture_no"));
				furniture.setCategory(rs.getString("category"));
				furniture.setModel(rs.getString("model"));
				furniture.setPrice(rs.getInt("price"));
				furniture.setFurnitureName(rs.getString("furniture_name"));
				furniture.setFurnitureUrl(rs.getString("furniture_url"));
				furniture.setDirection(rs.getInt("direction"));
				furniture.setBrand(rs.getString("brand"));
				
				return furniture;
			}
		});

		if (furnitures.isEmpty())
			return null;

		return furnitures;
	}

}
