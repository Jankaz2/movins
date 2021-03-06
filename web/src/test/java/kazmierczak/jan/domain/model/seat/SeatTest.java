package kazmierczak.jan.domain.model.seat;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;
import kazmierczak.jan.model.seat.dto.GetSeatDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class SeatTest {

    @Test
    @DisplayName("when method toCreateSeatDto works correct")
    public void test1() {
        var seat = Seat
                .builder()
                .row(1)
                .place(1)
                .build();

        var createSeatDto = CreateSeatDto
                .builder()
                .row(1)
                .place(1)
                .build();

        assertThat(seat.toCreateSeatDto())
                .isEqualTo(createSeatDto);
    }

    @Test
    @DisplayName("when method toGetSeatDto works correct")
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
                .name("Name")
                .rows(10)
                .places(11)
                .cinema(cinema)
                .build();

        var seat = Seat
                .builder()
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoom)
                .build();

        var getSeatDto = GetSeatDto
                .builder()
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoom.toGetCinemaRoomDto())
                .build();

        assertThat(seat.toGetSeatDto())
                .isEqualTo(getSeatDto);
    }
}