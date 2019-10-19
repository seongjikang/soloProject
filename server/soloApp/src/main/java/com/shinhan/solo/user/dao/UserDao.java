package com.shinhan.solo.user.dao;

import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.shinhan.solo.user.User;

@Repository
public class UserDao implements IUserDao {
	
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://solodatabase.caeplcbbax6v.ap-northeast-2.rds.amazonaws.com:3306/solodb?useSSL=false";
	private final static String USER_ID = "admin";
	private final static String USER_PW = "shinhan!1";
	
	private final static String USER_INSERT_QUERY = "INSERT INTO user_tb (name, uuid, id_number, password) values (?, ?, ?, ?)";
	private final static String USER_SELECT_QUERY_BY_UUID = "SELECT * FROM user_tb WHERE uuid = ?" ;
	private final static String USER_SELECT_QUERY_BY_INFO = "SELECT * FROM user_tb WHERE name = ? and id_number = ?" ;
	private final static String USER_UPDATE_QUERY = "UPDATE user_tb SET password = ? WHERE id_number = ? ";
	private final static String USER_DELETE_QUERY = "DELETE FROM user_tb WHERE id_number = ?";
	private final static String USER_ALL_SELECT_QUERY = "SELECT * FROM user_tb";
	
	private ComboPooledDataSource dataSource;
	
	private JdbcTemplate template;
	
	public UserDao() {
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
	public int userInsert(final User user) {
		
		int result = 0;
		
		result = template.update(USER_INSERT_QUERY, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getName());
				pstmt.setString(2, user.getUuid());
				pstmt.setString(3, user.getIdNumber());
				pstmt.setString(4, user.getPassword());
			}
		});
		
		return result;
	}
	
	@Override
	public User userSelect(String uuid) {

		List<User> users = null;

		users = template.query(USER_SELECT_QUERY_BY_UUID, new Object[]{uuid}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setUuid(rs.getString("uuid"));
				user.setUuid(rs.getString("id_number"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			
		});
		
		if(users.isEmpty())
			return null;

		return users.get(0);
	}
	
	@Override
	public User userSelect(String name, String idNum) {
		List<User> users = null;

		users = template.query(USER_SELECT_QUERY_BY_INFO, new Object[]{name, idNum}, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setUuid(rs.getString("uuid"));
				user.setUuid(rs.getString("id_number"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			
		});
		
		if(users.isEmpty())
			return null;

		return users.get(0);
	}
	
	@Override
	public int userUpdate(final String password, final String idNum) {
		int result = 0;
		
		result = template.update(USER_UPDATE_QUERY, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, password);
				pstmt.setString(2, idNum);
			}
		});
		
		return result;
	}

	@Override
	public int userDelete(final String idNum) {
		int result = 0;
		
		result = template.update(USER_DELETE_QUERY, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, idNum);
			}
		});
		
		return result;
	}

	@Override
	public List<User> userAllSelect() {
		List<User> users = null;

		users = template.query(USER_ALL_SELECT_QUERY, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setName(rs.getString("name"));
				user.setUuid(rs.getString("uuid"));
				user.setIdNumber(rs.getString("id_number"));
				user.setPassword(rs.getString("password"));
				return user;
			}
			
		});
		
		if(users.isEmpty())
			return null;

		return users;
	}
}
