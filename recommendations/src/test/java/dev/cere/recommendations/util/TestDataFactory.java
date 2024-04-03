package dev.cere.recommendations.util;

import dev.cere.recommendations.api.RecommendationDto;
import dev.cere.recommendations.data.review.Review;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestDataFactory {

    public static List<Review> reviews = getTestReviews();

    public static RecommendationDto getEmptyRecommendationDto() {
        var recommendationDto = new RecommendationDto();
        recommendationDto.setRecommendedMovies(List.of());

        return recommendationDto;
    }

    public static List<Review> getTestReviews() {
        return List.of(
                getReviewFactory(1L, 3L, 1L, 5),
                getReviewFactory(2L, 4L, 2L, 4),
                getReviewFactory(3L, 5L, 3L, 3),
                getReviewFactory(4L, 6L, 4L, 2),
                getReviewFactory(5L, 7L, 5L, 1),
                getReviewFactory(6L, 8L, 1L, 5),
                getReviewFactory(7L, 18L, 2L, 4),
                getReviewFactory(8L, 9L, 3L, 3),
                getReviewFactory(9L, 9L, 4L, 2),
                getReviewFactory(10L, 10L, 5L, 1),
                getReviewFactory(11L, 1L, 1L, 5),
                getReviewFactory(12L, 99L, 99L, 4));
    }

    private static Review getReviewFactory(Long id, Long contentId, Long userId, Integer stars) {
        Review review = new Review();
        review.setId(id);
        review.setContentId(contentId);
        review.setUserId(userId);
        review.setStars(stars);
        return review;
    }
}
