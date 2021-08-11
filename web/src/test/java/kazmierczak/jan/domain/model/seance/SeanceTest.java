package kazmierczak.jan.domain.model.seance;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seance.dto.GetSeanceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class SeanceTest {

    @Test
    @DisplayName("when method toGetSeanceDto works correct")
    public void test1() {
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

        var seance = Seance
                .builder()
                .id(1L)
                .movie(movie)
                .cinemaRoom(cinemaRoom)
                .date(LocalDate.of(2020, 12, 12))
                .tickets(new ArrayList<>())
                .build();

        var seanceDto = GetSeanceDto
                .builder()
                .id(1L)
                .cinemaRoom(cinemaRoom.toGetCinemaRoomDto())
                .movie(movie.toGetMovieDtoLight())
                .date(LocalDate.of(2020, 12, 12))
                .build();

        assertThat(seance.toGetSeanceDto())
                .isEqualTo(seanceDto);
    }
}
