package dev.cere.content.rest;

import dev.cere.content.api.Movie.MovieDto;
import dev.cere.content.api.Movie.MovieSimpleDto;
import dev.cere.content.facade.MovieFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/content")
public class MovieRestController {

    private final MovieFacade movieFacade;

    @Autowired
    public MovieRestController(MovieFacade movieFacade) {
        this.movieFacade = movieFacade;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieSimpleDto movieSimpleDto) {
        MovieDto addedMovie = movieFacade.addMovie(movieSimpleDto);
        return new ResponseEntity<>(addedMovie, HttpStatus.CREATED);
    }

    // READ
    @GetMapping
    public List<MovieDto> findAll() {
        return movieFacade.findAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MovieDto> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(movieFacade.findById(id));
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<MovieDto>> find(
            @RequestParam(value = "genre", required = false) String genre,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "directorName", required = false) String directorName) {
        return ResponseEntity.ok(movieFacade.find(genre, title, year, directorName));
    }

    @GetMapping(path = "/title/{title}")
    public ResponseEntity<List<MovieDto>> findByTitlePrefix(
            @PathVariable("title") String titlePrefix) {
        return ResponseEntity.ok(movieFacade.findByTitlePrefix(titlePrefix));
    }

    // UPDATE
    @PutMapping(path = "/{id}")
    public ResponseEntity<MovieDto> updateMovie(
            @PathVariable("id") Long id, @RequestBody MovieDto updatedMovieDto) {
        MovieDto updatedResponseMovieDto = movieFacade.updateMovie(id, updatedMovieDto);
        return ResponseEntity.ok().body(updatedResponseMovieDto);
    }

    // DELETE
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> removeMovie(@PathVariable("id") Long id) {
        movieFacade.removeMovie(id);
        return ResponseEntity.noContent().build();
    }
}
