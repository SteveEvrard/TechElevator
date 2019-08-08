package com.techelevator.model;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface WhiskeyDao {
	
	List<Whiskey> getAllBrandAndPriceWhiskey();
	void saveWhiskey(Whiskey whiskey);
	

}
