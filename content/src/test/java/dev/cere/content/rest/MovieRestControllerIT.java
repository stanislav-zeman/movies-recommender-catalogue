package dev.cere.content.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cere.content.api.Movie.MovieDto;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieRestControllerIT {

    @Autowired private MockMvc mockMvc;

    @Autowired ObjectMapper objectMapper;

    @Test
    void findByYear_moviesFound_returnsMovies() throws Exception {
        // Arrange
        int year = 1988;

        // Act
        String responseJson =
                mockMvc.perform(
                                get("/content/params?year={year}", year)
                                        .accept(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(status().isOk())
                        .andReturn()
                        .getResponse()
                        .getContentAsString(StandardCharsets.UTF_8);

        // Assert
        List<MovieDto> response =
                objectMapper.readValue(responseJson, new TypeReference<List<MovieDto>>() {});
        assertEquals(2, response.size());
        for (MovieDto movieDto : response) {
            assertEquals(year, movieDto.getYear());
        }
    }
}
