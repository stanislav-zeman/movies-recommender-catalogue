package dev.cere.content.data.repository;

import dev.cere.content.data.model.Genre;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepository {
    private List<Genre> genres = new CopyOnWriteArrayList<>();

    public void addGenre(Genre newGenre) {
        genres.add(newGenre);
    }

    public Genre find(String genreName) {
        return genres.stream()
                .filter(genre -> genre.getName().equals(genreName))
                .findAny()
                .orElse(null);
    }
}
