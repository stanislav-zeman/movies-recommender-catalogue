package dev.cere.content.config;

import dev.cere.content.data.model.Director;
import dev.cere.content.data.model.Genre;
import dev.cere.content.data.model.Movie;
import dev.cere.content.data.repository.*;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeedDataService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;

    @Autowired
    public SeedDataService(
            MovieRepository movieRepository,
            GenreRepository genreRepository,
            DirectorRepository directorRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.directorRepository = directorRepository;
    }

    @PostConstruct
    public void insertDummyData() {
        insertDummyMovies();
    }

    private void insertDummyMovies() {
        Genre genre1 = new Genre();
        genre1.setName("Action");
        genreRepository.save(genre1);

        Genre genre2 = new Genre();
        genre2.setName("Horror");
        genreRepository.save(genre2);

        Genre genre3 = new Genre();
        genre3.setName("Romantic");
        genreRepository.save(genre3);

        Genre genre4 = new Genre();
        genre4.setName("Comedy");
        genreRepository.save(genre4);

        Director director1 = new Director();
        director1.setName("Name1");
        director1.setDob(LocalDate.of(1960, 1, 1));
        directorRepository.save(director1);

        Director director2 = new Director();
        director2.setName("Name2");
        director2.setDob(LocalDate.of(1960, 2, 2));
        directorRepository.save(director2);

        Movie movie1 = new Movie();
        movie1.setGenres(List.of(genre1));
        movie1.setTitle("Movie1");
        movie1.setYear(2001);
        movie1.setDirector(director1);
        movie1.setDescription("Desc1");
        movie1.setCast(Arrays.asList("Actor 1", "Actor 2"));
        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setGenres(List.of(genre2));
        movie2.setTitle("Movie2");
        movie2.setYear(2002);
        movie2.setDirector(director2);
        movie2.setDescription("Desc2");
        movie2.setCast(Arrays.asList("Actor 3", "Actor 4"));
        movieRepository.save(movie2);

        Movie movie3 = new Movie();
        movie3.setGenres(Arrays.asList(genre3, genre4));
        movie3.setTitle("Movie3");
        movie3.setYear(2003);
        movie3.setDirector(director2);
        movie3.setDescription("Desc3");
        movie3.setCast(Arrays.asList("Actor 5", "Actor 6"));
        movieRepository.save(movie3);
    }
}
