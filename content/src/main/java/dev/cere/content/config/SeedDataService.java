package dev.cere.content.config;

import dev.cere.content.data.model.Director;
import dev.cere.content.data.model.Genre;
import dev.cere.content.data.model.Movie;
import dev.cere.content.data.repository.*;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
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
    public void insertData() {
        insertMovies();
    }

    private static Genre getGenreFactory(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genre;
    }

    private static Director getDirectorFactory(String name, LocalDate dob) {
        Director director = new Director();
        director.setName(name);
        director.setDob(dob);
        return director;
    }

    private static Movie getMovieFactory(
            List<Genre> genres,
            String title,
            int year,
            String description,
            Director director,
            List<String> cast) {
        Movie movie = new Movie();

        movie.setGenres(genres);

        movie.setTitle(title);

        movie.setYear(year);

        movie.setDescription(description);

        movie.setDirector(director);

        movie.setCast(cast);

        return movie;
    }

    private void insertMovies() {
        Genre actionGenre = getGenreFactory("Action");
        genreRepository.save(actionGenre);

        Genre thrillerGenre = getGenreFactory("Thriller");
        genreRepository.save(thrillerGenre);

        Genre dramaGenre = getGenreFactory("Drama");
        genreRepository.save(dramaGenre);

        Director dieHardDirector = getDirectorFactory("John McTiernan", LocalDate.of(1951, 1, 8));
        directorRepository.save(dieHardDirector);

        Director dieHard2Director = getDirectorFactory("Renny Harlin", LocalDate.of(1959, 3, 15));
        directorRepository.save(dieHard2Director);

        Director rainmanDirector = getDirectorFactory("Barry Levinson", LocalDate.of(1942, 4, 6));
        directorRepository.save(rainmanDirector);

        Movie dieHard =
                getMovieFactory(
                        List.of(actionGenre, thrillerGenre),
                        "Die Hard",
                        1988,
                        "A New York City police officer tries to save his estranged wife and"
                            + " several others taken hostage by terrorists during a Christmas party"
                            + " at the Nakatomi Plaza in Los Angeles.",
                        dieHardDirector,
                        List.of("Bruce Willis", "Alan Rickman", "Bonnie Bedelia"));
        movieRepository.save(dieHard);

        Movie dieHard2 =
                getMovieFactory(
                        List.of(actionGenre, thrillerGenre),
                        "Die Hard 2",
                        1990,
                        "John McClane attempts to avert disaster as rogue military operatives seize"
                                + " control of Dulles International Airport in Washington, D.C.",
                        dieHard2Director,
                        List.of("Bruce Willis", "William Atherton", "Bonnie Bedelia"));
        movieRepository.save(dieHard2);

        Movie rainMan =
                getMovieFactory(
                        List.of(dramaGenre),
                        "Rain Man",
                        1988,
                        "After a selfish L.A. yuppie learns his estranged father left a fortune to"
                            + " an autistic-savant brother in Ohio that he didn't know existed, he"
                            + " absconds with his brother and sets out across the country, hoping"
                            + " to gain a larger inheritance.",
                        rainmanDirector,
                        List.of("Dustin Hoffman", "Tom Cruise", "Valeria Golino"));
        movieRepository.save(rainMan);
    }
}
