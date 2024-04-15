package dev.cere.content.rest;

import static org.assertj.core.api.Assertions.assertThat;

import dev.cere.content.api.Movie.MovieDto;
import dev.cere.content.facade.MovieFacade;
import dev.cere.content.util.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class MovieRestControllerTest {
    @Mock private MovieFacade movieFacade;

    @InjectMocks private MovieRestController movieRestController;

    @Test
    void findById_movieFound_returnsMovie() {
        // Arrange
        Long id = 1L;
        Mockito.when(movieFacade.findById(id)).thenReturn(TestDataFactory.dieHardDto);

        // Act
        ResponseEntity<MovieDto> response = movieRestController.findById(id);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestDataFactory.dieHardDto);
    }

    @Test
    void find_moviesFoundByGenre_returnsMovies() {
        // Arrange
        String genre = "Action";
        Mockito.when(movieFacade.find(genre, null, null, null))
                .thenReturn(TestDataFactory.actionMoviesDtos);

        // Act
        ResponseEntity<List<MovieDto>> response = movieRestController.find(genre, null, null, null);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestDataFactory.actionMoviesDtos);
    }

    @Test
    void find_moviesFoundByTitle_returnsMovie() {
        // Arrange
        String title = "Rain Man";
        Mockito.when(movieFacade.find(null, title, null, null))
                .thenReturn(List.of(TestDataFactory.rainManDto));

        // Act
        ResponseEntity<List<MovieDto>> response = movieRestController.find(null, title, null, null);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(List.of(TestDataFactory.rainManDto));
    }

    @Test
    void find_moviesFoundByYear_returnsMovies() {
        // Arrange
        int year = 1988;
        Mockito.when(movieFacade.find(null, null, year, null))
                .thenReturn(TestDataFactory.moviesOf1988Dtos);

        // Act
        ResponseEntity<List<MovieDto>> response = movieRestController.find(null, null, year, null);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestDataFactory.moviesOf1988Dtos);
    }

    @Test
    void find_moviesFoundByDirectorName_returnsMovies() {
        // Arrange
        String directorName = "Barry Levinson";
        Mockito.when(movieFacade.find(null, null, null, directorName))
                .thenReturn(List.of(TestDataFactory.rainManDto));

        // Act
        ResponseEntity<List<MovieDto>> response =
                movieRestController.find(null, null, null, directorName);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(List.of(TestDataFactory.rainManDto));
    }

    @Test
    void find_moviesFound_returnsMovies() {
        // Arrange
        String genre = "Action";
        String title = "Die Hard 2";
        int year = 1988;
        String directorName = "Renny Harlin";
        Mockito.when(movieFacade.find(genre, title, year, directorName))
                .thenReturn(List.of(TestDataFactory.dieHard2Dto));

        // Act
        ResponseEntity<List<MovieDto>> response =
                movieRestController.find(genre, title, year, directorName);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(List.of(TestDataFactory.dieHard2Dto));
    }

    @Test
    void findByTitlePrefix_moviesFound_returnsMovies() {
        // Arrange
        String titlePrefix = "Die";
        Mockito.when(movieFacade.findByTitlePrefix(titlePrefix))
                .thenReturn(TestDataFactory.dieHardMoviesDtos);

        // Act
        ResponseEntity<List<MovieDto>> response =
                movieRestController.findByTitlePrefix(titlePrefix);

        // Assert
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(TestDataFactory.dieHardMoviesDtos);
    }

    @Test
    void updateMovie_validRequestBody_callsUpdateOnFacade() {
        // Arrange
        Long id = 1L;
        Mockito.when(movieFacade.updateMovie(id, TestDataFactory.dieHardDto))
                .thenReturn(TestDataFactory.dieHardDto);

        // Act
        ResponseEntity<MovieDto> response =
                movieRestController.updateMovie(id, TestDataFactory.dieHardDto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Mockito.verify(movieFacade, Mockito.times(1)).updateMovie(id, TestDataFactory.dieHardDto);
    }

    @Test
    void removeMovie_validRequestBody_callsRemoveOnFacade() {
        // Arrange
        Long id = 1L;
        Mockito.doNothing().when(movieFacade).removeMovie(1L);

        // Act
        ResponseEntity<Void> response = movieRestController.removeMovie(id);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Mockito.verify(movieFacade, Mockito.times(1)).removeMovie(id);
    }
}
