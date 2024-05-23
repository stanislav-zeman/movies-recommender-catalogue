package dev.cere.reviews.mappers;

import dev.cere.messaging.reviews.events.ReviewCreatedEvent;
import dev.cere.messaging.reviews.events.ReviewDeletedEvent;
import dev.cere.messaging.reviews.events.ReviewUpdatedEvent;
import dev.cere.reviews.api.ReviewDto;
import java.time.Instant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMessagingMapper {
    default ReviewCreatedEvent mapToCreatedEvent(ReviewDto review) {
        var event = new ReviewCreatedEvent();
        event.setId(review.getId());
        event.setContentId(review.getContentId());
        event.setUserId(review.getUserId());
        event.setReview(review.getReview());
        event.setStars(review.getStars());
        event.setTimestamp(Instant.now());
        return event;
    }

    default ReviewUpdatedEvent mapToUpdatedEvent(ReviewDto review) {
        var event = new ReviewUpdatedEvent();
        event.setId(review.getId());
        event.setContentId(review.getContentId());
        event.setUserId(review.getUserId());
        event.setReview(review.getReview());
        event.setStars(review.getStars());
        event.setTimestamp(Instant.now());
        return event;
    }

    default ReviewDeletedEvent mapToDeletedEvent(Long id) {
        var event = new ReviewDeletedEvent();
        event.setId(id);
        event.setTimestamp(Instant.now());
        return event;
    }
}
