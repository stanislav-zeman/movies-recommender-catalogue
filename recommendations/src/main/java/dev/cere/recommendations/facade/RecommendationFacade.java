package dev.cere.recommendations.facade;

import dev.cere.recommendations.api.RecommendationDto;
import dev.cere.recommendations.api.ReviewDto;
import dev.cere.recommendations.api.ReviewPutDto;
import dev.cere.recommendations.api.ReviewSimpleDto;
import dev.cere.recommendations.mappers.ReviewMapper;
import dev.cere.recommendations.service.RecommendationService;
import dev.cere.recommendations.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendationFacade {
    private final RecommendationService recommendationService;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @Autowired
    public RecommendationFacade(
            RecommendationService recommendationService,
            ReviewService reviewService,
            ReviewMapper reviewMapper) {
        this.recommendationService = recommendationService;
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    public RecommendationDto getRecommendationsForMovie(Long movieId) {
        return recommendationService.getRecommendations(movieId);
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
