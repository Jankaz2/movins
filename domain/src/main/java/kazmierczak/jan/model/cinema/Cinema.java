package kazmierczak.jan.model.cinema;

import lombok.*;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.dto.CreateCinemaResponseDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema_room.CinemaRoom;

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

    /**
     * @return GetCinemaDto object mapped from Cinema
     */
    public GetCinemaDto toGetCinemaDto() {
        return GetCinemaDto
                .builder()
                .id(id)
                .name(name)
                .address(address.toCreateAddressDto())
             /*   .cinemaRooms(cinemaRooms
                        .stream()
                        .map(CinemaRoom::toCreateCinemaRoomDto)
                        .toList())*/
                .build();
    }

    /**
     * @return CreateCinemaResponseDto object mapped from Cinema
     */
    public CreateCinemaResponseDto toCreateCinemaResponseDto() {
        return CreateCinemaResponseDto
                .builder()
                .addedCinemaId(id)
                .build();
    }

    public void setCinemaRooms(List<CinemaRoom> cinemaRooms) {
        this.cinemaRooms = cinemaRooms;
    }
}