package dev.cere.reviews.data.repository;

import dev.cere.reviews.data.entities.Review;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.id = :id")
    Optional<Review> findById(@Param("id") Long id);

    @Query("SELECT r FROM Review r WHERE r.contentId = :id")
    Page<Review> findAllByContentId(@Param("id") Long id, Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.userId = :id")
    Page<Review> findAllByUserId(@Param("id") Long id, Pageable pageable);
}
