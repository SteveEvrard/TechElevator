package com.techelevator.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.model.Event;
import com.techelevator.model.EventDao;
import com.techelevator.model.User;



@RestController
@CrossOrigin
public class EventController {

		private final EventDao eventDao;
		
	    @Autowired
	    private AuthProvider auth;

		public EventController(EventDao eventDao) {
			this.eventDao = eventDao;
		}

		@GetMapping(path="/api/events")
		public List<Event> getAllEvents() {
			return eventDao.getAllEvents();
		}
		
		@GetMapping(path="/api/users/{id}/events")
		public List<Event> getAllEventsByUser(@PathVariable long id) {
			return eventDao.getEventsAttendedByUser(id);
		}
		
		@GetMapping(path="/api/event/{id}")
		public Event getEventDetail(@PathVariable long id) {
			return eventDao.getEventDetailsByEventId(id);
		}
		
		@PostMapping(path="/api/events")
		public ResponseEntity<Event> createEvent(@RequestBody Event newEvent) {
			eventDao.saveEvent(newEvent);
			newEvent = eventDao.getEventIdByTitleDateLocationTime(newEvent);
			
			UriComponents uriComponent = ServletUriComponentsBuilder.fromCurrentRequestUri()
			.path("/" + newEvent.getEventId()).build();
			
			return ResponseEntity.created(uriComponent.toUri()).body(newEvent);
		}
		
		@PostMapping(path="/api/event/{eventId}")
		public void checkInUserAtEvent(@PathVariable Long eventId) {
			User currentUser = auth.getCurrentUser();
			eventDao.checkInUserToEvent(currentUser.getId(), eventId);
		}
		

}
