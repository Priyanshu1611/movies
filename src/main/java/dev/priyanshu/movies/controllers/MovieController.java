package dev.priyanshu.movies.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.priyanshu.movies.model.Movie;
import dev.priyanshu.movies.services.MovieException;
import dev.priyanshu.movies.services.MovieService;
import dev.priyanshu.movies.services.responses.ErrorResponse;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
	
	@Autowired
	private MovieService service;
	
	@GetMapping
	public ResponseEntity<List<Movie>> getMovies() {
		return new ResponseEntity<List<Movie>>(service.fetchAllMovies(),HttpStatus.OK);
	}

	
	@PostMapping("/create")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie){
		return new  ResponseEntity<Movie>(service.createMovie(movie),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable("id") String id) {
		try {
            Movie movie = service.fetchMovieByImdbID(id);
            return ResponseEntity.ok(movie);
        } catch (MovieException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            ErrorResponse errorResponse = new ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR.value());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	
}
