package kazmierczak.jan.model.cinema_room;

import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomResponseDto;
import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import lombok.*;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seat.Seat;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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
     * @param cinemaRoomDto we want to set
     * @return cinemaRoom with new data
     */
    public CinemaRoom withChangedData(CreateCinemaRoomDto cinemaRoomDto) {
        return CinemaRoom
                .builder()
                .id(id)
                .name(cinemaRoomDto.getName())
                .rows(cinemaRoomDto.getRows())
                .places(cinemaRoomDto.getPlaces())
                .cinema(cinema)
                .seats(seats)
                .places(places)
                .build();
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

    /**
     *
     * @return GetCinemaRoomDto object 
     */
    public GetCinemaRoomDto toGetCinemaRoomDto() {
        return GetCinemaRoomDto
                .builder()
                .id(id)
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
                '}';
    }
}