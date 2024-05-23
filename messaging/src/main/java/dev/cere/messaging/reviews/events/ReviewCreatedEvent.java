package dev.cere.messaging.reviews.events;

import java.time.Instant;

public class ReviewCreatedEvent {
    private Long id;
    private Long contentId;
    private Long userId;
    private String review;
    private Integer stars;
    private Instant timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ReviewCreated{"
                + "id="
                + id
                + ", contentId="
                + contentId
                + ", userId="
                + userId
                + ", review='"
                + review
                + '\''
                + ", stars="
                + stars
                + ", timestamp="
                + timestamp
                + '}';
    }
}
