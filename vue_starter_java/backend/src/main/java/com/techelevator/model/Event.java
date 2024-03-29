package com.techelevator.model;

import java.time.LocalDate;
import java.util.List;

public class Event {
	
	private List<Whiskey> tastingWhiskeys;
	private String time;
	private LocalDate date;
	private String location;
	private String title;
	private String eventDescription;
	private Boolean isBlindTasting;
	private Boolean isPrivate;
	private Long eventId;
	
	public List<Whiskey> getTastingWhiskeys() {
		return tastingWhiskeys;
	}
	public void setTastingWhiskeys(List<Whiskey> tastingWhiskeys) {
		this.tastingWhiskeys = tastingWhiskeys;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Boolean getIsBlindTasting() {
		return isBlindTasting;
	}
	public void setIsBlindTasting(Boolean isBlindTasting) {
		this.isBlindTasting = isBlindTasting;
	}
	public Boolean getIsPrivate() {
		return isPrivate;
	}
	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	

}
