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
    public static Movie dieHard =
            getMovieFactory(
                    1L,
                    List.of("Action", "Thriller"),
                    "Die Hard",
                    1988,
                    "A New York City police officer tries to save his estranged wife and several"
                            + " others taken hostage by terrorists during a Christmas party at the"
                            + " Nakatomi Plaza in Los Angeles.",
                    "John McTiernan",
                    LocalDate.of(1951, 1, 8),
                    List.of("Bruce Willis", "Alan Rickman", "Bonnie Bedelia"));
    public static MovieDto dieHardDto =
            getMovieDtoFactory(
                    1L,
                    List.of("Action", "Thriller"),
                    "Die Hard",
                    1988,
                    "A New York City police officer tries to save his estranged wife and several"
                            + " others taken hostage by terrorists during a Christmas party at the"
                            + " Nakatomi Plaza in Los Angeles.",
                    "John McTiernan",
                    LocalDate.of(1951, 1, 8),
                    List.of("Bruce Willis", "Alan Rickman", "Bonnie Bedelia"));

    public static Movie dieHard2 =
            getMovieFactory(
                    2L,
                    List.of("Action", "Thriller"),
                    "Die Hard 2",
                    1990,
                    "John McClane attempts to avert disaster as rogue military operatives seize"
                            + " control of Dulles International Airport in Washington, D.C.",
                    "Renny Harlin",
                    LocalDate.of(1959, 3, 15),
                    List.of("Bruce Willis", "William Atherton", "Bonnie Bedelia"));

    public static MovieDto dieHard2Dto =
            getMovieDtoFactory(
                    2L,
                    List.of("Action", "Thriller"),
                    "Die Hard 2",
                    1990,
                    "John McClane attempts to avert disaster as rogue military operatives seize"
                            + " control of Dulles International Airport in Washington, D.C.",
                    "Renny Harlin",
                    LocalDate.of(1959, 3, 15),
                    List.of("Bruce Willis", "William Atherton", "Bonnie Bedelia"));

    public static Movie rainMan =
            getMovieFactory(
                    3L,
                    List.of("Drama"),
                    "Rain Man",
                    1988,
                    "After a selfish L.A. yuppie learns his estranged father left a fortune to an"
                        + " autistic-savant brother in Ohio that he didn't know existed, he"
                        + " absconds with his brother and sets out across the country, hoping to"
                        + " gain a larger inheritance.",
                    "Barry Levinson",
                    LocalDate.of(1942, 4, 6),
                    List.of("Dustin Hoffman", "Tom Cruise", "Valeria Golino"));

    public static MovieDto rainManDto =
            getMovieDtoFactory(
                    3L,
                    List.of("Drama"),
                    "Rain Man",
                    1988,
                    "After a selfish L.A. yuppie learns his estranged father left a fortune to an"
                        + " autistic-savant brother in Ohio that he didn't know existed, he"
                        + " absconds with his brother and sets out across the country, hoping to"
                        + " gain a larger inheritance.",
                    "Barry Levinson",
                    LocalDate.of(1942, 4, 6),
                    List.of("Dustin Hoffman", "Tom Cruise", "Valeria Golino"));

    public static List<Movie> moviesOf1988 = List.of(dieHard, rainMan);
    public static List<MovieDto> moviesOf1988Dtos = List.of(dieHardDto, rainManDto);
    public static List<Movie> actionMovies = List.of(dieHard, dieHard2);
    public static List<MovieDto> actionMoviesDtos = List.of(dieHardDto, dieHard2Dto);
    public static List<Movie> dieHardMovies = List.of(dieHard, dieHard2);
    public static List<MovieDto> dieHardMoviesDtos = List.of(dieHardDto, dieHard2Dto);

    private static MovieDto getMovieDtoFactory(
            Long id,
            List<String> genres,
            String title,
            int year,
            String description,
            String directorName,
            LocalDate directorDob,
            List<String> cast) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(id);

        List<GenreDto> movieGenreDtos = new ArrayList<>();
        for (String genreName : genres) {
            GenreDto genreDto = new GenreDto();
            genreDto.setName(genreName);
            movieGenreDtos.add(genreDto);
        }
        movieDto.setGenres(movieGenreDtos);

        movieDto.setTitle(title);

        movieDto.setYear(year);

        movieDto.setDescription(description);

        DirectorDto directorDto = new DirectorDto();
        directorDto.setName(directorName);
        directorDto.setDob(directorDob);
        movieDto.setDirector(directorDto);

        movieDto.setCast(cast);

        return movieDto;
    }

    private static Movie getMovieFactory(
            Long id,
            List<String> genres,
            String title,
            int year,
            String description,
            String directorName,
            LocalDate directorDob,
            List<String> cast) {
        Movie movie = new Movie();
        movie.setId(id);

        List<Genre> movieGenres = new ArrayList<>();
        for (String genreName : genres) {
            Genre genre = new Genre();
            genre.setName(genreName);
            movieGenres.add(genre);
        }
        movie.setGenres(movieGenres);

        movie.setTitle(title);

        movie.setYear(year);

        movie.setDescription(description);

        Director director = new Director();
        director.setName(directorName);
        director.setDob(directorDob);
        movie.setDirector(director);

        movie.setCast(cast);

        return movie;
    }
}
