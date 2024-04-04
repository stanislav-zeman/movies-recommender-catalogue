package dev.cere.reviews.facade;

import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.api.ReviewPutDto;
import dev.cere.reviews.api.ReviewSimpleDto;
import dev.cere.reviews.mappers.ReviewMapper;
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
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewFacade(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
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
        return reviewMapper.mapToDto(reviewService.create(review));
    }

    public ReviewDto update(Long id, ReviewPutDto reviewPutDto) {
        return reviewMapper.mapToDto(reviewService.update(id, reviewPutDto));
    }

    public void delete(Long id) {
        reviewService.delete(id);
    }
}
