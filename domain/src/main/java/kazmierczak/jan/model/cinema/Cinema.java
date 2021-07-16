package kazmierczak.jan.model.cinema;

import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import lombok.*;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.dto.CreateCinemaResponseDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema_room.CinemaRoom;

import java.util.ArrayList;
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
     *
     * @param newCinemaRooms cinema rooms objects that we want to add to cinema
     * @return cinema with new cinema rooms objects added
     */
    public Cinema withAddedCinemaRooms(List<CinemaRoom> newCinemaRooms) {
        var cinemas = new ArrayList<>(cinemaRooms);
        cinemas.addAll(newCinemaRooms);

        return Cinema
                .builder()
                .id(id)
                .name(name)
                .address(address)
                .cinemaRooms(cinemas)
                .build();
    }

    /**
     *
     * @param newCinema object we want to set as a new
     * @return updated Cinema
     */
    public Cinema withChangedData(CreateCinemaDto newCinema) {
        return Cinema
                .builder()
                .id(id)
                .name(newCinema.getName())
                .address(newCinema.getAddress().toAddress())
                .cinemaRooms(newCinema.getCinemaRooms().stream().map(CreateCinemaRoomDto::toCinemaRoom).toList())
                .build();
    }

    /**
     * @return GetCinemaDto object mapped from Cinema
     */
    public GetCinemaDto toGetCinemaDto() {
        return GetCinemaDto
                .builder()
                .id(id)
                .name(name)
                .address(address.toCreateAddressDto())
                .cinemaRooms(cinemaRooms.stream().map(CinemaRoom::toCreateCinemaRoomDto).toList())
                .build();
    }

    /**
     * @return CreateCinemaResponseDto object mapped from Cinema
     */
    public CreateCinemaResponseDto toCreateCinemaResponseDto() {
        return CreateCinemaResponseDto
                .builder()
                .cinemaId(id)
                .build();
    }

    public void setCinemaRooms(List<CinemaRoom> cinemaRooms) {
        this.cinemaRooms = cinemaRooms;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}