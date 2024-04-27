package dev.cere.reviews.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cere.reviews.api.ReviewDto;
import dev.cere.reviews.data.entities.Review;
import dev.cere.reviews.util.TestDataFactory;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewRestControllerIT {

    @Autowired private MockMvc mockMvc;

    @Autowired ObjectMapper objectMapper;

    @Test
    void findById_reviewFound_returnsReview() throws Exception {
        Review actual = TestDataFactory.sashaGreyAdventuresBySandra;

        String responseJson =
                mockMvc.perform(
                                get("/reviews/1", actual.getId())
                                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(StandardCharsets.UTF_8);

        ReviewDto response = objectMapper.readValue(responseJson, new TypeReference<>() {});
        assertEquals(1L, response.getId());
        assertEquals(1L, response.getUserId());
        assertEquals(1L, response.getContentId());
        assertEquals(5, response.getStars());
        assertEquals("This is a great review.", response.getReview());
    }
}
