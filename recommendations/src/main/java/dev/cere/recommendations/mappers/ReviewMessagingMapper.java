package dev.cere.recommendations.mappers;

import dev.cere.messaging.reviews.events.ReviewCreatedEvent;
import dev.cere.messaging.reviews.events.ReviewUpdatedEvent;
import dev.cere.recommendations.api.ReviewPutDto;
import dev.cere.recommendations.data.review.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMessagingMapper {
    Review mapFromCreatedEvent(ReviewCreatedEvent event);

    ReviewPutDto mapFromUpdatedEvent(ReviewUpdatedEvent event);
}
