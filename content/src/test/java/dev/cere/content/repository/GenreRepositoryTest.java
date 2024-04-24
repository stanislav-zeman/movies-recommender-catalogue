package dev.cere.content.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.cere.content.data.model.Genre;
import dev.cere.content.data.repository.GenreRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GenreRepositoryTest {

    @Autowired private GenreRepository genreRepository;

    @Autowired private TestEntityManager testEntityManager;

    @BeforeEach
    void initData() {
        Genre actionGenre = new Genre();
        actionGenre.setName("Sci-fi");
        testEntityManager.persist(actionGenre);
    }

    @Test
    void findByName_genreFound_returnsGenre() {
        // Arrange
        String genre = "Sci-fi";

        // Act
        Optional<Genre> foundEntity = genreRepository.findByName(genre);

        // Assert
        assertTrue(foundEntity.isPresent());
        assertEquals(genre, foundEntity.get().getName());
    }

    @Test
    void findByName_genreNotFound_returnsNothing() {
        // Arrange
        String genre = "NotRealGenre";

        // Act
        Optional<Genre> foundEntity = genreRepository.findByName(genre);

        // Assert
        assertTrue(foundEntity.isEmpty());
    }
}
