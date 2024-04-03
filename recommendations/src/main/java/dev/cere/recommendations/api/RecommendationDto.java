package dev.cere.recommendations.api;

import java.util.List;

public class RecommendationDto {
    private List<String> recommendedMovies;

    public List<String> getRecommendedMovies() {
        return recommendedMovies;
    }

    public void setRecommendedMovies(List<String> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }
}
