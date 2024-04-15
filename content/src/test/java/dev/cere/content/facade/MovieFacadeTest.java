package dev.cere.content.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

import dev.cere.content.api.Movie.MovieDto;
import dev.cere.content.mappers.MovieMapper;
import dev.cere.content.service.MovieService;
import dev.cere.content.util.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MovieFacadeTest {
    @Mock private MovieService movieService;

    @Mock private MovieMapper movieMapper;

    @InjectMocks private MovieFacade movieFacade;

    @Test
    void findById_movieFound_returnsMovie() {
        // Arrange
        Mockito.when(movieService.findById(1L)).thenReturn(TestDataFactory.dieHard);
        Mockito.when(movieMapper.mapToDto(any())).thenReturn(TestDataFactory.dieHardDto);

        // Act
        MovieDto foundEntity = movieFacade.findById(1L);

        // Assert
        assertThat(foundEntity).isEqualTo(TestDataFactory.dieHardDto);
    }

    @Test
    void find_moviesFoundByGenre_returnsMovies() {
        // Arrange
        String genre = "Action";
        Mockito.when(movieService.find(genre, null, null, null))
                .thenReturn(TestDataFactory.actionMovies);
        Mockito.when(movieMapper.mapToDtoList(any())).thenReturn(TestDataFactory.actionMoviesDtos);

        // Act
        List<MovieDto> foundEntities = movieFacade.find(genre, null, null, null);

        // Assert
        assertThat(foundEntities).isEqualTo(TestDataFactory.actionMoviesDtos);
    }

    @Test
    void find_moviesFoundByTitle_returnsMovie() {
        // Arrange
        String title = "Rain Man";
        Mockito.when(movieService.find(null, title, null, null))
                .thenReturn(List.of(TestDataFactory.rainMan));
        Mockito.when(movieMapper.mapToDtoList(any()))
                .thenReturn(List.of(TestDataFactory.rainManDto));

        // Act
        List<MovieDto> foundEntities = movieFacade.find(null, title, null, null);

        // Assert
        assertThat(foundEntities).isEqualTo(List.of(TestDataFactory.rainManDto));
    }

    @Test
    void find_moviesFoundByYear_returnsMovies() {
        // Arrange
        int year = 1988;
        Mockito.when(movieService.find(null, null, year, null))
                .thenReturn(TestDataFactory.moviesOf1988);
        Mockito.when(movieMapper.mapToDtoList(any())).thenReturn(TestDataFactory.moviesOf1988Dtos);

        // Act
        List<MovieDto> foundEntities = movieFacade.find(null, null, year, null);

        // Assert
        assertThat(foundEntities).isEqualTo(TestDataFactory.moviesOf1988Dtos);
    }

    @Test
    void find_moviesFoundByDirectorName_returnsMovies() {
        // Arrange
        String directorName = "Barry Levinson";
        Mockito.when(movieService.find(null, null, null, directorName))
                .thenReturn(List.of(TestDataFactory.rainMan));
        Mockito.when(movieMapper.mapToDtoList(any()))
                .thenReturn(List.of(TestDataFactory.rainManDto));

        // Act
        List<MovieDto> foundEntities = movieFacade.find(null, null, null, directorName);

        // Assert
        assertThat(foundEntities).isEqualTo(List.of(TestDataFactory.rainManDto));
    }

    @Test
    void find_moviesFound_returnsMovies() {
        // Arrange
        String genre = "Action";
        String title = "Die Hard 2";
        int year = 1988;
        String directorName = "Renny Harlin";
        Mockito.when(movieService.find(genre, title, year, directorName))
                .thenReturn(List.of(TestDataFactory.dieHard2));
        Mockito.when(movieMapper.mapToDtoList(any()))
                .thenReturn(List.of(TestDataFactory.dieHard2Dto));

        // Act
        List<MovieDto> foundEntities = movieFacade.find(genre, title, year, directorName);

        // Assert
        assertThat(foundEntities).isEqualTo(List.of(TestDataFactory.dieHard2Dto));
    }

    @Test
    void findByTitlePrefix_moviesFound_returnsMovies() {
        // Arrange
        String titlePrefix = "Die";
        Mockito.when(movieService.findByTitlePrefix(titlePrefix))
                .thenReturn(TestDataFactory.dieHardMovies);
        Mockito.when(movieMapper.mapToDtoList(any())).thenReturn(TestDataFactory.dieHardMoviesDtos);

        // Act
        List<MovieDto> foundEntities = movieFacade.findByTitlePrefix(titlePrefix);

        // Assert
        assertThat(foundEntities).isEqualTo(TestDataFactory.dieHardMoviesDtos);
    }

    @Test
    void updateMovie_validId_exceptionNotThrown() {
        // Arrange
        Long id = 1L;
        Mockito.when(movieService.updateMovie(id, TestDataFactory.dieHard))
                .thenReturn(TestDataFactory.dieHard);
        Mockito.when(movieMapper.mapToMovie((MovieDto) any())).thenReturn(TestDataFactory.dieHard);

        // Act
        try {
            movieFacade.updateMovie(id, TestDataFactory.dieHardDto);
        } catch (Exception e) {
            fail("No exception should be thrown");
        }

        // Assert
        Mockito.verify(movieService, Mockito.times(1)).updateMovie(id, TestDataFactory.dieHard);
    }
}
