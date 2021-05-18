package model.cinema.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.adress.Address;
import model.cinema.Cinema;
import model.cinema_room.CinemaRoom;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCinemaDto {
    private String name;
    private Address address;
    private List<CinemaRoom> cinemaRooms;

    /**
     *
     * @return Cinema object mapped from CinemaDto
     */
    public Cinema toCinema() {
        return Cinema
                .builder()
                .name(name)
                .address(address)
                .cinemaRooms(cinemaRooms)
                .build();
    }
}
