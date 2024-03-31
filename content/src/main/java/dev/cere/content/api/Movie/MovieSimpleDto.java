package dev.cere.content.api.Movie;

import dev.cere.content.api.Director.DirectorSimpleDto;
import dev.cere.content.api.Genre.GenreSimpleDto;
import java.util.ArrayList;
import java.util.List;

public class MovieSimpleDto {
    private List<GenreSimpleDto> genres = new ArrayList<>();
    private String title;
    private int year;
    private String description;
    private DirectorSimpleDto director;
    private List<String> cast = new ArrayList<>();
    private byte[] playbill;

    public List<GenreSimpleDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreSimpleDto> genres) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DirectorSimpleDto getDirector() {
        return director;
    }

    public void setDirector(DirectorSimpleDto director) {
        this.director = director;
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
