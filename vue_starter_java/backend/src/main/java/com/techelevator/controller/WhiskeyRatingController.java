package com.techelevator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.techelevator.model.Event;
import com.techelevator.model.WhiskeyRating;
import com.techelevator.model.WhiskeyRatingDao;


@RestController
@CrossOrigin
public class WhiskeyRatingController {
	
	private final WhiskeyRatingDao whiskeyRatingDao;

	public WhiskeyRatingController(WhiskeyRatingDao whiskeyRatingDao) {
		this.whiskeyRatingDao = whiskeyRatingDao;
	}
	
	@GetMapping(path="/api/event/{eventId}/whiskeyRating")
	public List<WhiskeyRating> getAllRatingsByEvent(long eventId) {
		return whiskeyRatingDao.getRatingsByEvent(eventId);
	}
	
	@GetMapping(path="/api/event/{eventId}/whiskeyRating/{whiskeyId}")
	public List<WhiskeyRating> getAllRatingsByEventAndWhiskey(long eventId, long whiskeyId) {
		return whiskeyRatingDao.getRatingsByEventAndWhiskey(eventId, whiskeyId);
	}

//	@GetMapping(path="/api/users/{userId}/whiskeyRating")
//	public List<WhiskeyRating> getAllRatingsByEventAndUser(long eventId) {
//		return whiskeyRatingDao.getRatingsByEvent(eventId);
//	}
	
//	@PostMapping(path="/api/users/{userId}{eventId}{whiskeyId}/whiskeyRating")
//	public ResponseEntity<WhiskeyRating> rateWhiskey(@RequestBody WhiskeyRating rating) {
//
//		whiskeyRatingDao.submitRating(rating);
//
//		UriComponents uriComponent = ServletUriComponentsBuilder.fromCurrentRequestUri()
//		.path("/" + rating.getUserId() + rating.getEventId() + rating.getWhiskeyId()).build();
//		
//		return ResponseEntity.created(uriComponent.toUri()).body(rating);
//	}

}
