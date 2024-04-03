package dev.cere.recommendations.api;

import java.util.List;

public class RecommendationDto {
    private List<Long> recommendedMovies;

    public List<Long> getRecommendedMovies() {
        return recommendedMovies;
    }

    public void setRecommendedMovies(List<Long> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }
}
