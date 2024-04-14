package dev.cere.recommendations.service;

import dev.cere.recommendations.api.ReviewPutDto;
import dev.cere.recommendations.data.repository.ReviewRepository;
import dev.cere.recommendations.data.review.Review;
import dev.cere.recommendations.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional
    public Review update(Long id, ReviewPutDto reviewPutDto) {
        var review =
                reviewRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Review with id: " + id + " was not " + "found."));
        review.setStars(reviewPutDto.getStars());
        reviewRepository.save(review);
        return review;
    }

    @Transactional
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }
}
