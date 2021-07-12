package kazmierczak.jan.model.cinema_room;

import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomResponseDto;
import lombok.*;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seat.Seat;

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

    /**
     *
     * @return CreateCinemaRoomResponseDto
     */
    public CreateCinemaRoomResponseDto toCreateCinemaRoomResponseDto() {
        return CreateCinemaRoomResponseDto
                .builder()
                .id(id)
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
                '}';
    }
}