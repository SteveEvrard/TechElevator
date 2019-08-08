package com.techelevator.model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcWhiskeyRatingDao implements WhiskeyRatingDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWhiskeyRatingDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);	
	}

	@Override
	public List<WhiskeyRating> getRatingsByEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WhiskeyRating> getAverageRatingsByEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void submitRating(WhiskeyRating rating) {
		// TODO Auto-generated method stub

	}

}
