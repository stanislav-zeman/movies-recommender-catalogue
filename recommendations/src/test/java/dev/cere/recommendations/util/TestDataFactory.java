package dev.cere.recommendations.util;

import dev.cere.recommendations.api.ReviewDto;
import dev.cere.recommendations.api.ReviewPutDto;
import dev.cere.recommendations.api.ReviewSimpleDto;
import dev.cere.recommendations.data.review.Review;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestDataFactory {
    public static Review fiftyShadesOfGreyByBob = getReviewFactory(1L, 1L, 1L, "I loved it!", 5);
    public static Review fiftyShadesOfGreyByRob = getReviewFactory(2L, 1L, 2L, "I hated it!", 1);
    public static Review sashaGreyAdventuresBySandra =
            getReviewFactory(3L, 2L, 3L, "Sasha is so hot! I could not hold myself back!", 5);
    public static Review bigBustyMilfByHughHefner =
            getReviewFactory(4L, 3L, 4L, "That's how I like them!", 4);

    public static Review michelleObamaByBarackObama =
            getReviewFactory(5L, 4L, 1L, "I am so proud of my wife!", 5);

    public static Review michelleObamaByJoeBiden =
            getReviewFactory(6L, 4L, 1L, "I am so proud of my friend's wife!", 5);

    public static Review michelleObamaByDonaldTrump =
            getReviewFactory(7L, 4L, 1L, "I am so proud of my friend's wife!", 5);

    public static Review fromRaganToRichesByRaganFox =
            getReviewFactory(8L, 95L, 95L, "I am so proud of myself!", 5);

    public static Review fromRaganToRichesByRaganFox2 =
            getReviewFactory(18L, 96L, 95L, "I am so proud of myself!", 5);

    public static Review fromStanfordToStartupByPeterThiel =
            getReviewFactory(19L, 95L, 96L, "I am so proud of myself!", 5);

    public static Review fromStanfordToStartupByPeterThiel2 =
            getReviewFactory(10L, 97L, 96L, "I am so proud of myself!", 5);

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

    public static List<Review> getReviewListFactory() {
        return List.of(
                fiftyShadesOfGreyByBob,
                fiftyShadesOfGreyByRob,
                sashaGreyAdventuresBySandra,
                bigBustyMilfByHughHefner,
                michelleObamaByBarackObama,
                michelleObamaByJoeBiden,
                michelleObamaByDonaldTrump,
                fromRaganToRichesByRaganFox,
                fromRaganToRichesByRaganFox2,
                fromStanfordToStartupByPeterThiel,
                fromStanfordToStartupByPeterThiel2);
    }

    public static Review getReviewFactory(
            Long id, Long contentId, Long userId, String reviewText, Integer stars) {
        Review review = new Review();
        review.setId(id);
        review.setContentId(contentId);
        review.setUserId(userId);
        review.setStars(stars);
        return review;
    }

    public static ReviewDto getReviewDtoFactory(
            Long id, Long contentId, Long userId, String reviewText, Integer stars) {
        ReviewDto review = new ReviewDto();
        review.setId(id);
        review.setContentId(contentId);
        review.setUserId(userId);
        review.setReview(reviewText);
        review.setStars(stars);
        return review;
    }

    public static ReviewSimpleDto getReviewSimpleDtoFactory(
            Long contentId, Long userId, String reviewText, Integer stars) {
        ReviewSimpleDto review = new ReviewSimpleDto();
        review.setContentId(contentId);
        review.setUserId(userId);
        review.setReview(reviewText);
        review.setStars(stars);
        return review;
    }

    public static ReviewPutDto getReviewPutDtoFactory(String reviewText, Integer stars) {
        ReviewPutDto review = new ReviewPutDto();
        review.setReview(reviewText);
        review.setStars(stars);
        return review;
    }
}
