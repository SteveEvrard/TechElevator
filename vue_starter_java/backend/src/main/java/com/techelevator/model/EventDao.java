package com.techelevator.model;

import java.util.List;

public interface EventDao {

	List<Event> getAllEvents();
	void saveEvent(Event event); 
	List<String> getEventsAttendedByUser(Long id);
}
