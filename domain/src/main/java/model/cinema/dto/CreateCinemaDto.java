package model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.address.dto.CreateAddressDto;
import model.cinema.Cinema;
import model.cinema_room.CinemaRoom;
import model.cinema_room.dto.CreateCinemaRoomDto;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCinemaDto {
    private String name;
    private CreateAddressDto address;
    private List<CreateCinemaRoomDto> cinemaRooms;

    /**
     * @return Cinema object mapped from CinemaDto
     */
    public Cinema toCinema() {
        return Cinema
                .builder()
                .name(name)
                .address(address.toAddress())
                .cinemaRooms(CreateCinemaRoomDto.toListOfCinemaRooms(cinemaRooms))
                .build();
    }
}