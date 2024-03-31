package dev.cere.content.data.repository;

import dev.cere.content.data.model.Director;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class DirectorRepository {
    private List<Director> directors = new CopyOnWriteArrayList<>();

    public void addDirector(Director newDirector) {
        directors.add(newDirector);
    }

    public Director find(String directorName, LocalDate directorDbo) {
        return directors.stream()
                .filter(d -> d.getName().equals(directorName) && d.getDob().equals(directorDbo))
                .findAny()
                .orElse(null);
    }
}
