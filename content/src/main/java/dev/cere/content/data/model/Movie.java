package dev.cere.content.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @Column(name = "description")
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "movie_cast")
    private List<String> cast;

    @Column(name = "playbill")
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
        if (!(o instanceof Movie movie)) return false;
        return getYear() == movie.getYear()
                && Objects.equals(getTitle(), movie.getTitle())
                && Objects.equals(getDirector(), movie.getDirector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getYear(), getDirector());
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
