package com.techelevator.model;

import java.util.List;
import java.util.Map;

public interface WhiskeyRatingDao {

	List<WhiskeyRating> getRatingsByEvent(long eventId);
	List<WhiskeyRating> getRatingsByUser(long userId);
	List<WhiskeyRating> getRatingsByEventAndWhiskey(long eventId, long userId);
	void submitRating(WhiskeyRating rating);

}
