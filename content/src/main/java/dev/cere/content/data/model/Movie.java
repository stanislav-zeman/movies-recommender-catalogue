package dev.cere.content.data.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Movie implements Serializable {
    private Long id;
    private List<Genre> genres;
    private String title;
    private int year;
    private Director director;
    private String description;
    private List<String> cast;
    private byte[] playbill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
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

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getId(), movie.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Movie{"
                + "id="
                + id
                + ", genres="
                + genres
                + ", title='"
                + title
                + '\''
                + ", year="
                + year
                + ", director="
                + director
                + ", description='"
                + description
                + '\''
                + ", cast="
                + cast
                + ", playbill="
                + Arrays.toString(playbill)
                + '}';
    }
}
