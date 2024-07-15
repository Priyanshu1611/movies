package dev.priyanshu.movies.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import dev.priyanshu.movies.model.Movie;
import dev.priyanshu.movies.model.Review;
import dev.priyanshu.movies.repositories.ReviewRepositroy;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepositroy repo;

	@Autowired
	private MongoTemplate template;

	public Optional<Review> createReview(String reviewBody, String imdbId) {
		Review review = repo.insert(new Review(reviewBody));

		try {
			template.update(Movie.class).matching(Criteria.where("imdbID").is(imdbId))
					.apply(new Update().push("reviewIds").value(review)).first();
			return Optional.of(review);
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}

	}

}
