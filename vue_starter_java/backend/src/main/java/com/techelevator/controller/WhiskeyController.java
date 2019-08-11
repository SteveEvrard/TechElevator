package com.techelevator.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import com.techelevator.model.Event;
import com.techelevator.model.Whiskey;
import com.techelevator.model.WhiskeyDao;

@RestController
@CrossOrigin
public class WhiskeyController {

		private final WhiskeyDao whiskeyDao;

		public WhiskeyController(WhiskeyDao whiskeyDao) {
			this.whiskeyDao = whiskeyDao;
		}

		@GetMapping(path="/api/whiskeys")
		public List<Whiskey> getWhiskeyBrands() {
			return whiskeyDao.getAllBrandAndPriceWhiskey();
		}		
		
//		@PostMapping(path="/api/whiskeys")
//		public ResponseEntity<Event> createEvent(@RequestBody List<Whiskey> whiskeyBrands) {
//			for(Whiskey placeholder : whiskeyBrands) {
//				whiskeyDao.saveWhiskey(placeholder);
//
//			UriComponents uriComponent = ServletUriComponentsBuilder.fromCurrentRequestUri()
//			.path("/" + placeholder.getWhiskeyId()).build();
//			
//			return ResponseEntity.created(uriComponent.toUri()).body(placeholder);
//			}
//		}
	
}
