package dev.cere.content.facade;

import dev.cere.content.api.Movie.MovieDto;
import dev.cere.content.api.Movie.MovieSimpleDto;
import dev.cere.content.data.model.Movie;
import dev.cere.content.mappers.MovieMapper;
import dev.cere.content.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieFacade {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieFacade(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    // CREATE
    public void addMovie(MovieSimpleDto movieSimpleDto) {
        Movie newMovie = movieMapper.mapToMovie(movieSimpleDto);
        movieService.addMovie(newMovie);
    }

    // READ
    public List<MovieDto> findAll() {
        List<Movie> movies = movieService.findAll();
        return movieMapper.mapToDtoList(movies);
    }

    public MovieDto findById(Long id) {
        Movie movie = movieService.findById(id);
        return movieMapper.mapToDto(movie);
    }

    public List<MovieDto> find(String genre, String title, Integer year, String directorName) {
        List<Movie> movies = movieService.find(genre, title, year, directorName);
        return movieMapper.mapToDtoList(movies);
    }

    public List<MovieDto> findByTitlePrefix(String titlePrefix) {
        List<Movie> movies = movieService.findByTitlePrefix(titlePrefix);
        return movieMapper.mapToDtoList(movies);
    }

    // UPDATE
    public void updateMovie(Long id, MovieDto updatedMovieDto) {
        Movie movie = movieMapper.mapToMovie(updatedMovieDto);
        movieService.updateMovie(id, movie);
    }

    // DELETE
    public void removeMovie(Long id) {
        movieService.removeMovie(id);
    }
}
