package dev.cere.recommendations.service;

import static org.assertj.core.api.Assertions.assertThat;

import dev.cere.recommendations.api.RecommendationDto;
import dev.cere.recommendations.data.repository.ReviewRepository;
import dev.cere.recommendations.util.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest {
    @Mock private ReviewRepository reviewRepository;
    @InjectMocks private RecommendationService recommendationService;

    @Test
    void recommendByMovieId_recommendationFound_returnsRecommendationListWithOneMovie() {
        // Arrange
        Mockito.when(reviewRepository.findAll()).thenReturn(TestDataFactory.getReviewListFactory());

        // Act
        RecommendationDto recommendationDto = recommendationService.getRecommendations(1L);

        var x = recommendationDto.getRecommendedMovies();

        // Assert
        assertThat(recommendationDto.getRecommendedMovies()).isEqualTo(List.of(4L));
    }

    @Test
    void recommendByMovieId_recommendationFound_returnsRecommendationListWithMultipleMovies() {
        // Arrange
        Mockito.when(reviewRepository.findAll()).thenReturn(TestDataFactory.getReviewListFactory());

        // Act
        RecommendationDto recommendationDto = recommendationService.getRecommendations(95L);

        // Assert
        assertThat(recommendationDto.getRecommendedMovies()).containsOnly(96L, 97L);
    }

    @Test
    void recommendByMovieId_recommendationNotFound_returnsEmptyRecommendationList() {
        // Arrange
        Mockito.when(reviewRepository.findAll()).thenReturn(TestDataFactory.getReviewListFactory());

        // Act
        RecommendationDto recommendationDto = recommendationService.getRecommendations(99L);

        // Assert
        assertThat(recommendationDto.getRecommendedMovies()).isEmpty();
    }
}
