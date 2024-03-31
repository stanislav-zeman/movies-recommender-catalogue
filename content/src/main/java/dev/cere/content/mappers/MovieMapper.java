package dev.cere.content.mappers;

import dev.cere.content.api.Movie.MovieDto;
import dev.cere.content.api.Movie.MovieSimpleDto;
import dev.cere.content.data.model.Movie;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto mapToDto(Movie movie);

    Movie mapToMovie(MovieSimpleDto movieSimpleDto);

    Movie mapToMovie(MovieDto movieDto);

    List<MovieDto> mapToDtoList(List<Movie> movies);
}
