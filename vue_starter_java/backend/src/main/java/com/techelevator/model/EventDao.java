package com.techelevator.model;

import java.util.List;

public interface EventDao {

	List<Event> getAllEvents();
	void saveEvent(Event event); 
	List<Event> getEventsAttendedByUser(Long id);
	Event getEventDetailsByEventId(Long id);
	Event getEventIdByTitleDateLocationTime(Event event);
	
}
