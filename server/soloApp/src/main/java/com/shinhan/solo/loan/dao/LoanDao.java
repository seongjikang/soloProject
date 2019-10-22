package com.shinhan.solo.loan.dao;

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
import com.shinhan.solo.loan.Loan;

@Repository
public class LoanDao implements ILoanDao {

	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL = "jdbc:mysql://solodatabase.caeplcbbax6v.ap-northeast-2.rds.amazonaws.com:3306/solodb?useSSL=false";
	private final static String USER_ID = "admin";
	private final static String USER_PW = "shinhan!1";

	private final static String LOAN_INSERT_QUERY = "INSERT INTO loan_tb (name, id_number, max_money, request_money, loan_status ) values (?,?,?,?,?)";
	private final static String LOAN_SELECT_FOR_APPLY_QUERY = "SELECT * FROM loan_tb WHERE id_number = ?";
	private final static String LOAN_UPDATE_FOR_EXECUTE = "UPDATE loan_tb SET loan_status = ? WHERE id_number = ?";
	private final static String LOAN_APPLY_SELECT = "SELECT * FROM loan_tb WHERE loan_status = ?";
	private final static String LOAN_SELECT_FOR_RESULT = "SELECT * FROM loan_tb WHERE id_number = ?";

	private ComboPooledDataSource dataSource;

	private JdbcTemplate template;

	public LoanDao() {
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
	public int loanInsert(final Loan loan) {
		int result = 0;

		result = template.update(LOAN_INSERT_QUERY, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// name, id_number, max_money, request_money, loan_status
				pstmt.setString(1, loan.getName());
				pstmt.setString(2, loan.getIdNumber());
				pstmt.setInt(3, loan.getMaxMoney());
				pstmt.setInt(4, loan.getRequestMoney());
				pstmt.setString(5, loan.getLoanStatus());
			}
		});

		return result;

	}

	@Override
	public Loan loanSelectForApply(String idNumber) {
		List<Loan> loans = null;

		loans = template.query(LOAN_SELECT_FOR_APPLY_QUERY, new Object[] { idNumber }, new RowMapper<Loan>() {

			@Override
			public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan = new Loan();
				loan.setName(rs.getString("name"));
				loan.setIdNumber(rs.getString("id_number"));
				loan.setMaxMoney(rs.getInt("max_money"));
				loan.setRequestMoney(rs.getInt("request_money"));
				loan.setLoanStatus(rs.getString("loan_status"));
				return loan;
			}

		});

		if (loans.isEmpty())
			return null;

		return loans.get(0);
	}

	@Override
	public int loanUpdateForExcute(final String loanStatus, final String idNumber) {
		int result = 0;
		
		result = template.update(LOAN_UPDATE_FOR_EXECUTE, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, loanStatus);
				pstmt.setString(2, idNumber);
			}
		});
		
		return result;
	}

	@Override
	public List<Loan> laonApplySelect() {
		List<Loan> loans = null;

		loans = template.query(LOAN_APPLY_SELECT, new Object[] { "apply" }, new RowMapper<Loan>() {

			@Override
			public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan = new Loan();
				loan.setName(rs.getString("name"));
				loan.setIdNumber(rs.getString("id_number"));
				loan.setMaxMoney(rs.getInt("max_money"));
				loan.setRequestMoney(rs.getInt("request_money"));
				loan.setLoanStatus(rs.getString("loan_status"));
				return loan;
			}

		});

		if (loans.isEmpty())
			return null;

		return loans;
	}

	@Override
	public Loan loanSelectForResult(String idNumber) {
		List<Loan> loans = null;

		loans = template.query(LOAN_SELECT_FOR_RESULT, new Object[] { idNumber }, new RowMapper<Loan>() {

			@Override
			public Loan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan = new Loan();
				loan.setName(rs.getString("name"));
				loan.setIdNumber(rs.getString("id_number"));
				loan.setMaxMoney(rs.getInt("max_money"));
				loan.setRequestMoney(rs.getInt("request_money"));
				loan.setLoanStatus(rs.getString("loan_status"));
				return loan;
			}

		});

		if (loans.isEmpty())
			return null;

		return loans.get(0);
	}

}
