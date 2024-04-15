package dev.cere.content.util;

import dev.cere.content.api.Director.DirectorDto;
import dev.cere.content.api.Genre.GenreDto;
import dev.cere.content.api.Movie.MovieDto;
import dev.cere.content.data.model.Director;
import dev.cere.content.data.model.Genre;
import dev.cere.content.data.model.Movie;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TestDataFactory {

    public static Genre actionGenre = getGenreFactory("Action");
    public static Genre thrillerGenre = getGenreFactory("Thriller");
    public static Genre dramaGenre = getGenreFactory("Drama");

    public static Director dieHardDirector =
            getDirectorFactory("John McTiernan", LocalDate.of(1951, 1, 8));
    public static Director dieHard2Director =
            getDirectorFactory("Renny Harlin", LocalDate.of(1959, 3, 15));
    public static Director rainmanDirector =
            getDirectorFactory("Barry Levinson", LocalDate.of(1942, 4, 6));

    public static Movie dieHard =
            getMovieFactory(
                    List.of(actionGenre, thrillerGenre),
                    "Die Hard",
                    1988,
                    "A New York City police officer tries to save his estranged wife and several"
                            + " others taken hostage by terrorists during a Christmas party at the"
                            + " Nakatomi Plaza in Los Angeles.",
                    dieHardDirector,
                    List.of("Bruce Willis", "Alan Rickman", "Bonnie Bedelia"));
    public static MovieDto dieHardDto =
            getMovieDtoFactory(
                    1L,
                    List.of(actionGenre, thrillerGenre),
                    "Die Hard",
                    1988,
                    "A New York City police officer tries to save his estranged wife and several"
                            + " others taken hostage by terrorists during a Christmas party at the"
                            + " Nakatomi Plaza in Los Angeles.",
                    dieHardDirector,
                    List.of("Bruce Willis", "Alan Rickman", "Bonnie Bedelia"));

    public static Movie dieHard2 =
            getMovieFactory(
                    List.of(actionGenre, thrillerGenre),
                    "Die Hard 2",
                    1990,
                    "John McClane attempts to avert disaster as rogue military operatives seize"
                            + " control of Dulles International Airport in Washington, D.C.",
                    dieHard2Director,
                    List.of("Bruce Willis", "William Atherton", "Bonnie Bedelia"));

    public static MovieDto dieHard2Dto =
            getMovieDtoFactory(
                    2L,
                    List.of(actionGenre, thrillerGenre),
                    "Die Hard 2",
                    1990,
                    "John McClane attempts to avert disaster as rogue military operatives seize"
                            + " control of Dulles International Airport in Washington, D.C.",
                    dieHard2Director,
                    List.of("Bruce Willis", "William Atherton", "Bonnie Bedelia"));

    public static Movie rainMan =
            getMovieFactory(
                    List.of(dramaGenre),
                    "Rain Man",
                    1988,
                    "After a selfish L.A. yuppie learns his estranged father left a fortune to an"
                        + " autistic-savant brother in Ohio that he didn't know existed, he"
                        + " absconds with his brother and sets out across the country, hoping to"
                        + " gain a larger inheritance.",
                    rainmanDirector,
                    List.of("Dustin Hoffman", "Tom Cruise", "Valeria Golino"));

    public static MovieDto rainManDto =
            getMovieDtoFactory(
                    3L,
                    List.of(dramaGenre),
                    "Rain Man",
                    1988,
                    "After a selfish L.A. yuppie learns his estranged father left a fortune to an"
                        + " autistic-savant brother in Ohio that he didn't know existed, he"
                        + " absconds with his brother and sets out across the country, hoping to"
                        + " gain a larger inheritance.",
                    rainmanDirector,
                    List.of("Dustin Hoffman", "Tom Cruise", "Valeria Golino"));

    public static List<Movie> moviesOf1988 = List.of(dieHard, rainMan);
    public static List<MovieDto> moviesOf1988Dtos = List.of(dieHardDto, rainManDto);
    public static List<Movie> actionMovies = List.of(dieHard, dieHard2);
    public static List<MovieDto> actionMoviesDtos = List.of(dieHardDto, dieHard2Dto);
    public static List<Movie> dieHardMovies = List.of(dieHard, dieHard2);
    public static List<MovieDto> dieHardMoviesDtos = List.of(dieHardDto, dieHard2Dto);

    public static Genre getGenreFactory(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        return genre;
    }

    public static Director getDirectorFactory(String name, LocalDate dob) {
        Director director = new Director();
        director.setName(name);
        director.setDob(dob);
        return director;
    }

    public static MovieDto getMovieDtoFactory(
            Long id,
            List<Genre> genres,
            String title,
            int year,
            String description,
            Director director,
            List<String> cast) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(id);

        List<GenreDto> movieGenreDtos = new ArrayList<>();
        for (Genre genre : genres) {
            GenreDto genreDto = new GenreDto();
            genreDto.setName(genre.getName());
            movieGenreDtos.add(genreDto);
        }
        movieDto.setGenres(movieGenreDtos);

        movieDto.setTitle(title);

        movieDto.setYear(year);

        movieDto.setDescription(description);

        DirectorDto directorDto = new DirectorDto();
        directorDto.setName(director.getName());
        directorDto.setDob(director.getDob());
        movieDto.setDirector(directorDto);

        movieDto.setCast(cast);

        return movieDto;
    }

    public static Movie getMovieFactory(
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
}
