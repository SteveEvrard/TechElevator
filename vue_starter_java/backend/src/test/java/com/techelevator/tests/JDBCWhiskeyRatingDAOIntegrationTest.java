package com.techelevator.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

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
		
		List<WhiskeyRating> ratingsForEvent = dao.getRatingsByEvent((long) 1);
		
		Assert.assertEquals(2, ratingsForEvent.size());
	}
	
	@Test
	public void submit_rating_adds_rating_to_database() {
		
//		String sql = "insert into whiskeyrating (whiskey_rating_id, whiskey_id, event_id, user_id, taste_rating, nose_rating, color_rating, " + 
//				"body_rating, finish_rating, price__rating, overall_rating) " + 
//				"values (default, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3);";
//		jdbcTemplate.update(sql);
//		
		WhiskeyRating testRating = createWhiskeyRating();
		
		dao.submitRating(testRating);
		
		String sql = "select whiskey_rating_id, whiskey_id, event_id, user_id, taste_rating, nose_rating, color_rating, " + 
				"body_rating, finish_rating, price__rating, overall_rating " + 
				"from whiskeyrating " + 
				"where color_rating = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, 3);
		result.next();
		Integer dataReceived = result.getInt("body_rating");
		
		Assert.assertTrue(3 == dataReceived);
		
	}
	
	private WhiskeyRating createWhiskeyRating() {
		WhiskeyRating rating = new WhiskeyRating();
		
		rating.setWhiskeyId((long) 1);
		rating.setEventId((long) 1);
		rating.setUserId((long) 1);
		rating.setTasteRating(3);
		rating.setSmellRating(3);
		rating.setColorRating(3);
		rating.setBodyRating(3);
		rating.setFinishRating(3);
		rating.setPriceRating(3);
		rating.setOverallRating(3);
		
		
		return rating;
	}
	
}
