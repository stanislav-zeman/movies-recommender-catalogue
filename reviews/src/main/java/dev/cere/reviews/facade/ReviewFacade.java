package dev.cere.reviews.facade;

import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.api.ReviewPutDto;
import dev.cere.reviews.api.ReviewSimpleDto;
import dev.cere.reviews.mappers.ReviewMapper;
import dev.cere.reviews.messaging.MessageActionType;
import dev.cere.reviews.service.ReviewMessagingService;
import dev.cere.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviewFacade {
    private final ReviewService reviewService;
    private final ReviewMessagingService reviewMessagingService;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewFacade(
            ReviewService reviewService,
            ReviewMessagingService reviewMessagingService,
            ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMessagingService = reviewMessagingService;
        this.reviewMapper = reviewMapper;
    }

    @Transactional(readOnly = true)
    public ReviewDto findById(Long id) {
        return reviewMapper.mapToDto(reviewService.findById(id));
    }

    @Transactional(readOnly = true)
    public Page<ReviewDto> findAll(Pageable pageable) {
        return reviewMapper.mapToPageDto(reviewService.findAll(pageable));
    }

    public ReviewDto create(ReviewSimpleDto reviewSimpleDto) {
        var review = reviewMapper.mapFromSimpleDto(reviewSimpleDto);
        var reviewDto = reviewMapper.mapToDto(reviewService.create(review));
        reviewMessagingService.sendMessage(reviewDto, MessageActionType.CREATE);
        return reviewDto;
    }

    public ReviewDto update(Long id, ReviewPutDto reviewPutDto) {
        var messageDto = reviewMapper.mapToDto(reviewService.update(id, reviewPutDto));
        reviewMessagingService.sendMessage(messageDto, MessageActionType.UPDATE);
        return messageDto;
    }

    public void delete(Long id) {
        reviewService.delete(id);
        reviewMessagingService.sendMessage(id, MessageActionType.DELETE);
    }
}
