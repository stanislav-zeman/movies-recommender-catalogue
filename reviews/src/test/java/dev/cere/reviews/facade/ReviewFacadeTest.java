package dev.cere.reviews.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.mappers.ReviewMapper;
import dev.cere.reviews.service.ReviewService;
import dev.cere.reviews.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ReviewFacadeTest {
    @Mock private ReviewService reviewService;

    @Mock private ReviewMapper reviewMapper;

    @InjectMocks private ReviewFacade reviewFacade;

    @Test
    void findById_reviewFound_returnsReview() {
        Long id = 4L;
        Mockito.when(reviewService.findById(id))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefner);
        Mockito.when(reviewMapper.mapToDto(any()))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefnerDto);

        ReviewDto foundEntity = reviewFacade.findById(id);

        assertThat(foundEntity).isEqualTo(TestDataFactory.bigBustyMilfByHughHefnerDto);
    }

    @Test
    void create_reviewCreated_returnsCreatedReview() {
        Mockito.when(reviewService.create(TestDataFactory.bigBustyMilfByHughHefner))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefner);
        Mockito.when(reviewMapper.mapFromSimpleDto(any()))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefner);
        Mockito.when(reviewMapper.mapToDto(any()))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefnerDto);

        ReviewDto review = reviewFacade.create(TestDataFactory.bigBustyMilfByHughHefnerSimpleDto);

        assertThat(review).isEqualTo(TestDataFactory.bigBustyMilfByHughHefnerDto);
    }

    @Test
    void update_reviewUpdated_returnsUpdatedReview() {
        Long id = 4L;
        Mockito.when(reviewService.update(id, TestDataFactory.bigBustyMilfByHughHefnerPutDto))
                .thenReturn(TestDataFactory.updatedBigBustyMilfByHughHefner);
        Mockito.when(reviewMapper.mapToDto(any()))
                .thenReturn(TestDataFactory.updatedBigBustyMilfByHughHefnerDto);

        ReviewDto review = reviewFacade.update(id, TestDataFactory.bigBustyMilfByHughHefnerPutDto);

        assertThat(review).isEqualTo(TestDataFactory.updatedBigBustyMilfByHughHefnerDto);
    }

    @Test
    void delete_reviewDeleted_returnsNothing() {
        Long id = 4L;
        Mockito.doNothing().when(reviewService).delete(id);

        reviewFacade.delete(id);
    }
}
