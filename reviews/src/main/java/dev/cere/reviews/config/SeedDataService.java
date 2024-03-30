package dev.cere.reviews.config;

import dev.cere.reviews.data.entities.Review;
import dev.cere.reviews.data.repository.ReviewRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SeedDataService {
    private final ReviewRepository reviewRepository;

    @Autowired
    public SeedDataService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @PostConstruct
    public void insertData() {
        insertReviews();
    }

    private void insertReviews() {
        Review review1 = new Review();
        review1.setReview("This is a great review.");
        review1.setStars(5);
        review1.setContentId(1L);
        review1.setUserId(1L);

        Review review2 = new Review();
        review2.setReview("This is a not so bad review.");
        review2.setStars(4);
        review2.setContentId(1L);
        review2.setUserId(2L);

        Review review3 = new Review();
        review3.setReview("This is a bad review.");
        review3.setStars(2);
        review3.setContentId(2L);
        review3.setUserId(3L);

        reviewRepository.saveAll(List.of(review1, review2, review3));
    }
}
