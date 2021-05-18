package kazmierczak.jan.web.persistence.entity;

import kazmierczak.jan.persistence.entity.AddressEntity;
import kazmierczak.jan.persistence.entity.CinemaEntity;
import kazmierczak.jan.persistence.entity.CinemaRoomEntity;
import model.cinema_room.CinemaRoom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CinemaRoomEntityTest {

    @Test
    @DisplayName("when mapping method works correctly")
    public void test1() {
        var addressEntity = AddressEntity.builder().build();
        var cinemaEntity = CinemaEntity.builder()
                .id(1L)
                .cinemaRooms(new ArrayList<>())
                .name("name")
                .address(addressEntity)
                .build();

        var cinemaRoom = CinemaRoom
                .builder()
                .id(1L)
                .seats(new ArrayList<>())
                .rows(1)
                .cinema(cinemaEntity.toCinema())
                .places(12)
                .build();

        var cinemaRoomEntity = CinemaRoomEntity
                .builder()
                .id(1L)
                .seats(new ArrayList<>())
                .rows(1)
                .cinema(cinemaEntity)
                .places(12)
                .build();

        assertThat(cinemaRoomEntity.toCinemaRoom())
                .isEqualTo(cinemaRoom);
    }
}
