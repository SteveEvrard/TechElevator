package com.techelevator.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.model.JdbcWhiskeyRatingDao;
import com.techelevator.model.WhiskeyRating;
import com.techelevator.model.WhiskeyRatingDao;

public class JDBCWhiskeyRatingDAOIntegrationTest extends DAOIntegrationTesting{

	private WhiskeyRatingDao dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		dao = new JdbcWhiskeyRatingDao(dataSource);
		
	}
	
	@Test
	public void get_ratings_by_event_gets_all_ratings_from_event() {
		
		String sql = "insert into whiskeyrating (whiskey_rating_id, whiskey_id, event_id, user_id, taste_rating, nose_rating, color_rating, " + 
				"body_rating, finish_rating, price__rating, overall_rating) " + 
				"values (default, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3);";
		jdbcTemplate.update(sql);
		
		String sql2 = "insert into whiskeyrating (whiskey_rating_id, whiskey_id, event_id, user_id, taste_rating, nose_rating, color_rating,\n" + 
				"body_rating, finish_rating, price__rating, overall_rating)\n" + 
				"values (default, 2, 1, 2, 3, 3, 3, 3, 3, 3, 3);";
		jdbcTemplate.update(sql2);
		
		List<WhiskeyRating> ratingsForEvent = dao.getRatingsByEvent();
		
		Assert.assertEquals(2, ratingsForEvent.size());
	}
	
}
