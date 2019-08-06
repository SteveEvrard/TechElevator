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
		
		String sql = "SELECT event_id, date_time, location, title, is_blind, has_occurred, is_private " + 
				"FROM event;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while (results.next()) {
			allEvents.add(mapRowToEvent(results));
		}
		
		return allEvents;
	}
	
	public void saveEvent(Event event){ 
		Timestamp ts = Timestamp.valueOf(event.getDate().atStartOfDay());
		String sql = "INSERT INTO event(event_id, date_time, location, title, is_blind, has_occurred, is_private) " + 
				"VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
		jdbcTemplate.update(sql, ts, event.getLocation(), event.getTitle(), 
				event.getIsBlindTasting(), event.getHasOccured(), event.getIsPrivate());
	}
	
	private Event mapRowToEvent(SqlRowSet results) {
		Event event = new Event();
		
		LocalDate date = results.getTimestamp("date_time").toLocalDateTime().toLocalDate();
		LocalTime time = results.getTimestamp("date_time").toLocalDateTime().toLocalTime();
		
		event.setTime(time);
		event.setDate(date);
		event.setLocation(results.getString("location"));
		event.setTitle(results.getString("title"));
		event.setIsBlindTasting(results.getBoolean("is_blind"));
		event.setIsPrivate(results.getBoolean("is_private"));
		event.setHasOccured(results.getBoolean("has_occurred"));
		event.setEventId(results.getLong("event_id"));
		
		return event;
	}

}
