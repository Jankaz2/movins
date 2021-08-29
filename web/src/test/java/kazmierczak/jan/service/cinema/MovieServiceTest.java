package kazmierczak.jan.service.cinema;

import kazmierczak.jan.cinema.MovieService;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.movie.repository.MovieRepository;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import kazmierczak.jan.model.seance.repository.SeanceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MovieServiceTest {

    @TestConfiguration
    public static class MovieServiceTestConfiguration {
        @MockBean
        public MovieRepository movieRepository;

        @MockBean
        public SeanceRepository seanceRepository;

        @MockBean
        public CinemaRoomRepository cinemaRoomRepository;

        @Bean
        public MovieService movieService() {
            return new MovieService(movieRepository, seanceRepository, cinemaRoomRepository);
        }
    }

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Test
    @DisplayName("when method findAll works correctly")
    public void test1() {
        var movie = Movie
                .builder()
                .id(1L)
                .title("Title")
                .genre("Genre")
                .duration(110)
                .releaseDate(LocalDate.of(2022, 12, 12))
                .seances(new ArrayList<>())
                .build();

        when(movieRepository.findAll())
                .thenReturn(of(movie));

        assertThat(movieService.findAll())
                .hasSize(1)
                .containsExactly(movie.toGetMovieDto());
    }

    @Test
    @DisplayName("when method findAllSeancesByCinemaRoomId works correctly")
    public void test2() {
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
                .name("Name")
                .rows(10)
                .places(11)
                .cinema(cinema)
                .build();

        var movie = Movie
                .builder()
                .id(1L)
                .title("Title")
                .genre("Genre")
                .duration(110)
                .releaseDate(LocalDate.of(2022, 12, 12))
                .seances(new ArrayList<>())
                .build();

        var seance = Seance
                .builder()
                .id(1L)
                .movie(movie)
                .cinemaRoom(cinemaRoom)
                .date(LocalDate.of(2022, 12, 12))
                .tickets(new ArrayList<>())
                .build();

        when(seanceRepository.findAllByCinemaRoomId(1L))
                .thenReturn(of(seance));

        assertThat(movieService.findAllSeancesByCinemaRoomId(1L))
                .hasSize(1)
                .containsExactly(seance.toGetSeanceDto());
    }

    @Test
    @DisplayName("when method createMovie works correctly")
    public void test3() {
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
                .name("Name")
                .rows(10)
                .places(11)
                .cinema(cinema)
                .build();

        var movie = Movie
                .builder()
                .id(1L)
                .title("Title")
                .genre("Genre")
                .duration(110)
                .releaseDate(LocalDate.of(2022, 12, 12))
                .seances(new ArrayList<>())
                .build();


        var seance = Seance
                .builder()
                .id(1L)
                .movie(movie)
                .cinemaRoom(cinemaRoom)
                .date(LocalDate.of(2020, 12, 12))
                .tickets(new ArrayList<>())
                .build();

        var seanceDto = CreateSeanceDto
                .builder()
                .date(LocalDate.of(2022, 12, 12))
                .build();

        var movieDto = CreateMovieDto
                .builder()
                .title("Title")
                .genre("Genre")
                .duration(110)
                .releaseDate(LocalDate.of(2022, 12, 12))
                .seances(of(seanceDto))
                .build();

        when(cinemaRoomRepository.findById(1L))
                .thenReturn(Optional.of(cinemaRoom));

        when(movieRepository.add(any(Movie.class)))
                .thenReturn(Optional.of(movie));

        when(seanceRepository.saveAll(anyList()))
                .thenReturn(of(seance));

        var createdMovie = movieService
                .createMovie(1L, movieDto);

        assertThat(createdMovie.getId())
                .isEqualTo(1L);
    }
}
