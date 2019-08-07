package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

@RestController
@RequestMapping("/api/whiskey")
@CrossOrigin
public class WhiskeyController {

	public class EventController {

		private final WhiskeyDao whiskeyDao;

		public WhiskeyController(WhiskeyDao whiskeyDao) {
			this.whiskeyDao = whiskeyDao;
		}

		@GetMapping
		public List<Whiskey> getWhiskeyBrands() {
			return whiskeyDao.getWhiskeyBrands();
		}
	
}
