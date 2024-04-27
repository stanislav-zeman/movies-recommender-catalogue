package dev.cere.reviews.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.cere.reviews.data.entities.Review;
import dev.cere.reviews.data.repository.ReviewRepository;
import dev.cere.reviews.util.TestDataFactory;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest {

    @Autowired private ReviewRepository reviewRepository;

    @Autowired private TestEntityManager testEntityManager;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @BeforeEach
    void initData() {
        testEntityManager.merge(TestDataFactory.fiftyShadesOfGreyByBob);
        testEntityManager.merge(TestDataFactory.fiftyShadesOfGreyByRob);
    }

    @Test
    void findById_reviewFound_returnsReview() {
        Review expected = TestDataFactory.fiftyShadesOfGreyByBob;

        Optional<Review> actual = reviewRepository.findById(expected.getId());

        assertTrue(actual.isPresent());
        assertEquals(expected, actual.get());
    }

    @Test
    void findById_reviewNotFound_returnsNone() {
        Long madeUpId = 123456789L;

        Optional<Review> actual = reviewRepository.findById(madeUpId);

        assertTrue(actual.isEmpty());
    }

    @Test
    void findByContentId_reviewFound_returnsReviews() {
        Long fiftyShadesOfGreyId = TestDataFactory.fiftyShadesOfGreyByBob.getContentId();

        Page<Review> actual =
                reviewRepository.findAllByContentId(fiftyShadesOfGreyId, Pageable.unpaged());

        assertFalse(actual.isEmpty());
        assertEquals(2, actual.getTotalElements());
    }
}
