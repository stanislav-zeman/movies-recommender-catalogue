package dev.cere.content.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dev.cere.content.data.model.Movie;
import dev.cere.content.data.repository.MovieRepository;
import dev.cere.content.exceptions.ResourceNotFoundException;
import dev.cere.content.util.TestDataFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock private MovieRepository movieRepository;

    @InjectMocks private MovieService movieService;

    @Test
    void findById_movieFound_returnsMovie() {
        // Arrange
        Long id = 1L;
        Mockito.when(movieRepository.findById(id))
                .thenReturn(Optional.ofNullable(TestDataFactory.dieHard));

        // Act
        Movie foundEntity = movieService.findById(id);

        // Assert
        assertThat(foundEntity).isEqualTo(TestDataFactory.dieHard);
    }

    @Test
    void findById_personNotFound_throwsResourceNotFoundException() {
        // Arrange
        Long id = 999L;
        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Exception exception =
                assertThrows(
                        ResourceNotFoundException.class,
                        () ->
                                Optional.ofNullable(movieService.findById(id))
                                        .orElseThrow(ResourceNotFoundException::new));

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Movie with id: " + id + " was not found.");
    }

    @Test
    void find_moviesFoundByGenre_returnsMovies() {
        // Arrange
        String genre = "Action";
        Mockito.when(movieRepository.find(genre, null, null, null))
                .thenReturn(TestDataFactory.actionMovies);

        // Act
        List<Movie> foundEntities = movieService.find(genre, null, null, null);

        // Assert
        assertThat(foundEntities).isEqualTo(TestDataFactory.actionMovies);
    }

    @Test
    void find_moviesFoundByTitle_returnsMovie() {
        // Arrange
        String title = "Rain Man";
        Mockito.when(movieRepository.find(null, title, null, null))
                .thenReturn(List.of(TestDataFactory.rainMan));

        // Act
        List<Movie> foundEntities = movieService.find(null, title, null, null);

        // Assert
        assertThat(foundEntities).isEqualTo(List.of(TestDataFactory.rainMan));
    }

    @Test
    void find_moviesFoundByYear_returnsMovies() {
        // Arrange
        int year = 1988;
        Mockito.when(movieRepository.find(null, null, year, null))
                .thenReturn(TestDataFactory.moviesOf1988);

        // Act
        List<Movie> foundEntities = movieService.find(null, null, year, null);

        // Assert
        assertThat(foundEntities).isEqualTo(TestDataFactory.moviesOf1988);
    }

    @Test
    void find_moviesFoundByDirectorName_returnsMovies() {
        // Arrange
        String directorName = "Barry Levinson";
        Mockito.when(movieRepository.find(null, null, null, directorName))
                .thenReturn(List.of(TestDataFactory.rainMan));

        // Act
        List<Movie> foundEntities = movieService.find(null, null, null, directorName);

        // Assert
        assertThat(foundEntities).isEqualTo(List.of(TestDataFactory.rainMan));
    }

    @Test
    void find_moviesFound_returnsMovies() {
        // Arrange
        String genre = "Action";
        String title = "Die Hard 2";
        int year = 1990;
        String directorName = "Renny Harlin";
        Mockito.when(movieRepository.find(genre, title, year, directorName))
                .thenReturn(List.of(TestDataFactory.dieHard2));

        // Act
        List<Movie> foundEntities = movieService.find(genre, title, year, directorName);

        // Assert
        assertThat(foundEntities).isEqualTo(List.of(TestDataFactory.dieHard2));
    }

    @Test
    void find_noMoviesFound_returnsEmptyList() {
        // Arrange
        int year = 1910;
        Mockito.when(movieRepository.find(null, null, year, null)).thenReturn(new ArrayList<>());

        // Act
        List<Movie> foundEntities = movieService.find(null, null, year, null);

        // Assert
        assertThat(foundEntities).isEmpty();
    }

    @Test
    void findByTitlePrefix_moviesFound_returnsMovies() {
        // Arrange
        String titlePrefix = "Die";
        Mockito.when(movieRepository.findByTitlePrefix(titlePrefix))
                .thenReturn(TestDataFactory.dieHardMovies);

        // Act
        List<Movie> foundEntities = movieService.findByTitlePrefix(titlePrefix);

        // Assert
        assertThat(foundEntities).isEqualTo(TestDataFactory.dieHardMovies);
    }

    @Test
    void updateMovie_movieNotFound_throwsResourceNotFoundException() {
        // Arrange
        Long id = 999L;
        Movie movie = new Movie();

        // Act
        Exception exception =
                assertThrows(
                        ResourceNotFoundException.class, () -> movieService.updateMovie(id, movie));

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Movie with id: " + id + " was not found.");
    }

    @Test
    void removeMovie_movieNotFound_throwsResourceNotFoundException() {
        // Arrange
        Long id = 999L;

        // Act
        Exception exception =
                assertThrows(ResourceNotFoundException.class, () -> movieService.removeMovie(id));

        // Assert
        assertThat(exception.getMessage()).isEqualTo("Movie with id: " + id + " was not found.");
    }
}
