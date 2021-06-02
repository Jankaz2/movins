package model.cinema_room;

import lombok.*;
import model.cinema.Cinema;
import model.cinema.dto.CreateCinemaResponseDto;
import model.cinema_room.dto.CreateCinemaRoomDto;
import model.cinema_room.dto.CreateCinemaRoomResponseDto;
import model.seance.Seance;
import model.seat.Seat;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CinemaRoom {
    Long id;
    String name;
    Integer rows;
    Integer places;
    Cinema cinema;
    List<Seat> seats;
    List<Seance> seances;

    /**
     *
     * @param cinemaRooms we want to map
     * @return list of CreateCinemaRoomDto
     */
    public static List<CreateCinemaRoomDto> toListOfCreateCinemaRoomDto(List<CinemaRoom> cinemaRooms) {
        return cinemaRooms
                .stream()
                .map(CinemaRoom::toCreateCinemaRoomDto)
                .toList();
    }

    /**
     *
     * @return CreateCinemaRoomDto object
     */
    public CreateCinemaRoomDto toCreateCinemaRoomDto() {
        return CreateCinemaRoomDto
                .builder()
                .name(name)
                .rows(rows)
                .places(places)
                .build();
    }
}