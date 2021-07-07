package kazmierczak.jan.model.cinema.dto;

import lombok.*;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;

import java.util.List;

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