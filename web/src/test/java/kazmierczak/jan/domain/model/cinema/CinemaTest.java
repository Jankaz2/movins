package kazmierczak.jan.domain.model.cinema;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema.dto.CreateCinemaResponseDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CinemaTest {

    @Test
    @DisplayName("when method toGetCinemaDto works correct")
    public void test1() {
        var cinemaRoomsDtoList = List.of(
                CreateCinemaRoomDto.builder().name("Name1").rows(10).places(10).build(),
                CreateCinemaRoomDto.builder().name("Name2").rows(11).places(11).build()
        );

        var cinemaRoomsList = List.of(
                CinemaRoom.builder().name("Name1").rows(10).places(10).build(),
                CinemaRoom.builder().name("Name2").rows(11).places(11).build()
        );

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
                .cinemaRooms(cinemaRoomsList)
                .build();

        var getCinemaDto = GetCinemaDto
                .builder()
                .id(1L)
                .name("Name")
                .address(address.toCreateAddressDto())
                .cinemaRooms(cinemaRoomsDtoList)
                .build();

        assertThat(cinema.toGetCinemaDto())
                .isEqualTo(getCinemaDto);
    }

    @Test
    @DisplayName("when method toCreateCinemaResponseDto works correct")
    public void test2() {
        var cinemaRoomsDtoList = List.of(
                CreateCinemaRoomDto.builder().name("Name1").rows(10).places(10).build(),
                CreateCinemaRoomDto.builder().name("Name2").rows(11).places(11).build()
        );

        var cinemaRoomsList = List.of(
                CinemaRoom.builder().name("Name1").rows(10).places(10).build(),
                CinemaRoom.builder().name("Name2").rows(11).places(11).build()
        );

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
                .cinemaRooms(cinemaRoomsList)
                .build();

        var createCinemaResponseDto = CreateCinemaResponseDto
                .builder()
                .cinemaId(1L)
                .build();

        assertThat(cinema.toCreateCinemaResponseDto())
                .isEqualTo(createCinemaResponseDto);
    }

    @Test
    @DisplayName("test for withAddedCinemaRooms method")
    public void test3() {
        var cinemaRoomsList = List.of(
                CinemaRoom.builder().name("Name1").rows(10).places(10).build()
        );

        var cinemaRoomsListToAdd = List.of(
                CinemaRoom.builder().name("Name2").rows(11).places(11).build()
        );

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
                .cinemaRooms(cinemaRoomsList)
                .build();

        var cinemaWithNewCinemaRooms = cinema.withAddedCinemaRooms(cinemaRoomsListToAdd);
        var cinemaWithNewCinemaROoms2 = Cinema
                .builder()
                .id(1L)
                .name("Name")
                .address(address)
                .cinemaRooms(List.of(
                        CinemaRoom.builder().name("Name1").rows(10).places(10).build(),
                        CinemaRoom.builder().name("Name2").rows(11).places(11).build()
                ))
                .build();

        assertThat(cinemaWithNewCinemaRooms)
                .isEqualTo(cinemaWithNewCinemaROoms2);
    }
}
