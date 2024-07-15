package dev.priyanshu.movies.repositories;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.priyanshu.movies.model.Movie;



@Repository
public interface MovieRepositroy extends MongoRepository<Movie, ObjectId> {
	
	Optional<Movie> findByImdbID(String imdbID);

}
