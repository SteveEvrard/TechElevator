package com.techelevator.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Event;
import com.techelevator.model.EventDao;



@RestController
@RequestMapping("/api/events")
@CrossOrigin
public class EventController {

		private final EventDao eventDao;

		public EventController(EventDao eventDao) {
			this.eventDao = eventDao;
		}

		@GetMapping
		public List<Event> getAllEvents() {
			return eventDao.getAllEvents();
		}
		
//		@RequestMapping(path="/api/events", method=RequestMethod.GET)
//		public List<Event> getAllEvents() {
//			return eventDao.getAllEvents();
//		}

//		@GetMapping("/{id}")
//		public ProductReview getProductReview(@PathVariable int id) {
//			ProductReview productReview = productReviewDao.read(id);
//
//			if (productReview != null) {
//				return productReview;
//			}
//
//			throw new ProductReviewNotFoundException(id, "Product review could not be found.");
//		}
//
//		@PostMapping
//		public ResponseEntity<ProductReview> createProductReview(@RequestBody ProductReview productReview) {
//			productReviewDao.create(productReview);
//
//			UriComponents uriComponent = ServletUriComponentsBuilder.fromCurrentRequestUri()
//					.path("/" + Integer.toString(productReview.getId())).build();
//
//			return ResponseEntity.created(uriComponent.toUri()).body(productReview);
//		}
//
//		@PutMapping("/{id}")
//		@ResponseStatus(HttpStatus.NO_CONTENT)
//		public void updateProductReview(@PathVariable int id, @RequestBody ProductReview productReview) {
//
//			if (productReviewDao.read(productReview.getId()) != null) {
//				productReviewDao.update(productReview);
//				return;
//			}
//
//			throw new ProductReviewNotFoundException(id, "Product review could not be found.");
//		}
//
//		@DeleteMapping("/{id}")
//		@ResponseStatus(HttpStatus.NO_CONTENT)
//		public void deleteProductReview(@PathVariable int id) {
//			if (productReviewDao.read(id) != null) {
//				productReviewDao.delete(id);
//				return;
//			}
//
//			throw new ProductReviewNotFoundException(id, "Product review could not be found.");
//		}
//	}

}
