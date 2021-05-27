package model.cinema;

import lombok.*;
import model.address.Address;
import model.cinema.dto.CreateCinemaResponseDto;
import model.cinema.dto.GetCinemaDto;
import model.cinema_room.CinemaRoom;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Cinema {
    Long id;
    String name;
    Address address;
    List<CinemaRoom> cinemaRooms;

    public GetCinemaDto toGetCinemaDto() {
        return GetCinemaDto
                .builder()
                .id(id)
                .name(name)
                .address(address.toCreateAddressDto())
                .build();
    }

    /**
     *
     * @return CreateCinemaResponseDto object mapped from Cinema
     */
    public CreateCinemaResponseDto toCreateCinemaResponseDto() {
        return CreateCinemaResponseDto
                .builder()
                .id(id)
                .name(name)
                .address(address.toCreateAddressDto())
                .build();
    }
}
