package com.techelevator.model;

import java.util.List;
import java.util.Map;

public interface WhiskeyRatingDao {

	List<WhiskeyRating> getRatingsByEvent(long eventId);
	void submitRating(WhiskeyRating rating);
	
}
