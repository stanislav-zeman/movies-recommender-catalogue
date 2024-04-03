package dev.cere.recommendations.rest;

import dev.cere.recommendations.api.RecommendationDto;
import dev.cere.recommendations.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/recommendations")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @Autowired
    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping(path = "/{movieId}")
    public ResponseEntity<RecommendationDto> findById(@PathVariable("movieId") Long movieId) {
        return ResponseEntity.ok(recommendationService.getRecommendations(movieId));
    }
}
