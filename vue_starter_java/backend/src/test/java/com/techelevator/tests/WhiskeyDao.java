package com.techelevator.tests;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Whiskey;
//import java.util.List;

public interface WhiskeyDao {
	
	List<Whiskey> getAllBrandAndPriceWhiskey();
	void saveWhiskey(Whiskey whiskey);
	

}
