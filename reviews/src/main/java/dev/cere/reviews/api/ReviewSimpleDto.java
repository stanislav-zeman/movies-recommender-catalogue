package dev.cere.reviews.api;

public class ReviewSimpleDto {
    private Long contentId;
    private Long userId;
    private String review;
    private Integer stars;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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
        return "ReviewDto{"
                + "contentId="
                + contentId
                + ", userId="
                + userId
                + ", review='"
                + review
                + '\''
                + ", stars="
                + stars
                + '}';
    }
}
