package dev.cere.reviews.service;

import dev.cere.reviews.data.entities.Review;
import dev.cere.reviews.data.repository.ReviewRepository;
import dev.cere.reviews.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    public Review findById(Long id) {
        return reviewRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Review with id: " + id + " was not " + "found."));
    }

    @Transactional(readOnly = true)
    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }
}
