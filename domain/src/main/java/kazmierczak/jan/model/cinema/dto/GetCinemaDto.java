package kazmierczak.jan.model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCinemaDto {
    private Long id;
    private String name;
    private CreateAddressDto address;
    private List<CreateCinemaRoomDto> cinemaRooms;
}