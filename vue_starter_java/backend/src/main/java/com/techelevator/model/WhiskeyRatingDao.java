package com.techelevator.model;

import java.util.List;
import java.util.Map;

public interface WhiskeyRatingDao {

	List<WhiskeyRating> getRatingsByEvent(long eventId);
	List<WhiskeyRating> getRatingsByUserAndEvent(long userId, long eventId);
	List<WhiskeyRating> getRatingsByEventAndWhiskey(long eventId, long userId);
	void submitRating(WhiskeyRating rating);

}
