package com.techelevator.model;

import java.util.List;

public interface WhiskeyRatingDao {

	List<WhiskeyRating> getRatingsByEvent();
	List<WhiskeyRating> getAverageRatingsByEvent();
	void submitRating(WhiskeyRating rating);
	
}
