package model.cinema.dto;

import lombok.*;
import model.address.dto.CreateAddressDto;
import model.cinema.Cinema;
import model.cinema_room.dto.CreateCinemaRoomDto;

import java.util.List;

import static model.cinema_room.dto.CreateCinemaRoomDto.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
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
                .build();
    }
}