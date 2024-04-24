package dev.cere.content.repository;

import static dev.cere.content.util.TestDataFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dev.cere.content.data.model.Director;
import dev.cere.content.data.model.Genre;
import dev.cere.content.data.model.Movie;
import dev.cere.content.data.repository.MovieRepository;
import dev.cere.content.util.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTest {

    @Autowired private MovieRepository movieRepository;

    @Autowired private TestEntityManager testEntityManager;

    @BeforeEach
    void initData() {
        Genre actionGenre = getGenreFactory(TestDataFactory.actionGenre.getName());
        testEntityManager.persist(actionGenre);

        Genre thrillerGenre = getGenreFactory(TestDataFactory.thrillerGenre.getName());
        testEntityManager.persist(thrillerGenre);

        Genre dramaGenre = getGenreFactory(TestDataFactory.dramaGenre.getName());
        testEntityManager.persist(dramaGenre);

        Director dieHardDirector =
                getDirectorFactory(
                        TestDataFactory.dieHardDirector.getName(),
                        TestDataFactory.dieHardDirector.getDob());
        testEntityManager.persist(dieHardDirector);

        Director dieHard2Director =
                getDirectorFactory(
                        TestDataFactory.dieHard2Director.getName(),
                        TestDataFactory.dieHard2Director.getDob());
        testEntityManager.persist(dieHard2Director);

        Director rainmanDirector =
                getDirectorFactory(
                        TestDataFactory.rainmanDirector.getName(),
                        TestDataFactory.rainmanDirector.getDob());
        testEntityManager.persist(rainmanDirector);

        Movie dieHard =
                getMovieFactory(
                        List.of(actionGenre, thrillerGenre),
                        TestDataFactory.dieHard.getTitle(),
                        TestDataFactory.dieHard.getYear(),
                        TestDataFactory.dieHard.getDescription(),
                        dieHardDirector,
                        TestDataFactory.dieHard.getCast());
        testEntityManager.persist(dieHard);

        Movie dieHard2 =
                getMovieFactory(
                        List.of(actionGenre, thrillerGenre),
                        TestDataFactory.dieHard2.getTitle(),
                        TestDataFactory.dieHard2.getYear(),
                        TestDataFactory.dieHard2.getDescription(),
                        dieHard2Director,
                        TestDataFactory.dieHard2.getCast());
        testEntityManager.persist(dieHard2);

        Movie rainMan =
                getMovieFactory(
                        List.of(dramaGenre),
                        TestDataFactory.rainMan.getTitle(),
                        TestDataFactory.rainMan.getYear(),
                        TestDataFactory.rainMan.getDescription(),
                        rainmanDirector,
                        TestDataFactory.rainMan.getCast());
        testEntityManager.persist(rainMan);
    }

    @Test
    void find_moviesFoundByGenre_returnsMovie() {
        // Arrange
        String genre = "Action";

        // Act
        List<Movie> foundEntities = movieRepository.find(genre, null, null, null);

        // Assert
        assertThat(foundEntities.isEmpty()).isFalse();
        for (Movie movie : foundEntities) {
            boolean containsExpectedGenre =
                    movie.getGenres().stream()
                            .map(Genre::getName)
                            .anyMatch(name -> name.equals(genre));
            assertThat(containsExpectedGenre).isTrue();
        }
    }

    @Test
    void find_moviesFoundByTitle_returnsMovie() {
        // Arrange
        String title = "Rain Man";

        // Act
        List<Movie> foundEntities = movieRepository.find(null, title, null, null);

        // Assert
        assertThat(foundEntities.isEmpty()).isFalse();
        for (Movie movie : foundEntities) {
            assertEquals(title, movie.getTitle());
        }
    }

    @Test
    void find_moviesFoundByYear_returnsMovies() {
        // Arrange
        int year = 1988;

        // Act
        List<Movie> foundEntities = movieRepository.find(null, null, year, null);

        // Assert
        assertThat(foundEntities.isEmpty()).isFalse();
        for (Movie movie : foundEntities) {
            assertEquals(year, movie.getYear());
        }
    }

    @Test
    void find_moviesFoundByDirectorName_returnsMovies() {
        // Arrange
        String directorName = "Barry Levinson";

        // Act
        List<Movie> foundEntities = movieRepository.find(null, null, null, directorName);

        // Assert
        assertThat(foundEntities.isEmpty()).isFalse();
        for (Movie movie : foundEntities) {
            assertEquals(directorName, movie.getDirector().getName());
        }
    }

    @Test
    void find_moviesFound_returnsMovies() {
        // Arrange
        String genre = "Action";
        String title = "Die Hard 2";
        int year = 1990;
        String directorName = "Renny Harlin";

        // Act
        List<Movie> foundEntities = movieRepository.find(genre, title, year, directorName);

        // Assert
        assertThat(foundEntities.isEmpty()).isFalse();
        for (Movie movie : foundEntities) {
            boolean containsExpectedGenre =
                    movie.getGenres().stream()
                            .map(Genre::getName)
                            .anyMatch(name -> name.equals(genre));
            assertThat(containsExpectedGenre).isTrue();
            assertEquals(title, movie.getTitle());
            assertEquals(year, movie.getYear());
            assertEquals(directorName, movie.getDirector().getName());
        }
    }

    @Test
    void find_noMoviesFound_returnsEmptyList() {
        // Arrange
        int year = 1910;

        // Act
        List<Movie> foundEntities = movieRepository.find(null, null, year, null);

        // Assert
        assertThat(foundEntities).isEmpty();
    }

    @Test
    void findByTitlePrefix_moviesFound_returnsMovies() {
        // Arrange
        String titlePrefix = "Die";

        // Act
        List<Movie> foundEntities = movieRepository.findByTitlePrefix(titlePrefix);

        // Assert
        assertThat(foundEntities.isEmpty()).isFalse();
        for (Movie movie : foundEntities) {
            assertThat(movie.getTitle()).startsWith(titlePrefix);
        }
    }
}
