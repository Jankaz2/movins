package model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.address.dto.CreateAddressDto;
import model.cinema_room.dto.CreateCinemaRoomDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCinemaResponseDto {
    private Long id;
    private String name;
    private CreateAddressDto address;
    private List<CreateCinemaRoomDto> cinemaRooms;
}