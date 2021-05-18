package model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.address.Address;
import model.cinema_room.CinemaRoom;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCinemaDto {
    private Long id;
    private String name;
    private Address address;
    private List<CinemaRoom> cinemaRooms;
}
