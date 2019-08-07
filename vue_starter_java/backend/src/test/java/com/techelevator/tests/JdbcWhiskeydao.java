package com.techelevator.tests;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.techelevator.model.Whiskey;

@Component	
public class JdbcWhiskeyDao implements WhiskeyDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired public JdbcWhiskeyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void saveWhiskey(Whiskey whiskey) {
		String sql = "INSERT INTO whiskey(brand), whiskey(price) " +
				"VALUES (DEFAULT, ?);";
		jdbcTemplate.update(sql,whiskey.getPrice(), whiskey.getBrand());
	}

}
