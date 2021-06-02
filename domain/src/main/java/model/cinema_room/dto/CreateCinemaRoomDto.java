package model.cinema_room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.cinema_room.CinemaRoom;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCinemaRoomDto {
    private String name;
    private Integer rows;
    private Integer places;

    /**
     * @param cinemaRooms list we want to map
     * @return list of CinemaRoom objects
     */
    public static List<CinemaRoom> toListOfCinemaRooms(List<CreateCinemaRoomDto> cinemaRooms) {
        return cinemaRooms
                .stream()
                .map(CreateCinemaRoomDto::toCinemaRoom)
                .toList();
    }

    /**
     *
     * @return CinemaRoom object mapped from CinemaRoomDto
     */
    public CinemaRoom toCinemaRoom() {
        return CinemaRoom
                .builder()
                .name(name)
                .rows(rows)
                .places(places)
                .build();
    }
}
