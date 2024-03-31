package dev.cere.content.service;

import dev.cere.content.data.model.Director;
import dev.cere.content.data.model.Genre;
import dev.cere.content.data.model.Movie;
import dev.cere.content.data.repository.DirectorRepository;
import dev.cere.content.data.repository.GenreRepository;
import dev.cere.content.data.repository.MovieRepository;
import dev.cere.content.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;

    @Autowired
    public MovieService(
            MovieRepository movieRepository,
            GenreRepository genreRepository,
            DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
    }

    // CREATE
    public void addMovie(Movie newMovie) {
        Random random = new Random();
        Director newMovieDirector = newMovie.getDirector();
        Director director =
                directorRepository.find(newMovieDirector.getName(), newMovieDirector.getDob());

        if (director == null) {
            newMovieDirector.setId(random.nextLong(10, 100));
            directorRepository.addDirector(newMovieDirector);
        } else {
            newMovie.setDirector(director);
        }

        List<Genre> newMovieGenres = newMovie.getGenres();
        for (Genre newMovieGenre : newMovieGenres) {
            Genre genre = genreRepository.find(newMovieGenre.getName());
            if (genre == null) {
                newMovieGenre.setId(random.nextLong(10, 100));
                genreRepository.addGenre(newMovieGenre);
            } else {
                newMovieGenre.setId(genre.getId());
            }
        }

        newMovie.setId(random.nextLong(10, 100));
        movieRepository.addMovie(newMovie);
    }

    // READ
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long id) {
        return movieRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ResourceNotFoundException(
                                        "Movie with id: " + id + " was not found."));
    }

    public List<Movie> find(String genre, String title, Integer year, String directorName) {
        return movieRepository.find(genre, title, year, directorName);
    }

    public List<Movie> findByTitlePrefix(String titlePrefix) {
        return movieRepository.findByTitlePrefix(titlePrefix);
    }

    // UPDATE
    public void updateMovie(Long id, Movie updatedMovie) {
        Movie movie =
                movieRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Movie with id: " + id + " was not found."));

        movie.setGenres(updatedMovie.getGenres());
        movie.setTitle(updatedMovie.getTitle());
        movie.setYear(updatedMovie.getYear());
        movie.setDirector(updatedMovie.getDirector());
        movie.setDescription(updatedMovie.getDescription());
        movie.setCast(updatedMovie.getCast());
        movie.setPlaybill(updatedMovie.getPlaybill());
    }

    // DELETE
    public void removeMovie(Long id) {
        Movie movie =
                movieRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new ResourceNotFoundException(
                                                "Movie with id: " + id + " was not found."));

        movieRepository.removeMovie(movie);
    }
}
