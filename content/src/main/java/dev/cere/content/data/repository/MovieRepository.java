package dev.cere.content.data.repository;

import dev.cere.content.data.model.Movie;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class MovieRepository {
    private List<Movie> movies = new CopyOnWriteArrayList<>();

    // CREATE
    public void addMovie(Movie newMovie) {
        movies.add(newMovie);
    }

    // READ
    public List<Movie> findAll() {
        return movies;
    }

    public Optional<Movie> findById(Long id) {
        return movies.stream().filter(movie -> movie.getId().equals(id)).findFirst();
    }

    public List<Movie> find(String genre, String title, Integer year, String directorName) {
        return movies.stream()
                .filter(
                        (movie) ->
                                (genre != null
                                                && movie.getGenres().stream()
                                                        .anyMatch(g -> g.getName().equals(genre)))
                                        || (title != null && movie.getTitle().equals(title))
                                        || (year != null && movie.getYear() == year)
                                        || (directorName != null
                                                && movie.getDirector()
                                                        .getName()
                                                        .equals(directorName)))
                .collect(Collectors.toList());
    }

    public List<Movie> findByTitlePrefix(String titlePrefix) {
        return movies.stream()
                .filter(movie -> movie.getTitle().contains(titlePrefix))
                .collect(Collectors.toList());
    }

    // DELETE
    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }
}
