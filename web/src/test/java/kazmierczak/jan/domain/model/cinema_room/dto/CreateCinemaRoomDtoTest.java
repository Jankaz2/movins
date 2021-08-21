package kazmierczak.jan.domain.model.cinema_room.dto;

import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CreateCinemaRoomDtoTest {
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
