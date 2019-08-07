package com.techelevator.tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Event;
import com.techelevator.model.EventDao;
import com.techelevator.model.JdbcEventDao;


public class JDBCEventDAOIntegrationTest extends DAOIntegrationTesting{
	
	private EventDao dao;
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setup() {
		
		jdbcTemplate = new JdbcTemplate(dataSource);
		dao = new JdbcEventDao(dataSource);
		
	}
	
	@Test
	public void get_all_events_returns_all_events() {
		List<Event> eventsList = dao.getAllEvents();
		
		int eventListOriginalSize = eventsList.size();
		
		String sql = "INSERT INTO event(event_id, event_date, event_time, description, location, title, is_blind, has_occurred, is_private) " + 
				"VALUES (default, '2017-10-10', '12:00:00', 'test', 'test', 'test', true, true, true);";
		
		jdbcTemplate.update(sql);
		
		List<Event> updatedEventsList = dao.getAllEvents();
		
		int eventListNewSize = updatedEventsList.size();
		
		Assert.assertEquals(eventListOriginalSize + 1, eventListNewSize);
	}
	
	@Test
	public void save_event_adds_event() {
		
		Event event = createEvent();
		
		dao.saveEvent(event);
		
		String sql = "SELECT event_id, event_date, event_time, description, location, title, is_blind, has_occurred, is_private "
				+ "FROM event WHERE location = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, "test");
		result.next();
		String dataReceived = result.getString("location");
		
		Assert.assertEquals("test", dataReceived);
		
	}
	
	@Test
	public void get_event_by_user() {
		List<String> eventsByUserList = dao.getEventsAttendedByUser((long) 1);
		
		String sql = "insert into event(event_id, event_date, event_time, description, location, title, is_blind, has_occurred, is_private) " + 
				"values (10, '2017-10-10', '12:00:00', 'test', 'test', 'test', true, true, true);";
		
		jdbcTemplate.update(sql);
		
		String sql2 = "insert into jointable(id, event_id) " + 
				"values (1, 10);";
		
		jdbcTemplate.update(sql2);

		String sql3 = "select title from event " + 
				"join jointable on event.event_id = jointable.event_id " + 
				"where id = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql3, 10);
		
		eventsByUserList = dao.getEventsAttendedByUser((long) 1);
		
		Assert.assertEquals(1, eventsByUserList.size());
	}
	
	
	private Event createEvent() {
		
		Event event = new Event();
		event.setEventId((long) Math.random() * 100);
		event.setDate(LocalDate.of(2019,9,9));
		event.setTime(LocalTime.of(12, 0,0));
		event.setLocation("test");
		event.setTitle("title");
		event.setHasOccured(true);
		event.setIsBlindTasting(true);
		event.setIsPrivate(true);
		
		return event;
	}
}
