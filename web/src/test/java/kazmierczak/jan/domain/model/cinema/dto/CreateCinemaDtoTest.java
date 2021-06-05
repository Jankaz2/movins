package kazmierczak.jan.domain.model.cinema.dto;

import model.address.Address;
import model.cinema.Cinema;
import model.cinema.dto.CreateCinemaDto;
import model.cinema_room.CinemaRoom;
import model.cinema_room.dto.CreateCinemaRoomDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateCinemaDtoTest {

    @Test
    @DisplayName("when method toCinema works correct")
    public void test1() {
        var cinemaRoomsList = List.of(
                CinemaRoom.builder().name("Name1").rows(10).places(10).build(),
                CinemaRoom.builder().name("Name2").rows(11).places(11).build()
        );

        var cinemaRoomsDtoList = List.of(
                CreateCinemaRoomDto.builder().name("Name1").rows(10).places(10).build(),
                CreateCinemaRoomDto.builder().name("Name2").rows(11).places(11).build()
        );

        var address = Address
                .builder()
                .city("City")
                .street("Street")
                .number(10)
                .build();

        var cinema = Cinema
                .builder()
                .name("Name")
                .address(address)
                .cinemaRooms(cinemaRoomsList)
                .build();

        var createCinemaDto = CreateCinemaDto
                .builder()
                .name("Name")
                .address(address.toCreateAddressDto())
                .cinemaRooms(cinemaRoomsDtoList)
                .build();

        assertThat(createCinemaDto.toCinema())
                .isEqualTo(cinema);
    }
}
