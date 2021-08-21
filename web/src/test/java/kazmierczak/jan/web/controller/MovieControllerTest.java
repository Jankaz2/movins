package kazmierczak.jan.web.controller;

import kazmierczak.jan.cinema.MovieService;
import kazmierczak.jan.controller.MovieController;
import kazmierczak.jan.model.movie.dto.GetMovieDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.List.of;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {MovieService.class, MovieController.class})
public class MovieControllerTest {
    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when method getAllMovies works correct")
    public void test1() throws Exception {
        var movieDto1 = GetMovieDto
                .builder()
                .id(1L)
                .title("Title")
                .genre("Genre")
                .duration(130)
                .releaseDate(LocalDate.of(2022, 12, 12))
                .seances(new ArrayList<>())
                .build();

        var movieDto2 = GetMovieDto
                .builder()
                .id(1L)
                .title("Titlee")
                .genre("Genree")
                .duration(130)
                .releaseDate(LocalDate.of(2022, 12, 12))
                .seances(new ArrayList<>())
                .build();

        var movies = of(movieDto1, movieDto2);

        when(movieService.findAll()).thenReturn(movies);

        mockMvc
                .perform(
                        get("/cinema/movies")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data[0].title", equalTo("Title")));
    }
}
