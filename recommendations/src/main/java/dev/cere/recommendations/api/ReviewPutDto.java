package dev.cere.recommendations.api;

public class ReviewPutDto {
    private String review;
    private Integer stars;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "ReviewPutDto{" + "review='" + review + '\'' + ", stars=" + stars + '}';
    }
}
