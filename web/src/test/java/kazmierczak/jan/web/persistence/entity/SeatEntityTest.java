package kazmierczak.jan.web.persistence.entity;

import kazmierczak.jan.persistence.entity.AddressEntity;
import kazmierczak.jan.persistence.entity.CinemaEntity;
import kazmierczak.jan.persistence.entity.CinemaRoomEntity;
import kazmierczak.jan.persistence.entity.SeatEntity;
import kazmierczak.jan.model.seat.Seat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class SeatEntityTest {

    @Test
    @DisplayName("when method works correctly")
    public void test1() {
        var addressEntity = AddressEntity
                .builder()
                .id(1L)
                .street("street")
                .city("city")
                .number(2)
                .cinemas(new ArrayList<>())
                .build();

        var cinemaEntity = CinemaEntity
                .builder()
                .id(1L)
                .name("name")
                .address(addressEntity)
                .build();

        var cinemaRoomEntity = CinemaRoomEntity
                .builder()
                .id(1L)
                .name("name")
                .rows(1)
                .places(1)
                .cinema(cinemaEntity)
                .build();

        var seat = Seat
                .builder()
                .id(1L)
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoomEntity.toCinemaRoom())
                .tickets(new ArrayList<>())
                .build();

        var seatEntity = SeatEntity
                .builder()
                .id(1L)
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoomEntity)
                .tickets(new ArrayList<>())
                .build();

        assertThat(seatEntity.toSeat())
                .isEqualTo(seat);
    }
}
