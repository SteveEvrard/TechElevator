package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWhiskeyRatingDao implements WhiskeyRatingDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWhiskeyRatingDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);	
	}

	@Override
	public List<WhiskeyRating> getRatingsByEvent(long eventId) {
		List<WhiskeyRating> ratingsForEvent = new ArrayList<WhiskeyRating>();
		
		String sql = "SELECT whiskey_rating_id, whiskey_id, event_id, user_id, taste_rating, nose_rating, color_rating, " + 
				"body_rating, finish_rating, price__rating, overall_rating " + 
				"FROM whiskeyrating " + 
				"WHERE event_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
		
		while(results.next()) {
			ratingsForEvent.add(mapRowToRating(results));
		}
		
		return ratingsForEvent;
	}
	
	@Override
	public List<WhiskeyRating> getRatingsByUser(long userId) {
		List<WhiskeyRating> ratingsForUser = new ArrayList<WhiskeyRating>();
		
		String sql = "SELECT whiskey_rating_id, whiskey_id, event_id, user_id, taste_rating, nose_rating, color_rating, " + 
				"body_rating, finish_rating, price__rating, overall_rating " + 
				"FROM whiskeyrating " + 
				"WHERE user_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		
		while(results.next()) {
			ratingsForUser.add(mapRowToRating(results));
		}
		
		return ratingsForUser;
	}
	
	@Override
	public List<WhiskeyRating> getRatingsByEventAndWhiskey(long eventId, long whiskeyId){
		List<WhiskeyRating> ratingsForEventAndWhiskey = new ArrayList<WhiskeyRating>();
		
		String sql = "SELECT whiskey_rating_id, whiskey_id, event_id, user_id, taste_rating, nose_rating, color_rating, " + 
				"body_rating, finish_rating, price__rating, overall_rating " + 
				"FROM whiskeyrating " + 
				"WHERE event_id = ? AND whiskey_id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId, whiskeyId);
		
		while(results.next()) {
			ratingsForEventAndWhiskey.add(mapRowToRating(results));
		}
		
		return ratingsForEventAndWhiskey;
	}

	@Override
	public void submitRating(WhiskeyRating rating) {
		
		String sql = "INSERT INTO whiskeyrating (whiskey_rating_id, whiskey_id, event_id, user_id, taste_rating, nose_rating, color_rating, " + 
				"body_rating, finish_rating, price__rating, overall_rating) " + 
				"VALUES (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(sql, rating.getWhiskeyId(), rating.getEventId(), rating.getUserId(), rating.getTasteRating(),
				rating.getSmellRating(), rating.getColorRating(), rating.getBodyRating(), rating.getFinishRating(),
				rating.getPriceRating(), rating.getOverallRating());

	}
	
	private WhiskeyRating mapRowToRating(SqlRowSet results) {
		WhiskeyRating rating = new WhiskeyRating();
		
		rating.setWhiskeyId(results.getLong("whiskey_id"));
		rating.setEventId(results.getLong("event_id"));
		rating.setUserId(results.getLong("user_id"));
		rating.setTasteRating(results.getInt("taste_rating"));
		rating.setSmellRating(results.getInt("nose_rating"));
		rating.setColorRating(results.getInt("color_rating"));
		rating.setBodyRating(results.getInt("body_rating"));
		rating.setFinishRating(results.getInt("finish_rating"));
		rating.setPriceRating(results.getInt("price__rating"));
		rating.setOverallRating(results.getInt("overall_rating"));
		
		return rating;
	}

}
