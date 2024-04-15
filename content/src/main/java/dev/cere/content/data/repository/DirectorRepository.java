package dev.cere.content.data.repository;

import dev.cere.content.data.model.Director;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    Optional<Director> findByNameAndDob(String name, LocalDate dob);
}
