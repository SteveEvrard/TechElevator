package com.techelevator.tests;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.techelevator.model.User;
import com.techelevator.model.Whiskey;


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
		
		String sql = "INSERT INTO event(event_id, event_date, event_time, description, location, title, is_blind, is_private) " + 
				"VALUES (default, '2017-10-10', '12:00:00', 'test', 'test', 'test', true, true);";
		
		jdbcTemplate.update(sql);
		
		List<Event> updatedEventsList = dao.getAllEvents();
		
		int eventListNewSize = updatedEventsList.size();
		
		Assert.assertEquals(eventListOriginalSize + 1, eventListNewSize);
	}
	
	@Test
	public void save_event_adds_event() {
		
		Event event = createEventWithId();
		
		String sql2 = "INSERT INTO event (event_id) VALUES (?);";
		jdbcTemplate.update(sql2, event.getEventId());
		
		dao.saveEvent(event);
		
		String sql = "SELECT event_id, event_date, event_time, description, location, title, is_blind, is_private "
				+ "FROM event WHERE location = ?;";
		SqlRowSet result = jdbcTemplate.queryForRowSet(sql, "test");
		result.next();
		String dataReceived = result.getString("location");
		
		Assert.assertEquals("test", dataReceived);
		
	}
	
	@Test
	public void get_event_by_user() {
		List<Event> eventsByUserList = dao.getEventsAttendedByUser((long) 1);
		int startingLength = eventsByUserList.size();
		
		Event testEvent = createEventWithId();
		
		String sql = "INSERT INTO event(event_id, event_date, event_time, description, location, title, is_blind, is_private) " + 
				"VALUES (?, '2017-10-10', '12:00:00', 'test', 'test', 'test', true, true);";
		jdbcTemplate.update(sql, testEvent.getEventId());
		
		String sql2 = "insert into userstoevent(id, event_id) " + 
				"values (1, ?);";
		
		jdbcTemplate.update(sql2, testEvent.getEventId());
		
		eventsByUserList = dao.getEventsAttendedByUser((long) 1);
		
		Assert.assertEquals(startingLength + 1, eventsByUserList.size());
	}
	
	@Test
	public void get_event_details_by_event_id_returns_event_details() {
		Event testEvent = createEventWithId();
		
		String sql = "INSERT INTO event(event_id, event_date, event_time, description, location, title, is_blind, is_private) " + 
				"VALUES (?, '2017-10-10', '12:00:00', 'test', 'test', 'test', true, true);";
		jdbcTemplate.update(sql, testEvent.getEventId());
		
		testEvent = dao.getEventIdByTitleDateLocationTime(testEvent);
		
		Event event = dao.getEventDetailsByEventId(testEvent.getEventId());
		
		Assert.assertEquals(testEvent.getTitle(), event.getTitle());
	}
	
	@Test
	public void set_event_id_from_database() {
		
		Event newEvent = createEventWithoutId();
		
		Long resultsBeforeSql = newEvent.getEventId();
		dao.saveEvent(newEvent);
		
		System.out.println(newEvent.getEventId());
		
		dao.getEventIdByTitleDateLocationTime(newEvent);
		System.out.println(newEvent.getEventId());
		
		Assert.assertTrue(resultsBeforeSql != newEvent.getEventId());
	}
	
	@Test
	public void checkin_to_event_adds_user_and_event_id_to_jointable() {
		List<SqlRowSet> checkins = new ArrayList<>();
		Event event = createEventWithId();
		event.setEventId((long) 1);
		User user = new User();
		user.setId((long) 1);
		
		dao.checkInUserToEvent(user.getId(), event.getEventId());
		
		String sql = "SELECT id, event_id FROM userstoevent;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			checkins.add(results);
		}
		
		Assert.assertEquals(1, checkins.size());
	}
	
	private Long generateId() {
		String randomNumberAsString = Double.toString(Math.random() * 1000000);
		return Long.parseLong(randomNumberAsString.substring(0,4));
	}
	
	private Event createEventWithId() {

		Event event = new Event();
		event.setEventId(generateId());
		event.setTastingWhiskeys(createWhiskeysForEvent());
		event.setDate(LocalDate.of(2019,9,9));
		event.setTime("12:00:00");
		event.setLocation("test");
		event.setTitle("test");
		event.setIsBlindTasting(true);
		event.setIsPrivate(true);
		
		return event;
	}

	
	private Event createEventWithoutId() {
		Event event = new Event();
		
		event.setTastingWhiskeys(createWhiskeysForEvent());
		event.setDate(LocalDate.of(2019,9,9));
		event.setTime("12:00:00");
		event.setLocation("test");
		event.setTitle("test");
		event.setIsBlindTasting(true);
		event.setIsPrivate(true);
		
		return event;
	}
	
	private List<Whiskey> createWhiskeysForEvent() {
		List<Whiskey> whiskeys = new ArrayList<>();
		Whiskey whiskey1 = new Whiskey();
		Whiskey whiskey2 = new Whiskey();
		
		whiskey1.setWhiskeyId(1);
		whiskey1.setBrand("test1");
		whiskey1.setPrice(25);
		
		whiskey2.setWhiskeyId(2);
		whiskey2.setBrand("test2");
		whiskey2.setPrice(85);
		
		whiskeys.add(whiskey1);
		whiskeys.add(whiskey2);
		
		return whiskeys;
	}
}
