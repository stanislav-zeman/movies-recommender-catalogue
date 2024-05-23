package dev.cere.recommendations.facade;

import static org.assertj.core.api.Assertions.assertThat;

import dev.cere.recommendations.api.RecommendationDto;
import dev.cere.recommendations.service.RecommendationService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecommendationFacadeTest {
    @Mock private RecommendationService recommendationService;
    @InjectMocks private RecommendationFacade recommendationFacade;

    @Test
    void findById_movieFound_returnsMovie() {
        // Arrange
        RecommendationDto recommendationDto = new RecommendationDto();
        recommendationDto.setRecommendedMovies(List.of(4L));
        Mockito.when(recommendationService.getRecommendations(1L)).thenReturn(recommendationDto);

        // Act
        RecommendationDto foundEntity = recommendationFacade.getRecommendationsForMovie(1L);

        // Assert
        assertThat(foundEntity.getRecommendedMovies()).isEqualTo(List.of(4L));
    }

    @Test
    void findById_movieNotFound_returnsEmptyList() {
        // Arrange
        RecommendationDto recommendationDto = new RecommendationDto();
        recommendationDto.setRecommendedMovies(List.of());
        Mockito.when(recommendationService.getRecommendations(1L)).thenReturn(recommendationDto);

        // Act
        RecommendationDto foundEntity = recommendationFacade.getRecommendationsForMovie(1L);

        // Assert
        assertThat(foundEntity.getRecommendedMovies()).isEmpty();
    }
}
