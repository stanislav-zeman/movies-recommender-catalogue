package dev.cere.content.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.cere.content.data.model.Director;
import dev.cere.content.data.repository.DirectorRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DirectorRepositoryTest {

    @Autowired private DirectorRepository directorRepository;

    @Autowired private TestEntityManager testEntityManager;

    @BeforeEach
    void initData() {
        Director director = new Director();
        director.setName("John Doe");
        director.setDob(LocalDate.of(1990, 1, 1));
        testEntityManager.persist(director);
    }

    @Test
    void findByNameAndDob_directorFound_returnsDirector() {
        // Arrange
        String name = "John Doe";
        LocalDate dob = LocalDate.of(1990, 1, 1);

        // Act
        Optional<Director> foundEntity = directorRepository.findByNameAndDob(name, dob);

        // Assert
        assertTrue(foundEntity.isPresent());
        assertEquals(name, foundEntity.get().getName());
        assertEquals(dob, foundEntity.get().getDob());
    }

    @Test
    void findByNameAndDob_directorNotFound_returnsNothing() {
        // Arrange
        String name = "Not Real";
        LocalDate dob = LocalDate.of(1990, 1, 1);

        // Act
        Optional<Director> foundEntity = directorRepository.findByNameAndDob(name, dob);

        // Assert
        assertTrue(foundEntity.isEmpty());
    }
}
