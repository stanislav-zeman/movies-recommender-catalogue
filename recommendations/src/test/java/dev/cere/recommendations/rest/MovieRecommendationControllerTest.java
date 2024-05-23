package dev.cere.recommendations.rest;

import static org.assertj.core.api.Assertions.assertThat;

import dev.cere.recommendations.api.RecommendationDto;
import dev.cere.recommendations.facade.RecommendationFacade;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class MovieRecommendationControllerTest {
    @Mock private RecommendationFacade recommendationFacade;

    @InjectMocks private RecommendationController movieRecommendationController;

    // it is tested by the RecommendationServiceTest
    // Because the RecommendationServiceTest tests the RecommendationService, which is used by the
    // RecommendationController, the RecommendationController is also tested.
    @Test
    public void testRecommendByMovieId() {
        // Arrange
        RecommendationDto recommendationDto = new RecommendationDto();
        recommendationDto.setRecommendedMovies(List.of(4L));
        Mockito.when(recommendationFacade.getRecommendationsForMovie(1L))
                .thenReturn(recommendationDto);

        // Act
        ResponseEntity<RecommendationDto> response = movieRecommendationController.findById(1L);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getRecommendedMovies()).isEqualTo(List.of(4L));
    }

    @Test
    public void testRecommendByMovieIdNotFound() {
        // Arrange
        RecommendationDto recommendationDto = new RecommendationDto();
        recommendationDto.setRecommendedMovies(List.of());
        Mockito.when(recommendationFacade.getRecommendationsForMovie(1L))
                .thenReturn(recommendationDto);

        // Act
        ResponseEntity<RecommendationDto> response = movieRecommendationController.findById(1L);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getRecommendedMovies()).isEmpty();
    }
}
