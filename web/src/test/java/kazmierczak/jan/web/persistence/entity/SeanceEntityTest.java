package kazmierczak.jan.web.persistence.entity;

import kazmierczak.jan.persistence.entity.*;
import kazmierczak.jan.model.seance.Seance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class SeanceEntityTest {

    @Test
    @DisplayName("when mapping method works correctly")
    public void test1() {
        var address = AddressEntity
                .builder()
                .id(1L)
                .number(1)
                .street("street")
                .city("city")
                .cinemas(new ArrayList<>())
                .build();

        var cinema = CinemaEntity.builder()
                .id(1L)
                .name("name")
                .cinemaRooms(new ArrayList<>())
                .address(address)
                .build();

        var movieEntity = MovieEntity.builder()
                .id(1L)
                .seances(new ArrayList<>())
                .title("title")
                .genre("genre")
                .duration(10)
                .releaseDate(LocalDate.of(2020, 12, 12))
                .build();

        var cinemaRoomEntity = CinemaRoomEntity.builder()
                .id(1L)
                .places(12)
                .seats(new ArrayList<>())
                .cinema(cinema)
                .name("name")
                .rows(12)
                .build();

        var seance = Seance
                .builder()
                .id(1L)
                .movie(movieEntity.toMovie())
                .tickets(new ArrayList<>())
                .cinemaRoom(cinemaRoomEntity.toCinemaRoom())
                .date(LocalDate.of(2020, 12, 12))
                .build();

        var seanceEntity = SeanceEntity
                .builder()
                .id(1L)
                .movie(movieEntity)
                .tickets(new ArrayList<>())
                .cinemaRoom(cinemaRoomEntity)
                .date(LocalDate.of(2020, 12, 12))
                .build();

        assertThat(seanceEntity.toSeance())
                .isEqualTo(seance);
    }
}
