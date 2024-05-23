package dev.cere.recommendations.facade;

import dev.cere.messaging.reviews.events.ReviewCreatedEvent;
import dev.cere.messaging.reviews.events.ReviewDeletedEvent;
import dev.cere.messaging.reviews.events.ReviewUpdatedEvent;
import dev.cere.recommendations.mappers.ReviewMessagingMapper;
import dev.cere.recommendations.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewFacade {
    private final ReviewService reviewService;
    private final ReviewMessagingMapper reviewMessagingMapper;

    @Autowired
    public ReviewFacade(ReviewService reviewService, ReviewMessagingMapper reviewMessagingMapper) {
        this.reviewService = reviewService;
        this.reviewMessagingMapper = reviewMessagingMapper;
    }

    public void create(ReviewCreatedEvent event) {
        var review = reviewMessagingMapper.mapFromCreatedEvent(event);
        reviewService.create(review);
    }

    public void update(ReviewUpdatedEvent event) {
        var review = reviewMessagingMapper.mapFromUpdatedEvent(event);
        reviewService.update(event.getId(), review);
    }

    public void delete(ReviewDeletedEvent event) {
        reviewService.delete(event.getId());
    }
}
