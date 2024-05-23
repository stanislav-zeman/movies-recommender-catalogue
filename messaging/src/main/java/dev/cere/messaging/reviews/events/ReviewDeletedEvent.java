package dev.cere.messaging.reviews.events;

import java.time.Instant;

public class ReviewDeletedEvent {
    private Long id;
    private Instant timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ReviewDeleted{" + "id=" + id + ", timestamp=" + timestamp + '}';
    }
}
