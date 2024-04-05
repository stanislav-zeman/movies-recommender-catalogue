package dev.cere.reviews.rest;

import static org.assertj.core.api.Assertions.assertThat;

import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.facade.ReviewFacade;
import dev.cere.reviews.util.TestDataFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ReviewRestControllerTest {
    @Mock private ReviewFacade reviewFacade;

    @InjectMocks private ReviewRestController reviewRestController;

    @Test
    void findById_reviewFound_returnsReview() {
        Long id = 4L;
        Mockito.when(reviewFacade.findById(id))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefnerDto);

        ResponseEntity<ReviewDto> response = reviewRestController.findById(id);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestDataFactory.bigBustyMilfByHughHefnerDto);
    }

    @Test
    void create_reviewCreated_returnCreatedReview() {
        Mockito.when(reviewFacade.create(TestDataFactory.bigBustyMilfByHughHefnerSimpleDto))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefnerDto);

        ResponseEntity<ReviewDto> response =
                reviewRestController.create(TestDataFactory.bigBustyMilfByHughHefnerSimpleDto);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(TestDataFactory.bigBustyMilfByHughHefnerDto);
    }

    @Test
    void update_reviewUpdated_returnsUpdatedReview() {
        Long id = 4L;
        Mockito.when(reviewFacade.update(id, TestDataFactory.bigBustyMilfByHughHefnerPutDto))
                .thenReturn(TestDataFactory.bigBustyMilfByHughHefnerDto);

        ResponseEntity<ReviewDto> response =
                reviewRestController.updateById(id, TestDataFactory.bigBustyMilfByHughHefnerPutDto);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestDataFactory.bigBustyMilfByHughHefnerDto);
    }

    @Test
    void delete_reviewDeleted_returnsVoid() {
        Long id = 4L;
        Mockito.doNothing().when(reviewFacade).delete(id);

        ResponseEntity<Void> response = reviewRestController.removeById(id);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
