package com.techelevator.tests;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Whiskey;

@Component	
public class JdbcWhiskeyDao implements WhiskeyDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private JdbcWhiskeyDao dao;

	@Autowired 
	public JdbcWhiskeyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void saveWhiskey(Whiskey whiskey) {
		String sql = "INSERT INTO whiskey(brand), whiskey(price) " +
				"VALUES (DEFAULT, ?, ?);";
		jdbcTemplate.update(sql,whiskey.getPrice(), whiskey.getBrand());
	}
	
	private Whiskey mapRowToWhiskey(SqlRowSet results) {
		Whiskey whiskey = new Whiskey();
		
		whiskey.setBrand(results.getString("brand"));
		whiskey.setPrice(results.getInt("price"));
		
		return whiskey;
	}
}
