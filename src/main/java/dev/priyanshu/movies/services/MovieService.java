package dev.priyanshu.movies.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.priyanshu.movies.model.Movie;
import dev.priyanshu.movies.repositories.MovieRepositroy;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepositroy repo;
	
	public List<Movie> fetchAllMovies() {
		return repo.findAll();
	}
	
	public Movie createMovie(Movie movie) {
		try {
			repo.save(movie);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;
	}
	
	
	public Movie fetchMovieByImdbID(String imdbID) {
        return repo.findByImdbID(imdbID).orElseThrow(() -> new MovieException("Movie not found with IMDb ID: " + imdbID));
    }

}
