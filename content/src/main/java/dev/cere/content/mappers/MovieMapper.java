package dev.cere.content.mappers;

import dev.cere.content.api.Movie.MovieDto;
import dev.cere.content.api.Movie.MovieSimpleDto;
import dev.cere.content.data.model.Movie;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto mapToDto(Movie movie);

    @Mapping(target = "id", ignore = true)
    Movie mapToMovie(MovieSimpleDto movieSimpleDto);

    Movie mapToMovie(MovieDto movieDto);

    List<MovieDto> mapToDtoList(List<Movie> movies);
}
