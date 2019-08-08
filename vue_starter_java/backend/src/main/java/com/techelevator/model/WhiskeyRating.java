package com.techelevator.model;

public class WhiskeyRating {

	private Long whiskeyId;
	private Long userId;
	private Long eventId;
	private int tasteRating;
	private int smellRating;
	private int colorRating;
	private int bodyRating;
	private int finishRating;
	private int priceRating;
	private int overallRating;
	
	public int getBodyRating() {
		return bodyRating;
	}
	public void setBodyRating(int bodyRating) {
		this.bodyRating = bodyRating;
	}
	public int getPriceRating() {
		return priceRating;
	}
	public void setPriceRating(int priceRating) {
		this.priceRating = priceRating;
	}
	public Long getWhiskeyId() {
		return whiskeyId;
	}
	public void setWhiskeyId(Long whiskeyId) {
		this.whiskeyId = whiskeyId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public int getTasteRating() {
		return tasteRating;
	}
	public void setTasteRating(int tasteRating) {
		this.tasteRating = tasteRating;
	}
	public int getSmellRating() {
		return smellRating;
	}
	public void setSmellRating(int smellRating) {
		this.smellRating = smellRating;
	}
	public int getColorRating() {
		return colorRating;
	}
	public void setColorRating(int colorRating) {
		this.colorRating = colorRating;
	}
	public int getFinishRating() {
		return finishRating;
	}
	public void setFinishRating(int finishRating) {
		this.finishRating = finishRating;
	}
	public int getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}
	
	
	
}
