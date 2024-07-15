package dev.priyanshu.movies.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.priyanshu.movies.model.Review;


@Repository
public interface ReviewRepositroy extends MongoRepository<Review, ObjectId>{
	
}
