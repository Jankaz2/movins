package kazmierczak.jan.domain.model.cinema_room.dto;

import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateCinemaRoomDtoTest {

    @Test
    @DisplayName("when method toListOfCinemaRooms works correct")
    public void test1() {
        var cinemaRoomsDtoList = List.of(
                CreateCinemaRoomDto.builder().name("Name1").rows(10).places(10).build(),
                CreateCinemaRoomDto.builder().name("Name2").rows(11).places(11).build()
        );

        var cinemaRoomsList = List.of(
                CinemaRoom.builder().name("Name1").rows(10).places(10).build(),
                CinemaRoom.builder().name("Name2").rows(11).places(11).build()
        );

        assertThat(cinemaRoomsDtoList
                .stream()
                .map(CreateCinemaRoomDto::toCinemaRoom)
                .toList())
                .isEqualTo(cinemaRoomsList);
    }

    @Test
    @DisplayName("when toCinemaRoom method works correct")
    public void test2() {
        var createCinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name("Name")
                .rows(10)
                .places(10)
                .build();

        var cinemaRoom = CinemaRoom
                .builder()
                .name("Name")
                .rows(10)
                .places(10)
                .build();

        assertThat(createCinemaRoomDto.toCinemaRoom())
                .isEqualTo(cinemaRoom);
    }
}
