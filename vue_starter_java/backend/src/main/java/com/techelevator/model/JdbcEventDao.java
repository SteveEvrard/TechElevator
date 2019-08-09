package com.techelevator.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
		
		String sql = "INSERT INTO event(event_id, event_date, event_time, description, location, title, is_blind, is_private) " + 
				"VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(sql, event.getDate(), event.getTime(), event.getEventDescription(), event.getLocation(), event.getTitle(), 
				event.getIsBlindTasting(), event.getIsPrivate());
	}
	
	public List<String> getEventsAttendedByUser(Long id){
		List<String> allEventsByUser = new ArrayList<>();
		
		String sql = "SELECT title FROM event " + 
				"JOIN userstoevent on event.event_id = userstoevent.event_id " + 
				"WHERE id = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while(results.next()) {
			allEventsByUser.add(results.getString("title"));
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
		if(result.next()) {
			event = mapRowToEvent(result);
		}
		return event;
	}
	
	private Event mapRowToEvent(SqlRowSet results) {
		Event event = new Event();
		
		event.setTime(results.getString("event_time"));
		event.setDate(results.getDate("event_date").toLocalDate());
		event.setLocation(results.getString("location"));
		event.setTitle(results.getString("title"));
		event.setIsBlindTasting(results.getBoolean("is_blind"));
		event.setIsPrivate(results.getBoolean("is_private"));
		event.setEventId(results.getLong("event_id"));
		
		return event;
	}

}
