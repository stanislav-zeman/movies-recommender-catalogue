package dev.cere.reviews.util;

import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.api.ReviewPutDto;
import dev.cere.reviews.api.ReviewSimpleDto;
import dev.cere.reviews.data.entities.Review;
import org.springframework.stereotype.Component;

@Component
public class TestDataFactory {
    public static Review fiftyShadesOfGreyByBob = getReviewFactory(1L, 1L, 1L, "I loved it!", 5);
    public static Review fiftyShadesOfGreyByRob = getReviewFactory(2L, 1L, 2L, "I hated it!", 1);
    public static Review sashaGreyAdventuresBySandra =
            getReviewFactory(3L, 2L, 3L, "Sasha is so hot! I could not hold myself back!", 5);
    public static Review bigBustyMilfByHughHefner =
            getReviewFactory(4L, 3L, 4L, "That's how I like them!", 4);
    public static Review updatedBigBustyMilfByHughHefner =
            getReviewFactory(4L, 3L, 4L, "Or maybe I like them even more busty!", 3);

    public static ReviewDto bigBustyMilfByHughHefnerDto =
            getReviewDtoFactory(4L, 3L, 4L, "That's how I like them!", 4);
    public static ReviewDto updatedBigBustyMilfByHughHefnerDto =
            getReviewDtoFactory(4L, 3L, 4L, "Or maybe I like them even more busty!", 3);

    public static ReviewSimpleDto bigBustyMilfByHughHefnerSimpleDto =
            getReviewSimpleDtoFactory(3L, 4L, "That's how I like them!", 4);

    public static ReviewPutDto bigBustyMilfByHughHefnerPutDto =
            getReviewPutDtoFactory("Or maybe I like them even more busty!", 3);

    private static Review getReviewFactory(
            Long id, Long contentId, Long userId, String reviewText, Integer stars) {
        Review review = new Review();
        review.setId(id);
        review.setContentId(contentId);
        review.setUserId(userId);
        review.setReview(reviewText);
        review.setStars(stars);
        return review;
    }

    private static ReviewDto getReviewDtoFactory(
            Long id, Long contentId, Long userId, String reviewText, Integer stars) {
        ReviewDto review = new ReviewDto();
        review.setId(id);
        review.setContentId(contentId);
        review.setUserId(userId);
        review.setReview(reviewText);
        review.setStars(stars);
        return review;
    }

    private static ReviewSimpleDto getReviewSimpleDtoFactory(
            Long contentId, Long userId, String reviewText, Integer stars) {
        ReviewSimpleDto review = new ReviewSimpleDto();
        review.setContentId(contentId);
        review.setUserId(userId);
        review.setReview(reviewText);
        review.setStars(stars);
        return review;
    }

    private static ReviewPutDto getReviewPutDtoFactory(String reviewText, Integer stars) {
        ReviewPutDto review = new ReviewPutDto();
        review.setReview(reviewText);
        review.setStars(stars);
        return review;
    }
}
