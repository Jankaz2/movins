package kazmierczak.jan.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kazmierczak.jan.cinema.MovieService;
import kazmierczak.jan.controller.MovieController;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.movie.dto.CreateMovieResponseDto;
import kazmierczak.jan.model.movie.dto.GetMovieDto;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import kazmierczak.jan.model.seance.dto.GetSeanceDto;
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

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.List.of;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Autowired
    private ObjectMapper mapper;

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

    @Test
    @DisplayName("when method createMovie works correctly")
    public void test2() throws Exception {
        var createSeanceDto = CreateSeanceDto
                .builder()
                .date(LocalDate.of(2021, 12, 12))
                .build();

        var movieDto = CreateMovieDto
                .builder()
                .title("Titlee")
                .genre("Genree")
                .duration(130)
                .releaseDate(LocalDate.of(2022, 12, 12))
                .seances(of(createSeanceDto))
                .build();

        var cinemaRoomId = 2L;
        var movieResponseDto = CreateMovieResponseDto
                .builder()
                .id(1L)
                .build();

        when(movieService.createMovie(cinemaRoomId, movieDto)).thenReturn(movieResponseDto);

        mockMvc
                .perform(
                        post("/cinema/movies/admin/2")
                                .content(mapper
                                        .writeValueAsString(movieDto)
                                        .getBytes(UTF_8))
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.id", equalTo(1)));
    }

    @Test
    @DisplayName("when method findAllSeancesByCinemaRoomId works correct")
    public void test3() throws Exception {
        var address = Address
                .builder()
                .id(1L)
                .city("City")
                .street("Street")
                .number(10)
                .cinemas(new ArrayList<>())
                .build();

        var cinema = Cinema
                .builder()
                .id(1L)
                .name("Name")
                .address(address)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaRoom = CinemaRoom
                .builder()
                .id(1L)
                .name("Cinema")
                .places(10)
                .rows(10)
                .cinema(cinema)
                .seances(new ArrayList<>())
                .seances(new ArrayList<>())
                .build();

        var movie = Movie
                .builder()
                .id(1L)
                .title("Title")
                .genre("Genre")
                .duration(150)
                .releaseDate(LocalDate.of(2020, 2, 12))
                .seances(new ArrayList<>())
                .build();

        var seanceDto = GetSeanceDto
                .builder()
                .id(1L)
                .cinemaRoom(cinemaRoom.toGetCinemaRoomDto())
                .movie(movie.toGetMovieDtoLight())
                .date(LocalDate.of(2020, 12, 12))
                .build();

        var cinemaRoomId = 1L;
        var seances = of(seanceDto);

        when(movieService.findAllSeancesByCinemaRoomId(cinemaRoomId)).thenReturn(seances);

        mockMvc
                .perform(
                        get("/cinema/movies/seances/{id}", cinemaRoomId)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data[0].date", equalTo("2020-12-12")));
    }
}
