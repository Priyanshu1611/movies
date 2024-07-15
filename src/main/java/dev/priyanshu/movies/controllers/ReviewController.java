package dev.priyanshu.movies.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.priyanshu.movies.model.Review;
import dev.priyanshu.movies.services.ReviewService;
import dev.priyanshu.movies.services.responses.ErrorResponse;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

	@Autowired
	private ReviewService service;
	
	
	@PostMapping
	public ResponseEntity<?> addReview(@RequestBody Map<String,String> payload){
		if(payload.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("No Review Found To Add", HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
		
		try {
			Optional<Review> review = service.createReview(payload.get("reviewBody"),payload.get("imdbID"));
			return ResponseEntity.ok(review);
		} catch (Exception e) {
			 	ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
