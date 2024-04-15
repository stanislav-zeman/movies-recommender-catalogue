package dev.cere.content.service;

import dev.cere.content.data.model.Director;
import dev.cere.content.data.model.Genre;
import dev.cere.content.data.model.Movie;
import dev.cere.content.data.repository.*;
import dev.cere.content.exceptions.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public Movie addMovie(Movie newMovie) {
        // Check if director of the new movie exists in db
        // If not, persist the director
        Director newMovieDirector = newMovie.getDirector();
        Optional<Director> director =
                directorRepository.findByNameAndDob(
                        newMovieDirector.getName(), newMovieDirector.getDob());

        if (director.isPresent()) {
            newMovie.setDirector(director.get());
        } else {
            directorRepository.save(newMovieDirector);
        }

        // Check if all genres of the new movie exist in db
        // If not, persist them
        List<Genre> newMovieGenres = newMovie.getGenres();
        List<Genre> savedGenres = new ArrayList<>(); // To store saved genres

        for (Genre genre : newMovieGenres) {
            Optional<Genre> g = genreRepository.findByName(genre.getName());
            if (g.isPresent()) {
                savedGenres.add(g.get()); // Add existing genre to the list
            } else {
                genreRepository.save(genre);
                savedGenres.add(genre); // Add newly saved genre to the list
            }
        }

        newMovie.setGenres(savedGenres);
        movieRepository.save(newMovie);
        return newMovie;
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
    public Movie updateMovie(Long id, Movie updatedMovie) {
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
        movieRepository.save(movie);
        return movie;
    }

    // DELETE
    public void removeMovie(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            movieRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Movie with id: " + id + " was not found.");
        }
    }
}
