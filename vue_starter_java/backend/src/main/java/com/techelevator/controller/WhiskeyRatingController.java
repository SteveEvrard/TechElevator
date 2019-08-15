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
import com.techelevator.model.User;
import com.techelevator.model.WhiskeyRating;
import com.techelevator.model.WhiskeyRatingDao;


@RestController
@CrossOrigin
public class WhiskeyRatingController {
	
	@Autowired
    private AuthProvider auth;
	
	private final WhiskeyRatingDao whiskeyRatingDao;

	public WhiskeyRatingController(WhiskeyRatingDao whiskeyRatingDao) {
		this.whiskeyRatingDao = whiskeyRatingDao;
	}
	
	@GetMapping(path="/api/event/{eventId}/whiskeyRating")
	public List<WhiskeyRating> getAllRatingsByEvent(@PathVariable long eventId) {
		return whiskeyRatingDao.getRatingsByEvent(eventId);
	}
	
	@GetMapping(path="/api/event/{eventId}/whiskeyRating/{whiskeyId}")
	public List<WhiskeyRating> getAllRatingsByEventAndWhiskey(@PathVariable Long eventId, @PathVariable Long whiskeyId) {
		return whiskeyRatingDao.getRatingsByEventAndWhiskey(eventId, whiskeyId);
	}

	@GetMapping(path="/api/users/{eventId}/whiskeyRatings")
	public List<WhiskeyRating> getAllRatingsByEventAndUser(long eventId) {
		User currentUser = auth.getCurrentUser();
		
		return whiskeyRatingDao.getRatingsByUserAndEvent(currentUser.getId(), eventId);
	}
	
	@PostMapping(path="/api/users/{eventId}/{whiskeyId}/whiskeyRating")
	public ResponseEntity<WhiskeyRating> rateWhiskey(@RequestBody WhiskeyRating rating) {
		User currentUser = auth.getCurrentUser();
		rating.setUserId(currentUser.getId());

		whiskeyRatingDao.submitRating(rating);

		UriComponents uriComponent = ServletUriComponentsBuilder.fromCurrentRequestUri()
		.path("/" + rating.getUserId() + rating.getEventId() + rating.getWhiskeyId()).build();
		
		return ResponseEntity.created(uriComponent.toUri()).body(rating);
	}

}
