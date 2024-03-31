package dev.cere.content.api.Movie;

import dev.cere.content.api.Director.DirectorDto;
import dev.cere.content.api.Genre.GenreDto;
import java.util.ArrayList;
import java.util.List;

public class MovieDto {
    private Long id;
    private List<GenreDto> genres = new ArrayList<>();
    private String title;
    private int year;
    private String description;
    private DirectorDto director;
    private List<String> cast = new ArrayList<>();
    private byte[] playbill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DirectorDto getDirector() {
        return director;
    }

    public void setDirector(DirectorDto director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public byte[] getPlaybill() {
        return playbill;
    }

    public void setPlaybill(byte[] playbill) {
        this.playbill = playbill;
    }
}
