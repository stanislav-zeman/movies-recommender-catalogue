package dev.cere.reviews.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.cere.reviews.data.entities.Review;
import dev.cere.reviews.data.repository.ReviewRepository;
import dev.cere.reviews.exceptions.ResourceNotFoundException;
import dev.cere.reviews.util.TestDataFactory;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewsServiceTest {
    @Mock private ReviewRepository reviewRepository;

    @InjectMocks private ReviewService reviewService;

    @Test
    void findById_reviewFound_returnsReview() {
        Mockito.when(reviewRepository.findById(4L))
                .thenReturn(Optional.ofNullable(TestDataFactory.bigBustyMilfByHughHefner));

        Review foundEntity = reviewService.findById(4L);

        assertThat(foundEntity).isEqualTo(TestDataFactory.bigBustyMilfByHughHefner);
    }

    @Test
    void findById_reviewNotFound_throwsResourceNotFoundException() {
        Long invalidId = 69L;

        Exception exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () ->
                                Optional.ofNullable(reviewService.findById(invalidId))
                                        .orElseThrow(ResourceNotFoundException::new));

        assertThat(exception.getMessage())
                .isEqualTo("Review with id: " + invalidId + " was not found.");
    }

    @Test
    void create_reviewCreate_returnsReview() {
        Mockito.when(reviewRepository.save(TestDataFactory.bigBustyMilfByHughHefner))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefner);

        Review foundEntity = reviewService.create(TestDataFactory.bigBustyMilfByHughHefner);

        assertThat(foundEntity).isEqualTo(TestDataFactory.bigBustyMilfByHughHefner);
    }

    @Test
    void update_reviewUpdated_returnsUpdatedReview() {
        Mockito.when(reviewRepository.findById(4L))
                .thenReturn(Optional.ofNullable(TestDataFactory.bigBustyMilfByHughHefner));

        Review updatedEntity =
                reviewService.update(4L, TestDataFactory.bigBustyMilfByHughHefnerPutDto);

        assertThat(updatedEntity).isEqualTo(TestDataFactory.updatedBigBustyMilfByHughHefner);
    }

    @Test
    void delete_reviewDeleted_returnsVoid() {
        Mockito.doNothing().when(reviewRepository).deleteById(4L);

        reviewService.delete(4L);
    }
}
