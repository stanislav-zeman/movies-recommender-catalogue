package dev.cere.recommendations.service;

import dev.cere.recommendations.api.RecommendationDto;
import dev.cere.recommendations.data.repository.ReviewRepository;
import dev.cere.recommendations.data.review.Review;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public RecommendationService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public RecommendationDto getRecommendations(Long movieId) {
        // 1. Get all reviews for the movie
        List<Review> reviews = reviewRepository.findByContentId(movieId);

        // 2. Calculate the average rating
        Double averageRating = reviews.stream().mapToInt(Review::getStars).average().orElse(0.0);

        // 3. Get all users who rated the movie with a rating greater than the average rating
        List<Review> usersWithReviewsAboveAverageRating =
                reviews.stream().filter(review -> review.getStars() >= averageRating).toList();

        // 4. Get other reviews from the users who rated the movie with a rating greater than the
        // average rating
        List<Review> interestingReviews =
                usersWithReviewsAboveAverageRating.stream()
                        .flatMap(
                                review ->
                                        reviewRepository.findByUserId(review.getUserId()).stream())
                        .filter(review -> !review.getContentId().equals(movieId))
                        .toList();

        // 5. Create the recommendation DTO
        var interestingMovies = interestingReviews.stream().map(Review::getContentId).toList();

        RecommendationDto recommendationDto = new RecommendationDto();
        recommendationDto.setRecommendedMovies(
                interestingMovies.stream().map(String::valueOf).toList());

        return recommendationDto;
    }
}
