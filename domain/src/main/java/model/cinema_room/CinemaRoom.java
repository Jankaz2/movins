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

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public String toString() {
        return "CinemaRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rows=" + rows +
                ", places=" + places +
                ", cinema=" + cinema.toGetCinemaDto() +
                ", seats=" + seats +
                ", seances=" + seances +
                '}';
    }
}