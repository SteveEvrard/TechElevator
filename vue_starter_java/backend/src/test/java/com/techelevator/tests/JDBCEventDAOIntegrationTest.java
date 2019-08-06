package com.techelevator.tests;

import java.time.LocalDate;
import java.time.LocalTime;
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
		
		String sql = "INSERT INTO event(event_id, date_time, location, title, is_blind, has_occurred, is_private) " + 
				"VALUES (default, '2017-10-10', 'test', 'test', true, true, true);";
		
		jdbcTemplate.update(sql);
		
		List<Event> updatedEventsList = dao.getAllEvents();
		
		int eventListNewSize = updatedEventsList.size();
		
		Assert.assertEquals(eventListOriginalSize + 1, eventListNewSize);
	}
	
	@Test
	public void save_event_adds_event() {
		
		String sql = "INSERT INTO event(event_id, date_time, location, title, is_blind, has_occurred, is_private) " + 
				"VALUES (10, '2017-10-10', 'test', 'test', true, true, true);";
		jdbcTemplate.update(sql);
		
		Event event = createEvent();
		
		dao.saveEvent(event);
		
		String sql2 = "select event_id, date_time, location, title, is_blind, has_occurred, is_private "
				+ "from event where event_id = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql2, 10);
		result.next();
		Integer dataReceived = result.getInt("event_id");
		
		Assert.assertSame(10, dataReceived);
		
	}
	
	private Event createEvent() {
		
		Event event = new Event();
		event.setEventId((long) 20);
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
