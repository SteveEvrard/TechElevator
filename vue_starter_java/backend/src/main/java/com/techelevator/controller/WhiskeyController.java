package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Whiskey;
import com.techelevator.model.WhiskeyDao;

@RestController
@RequestMapping("/api/whiskeys")
@CrossOrigin
public class WhiskeyController {

		private final WhiskeyDao whiskeyDao;

		public WhiskeyController(WhiskeyDao whiskeyDao) {
			this.whiskeyDao = whiskeyDao;
		}

		@GetMapping
		public List<Whiskey> getWhiskeyBrands() {
			return whiskeyDao.getAllBrandAndPriceWhiskey();
		}
	
}
