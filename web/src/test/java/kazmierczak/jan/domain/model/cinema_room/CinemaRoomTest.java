package kazmierczak.jan.domain.model.cinema_room;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CinemaRoomTest {
    @Test
    @DisplayName("when toCreateCinemaRoomDto method works correct")
    public void test1() {
        var cinemaRoom = CinemaRoom
                .builder()
                .name("Name")
                .rows(10)
                .places(10)
                .build();

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name("Name")
                .rows(10)
                .places(10)
                .build();


        assertThat(cinemaRoom.toCreateCinemaRoomDto())
                .isEqualTo(createCinemaRoomDto);
    }

    @Test
    @DisplayName("when toGetCinemaRoomDto method works correct")
    public void test2() {
        var cinemaRoom = CinemaRoom
                .builder()
                .id(1L)
                .name("Name")
                .rows(10)
                .places(10)
                .build();

        var getCinemaRoomDto = GetCinemaRoomDto
                .builder()
                .id(1L)
                .name("Name")
                .rows(10)
                .places(10)
                .build();


        assertThat(cinemaRoom.toGetCinemaRoomDto())
                .isEqualTo(getCinemaRoomDto);
    }

    @Test
    @DisplayName("when withChangedData method works correct")
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
                .name("Name")
                .rows(10)
                .places(11)
                .cinema(cinema)
                .build();

        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name("Namedto")
                .rows(11)
                .places(11)
                .build();

        var cinemaRoomAfterChange = CinemaRoom
                .builder()
                .name("Namedto")
                .rows(11)
                .places(11)
                .cinema(cinema)
                .build();

        var newCinemaRoom = cinemaRoom.withChangedData(createCinemaRoomDto);

        assertThat(newCinemaRoom)
                .isEqualTo(cinemaRoomAfterChange);
    }
}
