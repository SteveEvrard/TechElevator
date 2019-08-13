package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcEventDao implements EventDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcEventDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Event> getAllEvents(){
		List<Event> allEvents = new ArrayList<Event>();
		
		String sql = "SELECT event_id, event_date, event_time, description, location, title, is_blind, is_private " + 
				"FROM event;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			allEvents.add(mapRowToEvent(results));
		}
		
		return allEvents;
	}
	
	public void saveEvent(Event event){ 
		
		String sql = "INSERT INTO event(event_date, event_time, description, location, title, is_blind, is_private) " + 
				"VALUES (?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(sql, event.getDate(), event.getTime(), event.getEventDescription(), event.getLocation(), event.getTitle(), 
				event.getIsBlindTasting(), event.getIsPrivate());
	}
	
	public List<Event> getEventsAttendedByUser(Long id){
		List<Event> allEventsByUser = new ArrayList<>();
		
		String sql = "SELECT event.event_id, event_date, event_time, description, location, " +
				"title, is_blind, is_private From event " + 
				"JOIN userstoevent on event.event_id = userstoevent.event_id " + 
				"WHERE id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while(results.next()) {
			allEventsByUser.add(mapRowToEvent(results));
		}
		
		return allEventsByUser;
	}
	
	public Event getEventDetailsByEventId(Long id) {
		Event event = new Event();
		
		String sql = "SELECT event_id, event_date, event_time, description, location, " + 
				"title, is_blind, is_private " + 
				"FROM event " + 
				"WHERE event_id = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
		while(result.next()) {
			event = mapRowToEvent(result);
		}
		return event;
	}
	
	public Event getEventIdByTitleDateLocationTime(Event event) {
		String sql = "Select event_id from event Where title Like ? And location = ?;";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, "%" + event.getTitle() + "%", event.getLocation());
		
		while(result.next()) {
				event.setEventId(result.getLong("event_id"));
		}
		return event;
	
	}
	
	private Event mapRowToEvent(SqlRowSet results) {
		Event event = new Event();
		
		event.setEventId(results.getLong("event_id"));
		event.setTastingWhiskeys(getWhiskeyListByEventId(results.getLong("event_id")));
		event.setTime(results.getString("event_time"));
		event.setDate(results.getDate("event_date").toLocalDate());
		event.setLocation(results.getString("location"));
		event.setTitle(results.getString("title"));
		event.setIsBlindTasting(results.getBoolean("is_blind"));
		event.setIsPrivate(results.getBoolean("is_private"));
		event.setEventDescription(results.getString("description"));
		event.setEventId(results.getLong("event_id"));
		
		return event;
	}
	
	private List<Whiskey> getWhiskeyListByEventId(long eventId){
		List<Whiskey> whiskeys = new ArrayList<>();
		
		String sql = "SELECT w.whiskey_id, brand, price " + 
				"FROM whiskey w " + 
				"JOIN whiskeytoevent we ON w.whiskey_id = we.whiskey_id " + 
				"WHERE event_id = ? " + 
				"GROUP BY event_id, w.whiskey_id;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, eventId);
		while(results.next()) {
			Whiskey whiskey = new Whiskey();
			
			whiskey.setWhiskeyId(results.getLong("whiskey_id"));
			whiskey.setBrand(results.getString("brand"));
			whiskey.setPrice(results.getInt("price"));
			
			whiskeys.add(whiskey);
		}
		
		return whiskeys;
	}

}
