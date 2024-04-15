package dev.cere.content.data.repository;

import dev.cere.content.data.model.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(
            "SELECT DISTINCT m FROM Movie m LEFT JOIN m.genres g WHERE (:genre IS NULL OR :genre ="
                + " '' OR g.name = :genre) AND (:title IS NULL OR :title = '' OR m.title = :title)"
                + " AND (:year IS NULL OR m.year = :year) AND (:directorName IS NULL OR"
                + " :directorName = '' OR m.director.name = :directorName)")
    List<Movie> find(
            @Param("genre") String genre,
            @Param("title") String title,
            @Param("year") Integer year,
            @Param("directorName") String directorName);

    @Query("SELECT m FROM Movie m WHERE m.title LIKE CONCAT(:titlePrefix, '%')")
    List<Movie> findByTitlePrefix(@Param("titlePrefix") String titlePrefix);
}
