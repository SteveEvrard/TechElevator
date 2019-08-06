package com.techelevator.model;

import java.util.List;

public interface EventDao {

	List<Event> getAllEvents();
	public void saveEvent(Event event); 
}
