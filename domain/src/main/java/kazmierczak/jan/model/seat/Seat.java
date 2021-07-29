package kazmierczak.jan.model.seat;

import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;
import kazmierczak.jan.model.seat.dto.GetSeatDto;
import kazmierczak.jan.model.ticket.Ticket;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Seat {
    Long id;
    Integer row;
    Integer place;
    CinemaRoom cinemaRoom;
    List<Ticket> tickets;

    /**
     *
     * @param seats we want to map
     * @return list of CreateSeatDto objects
     */
    public static List<CreateSeatDto> toListOfCreateSeatDto(List<Seat> seats) {
        return seats
                .stream()
                .map(Seat::toCreateSeatDto)
                .toList();
    }

    /**
     *
     * @return CreateSeatDto object
     */
    public CreateSeatDto toCreateSeatDto() {
        return CreateSeatDto
                .builder()
                .row(row)
                .place(place)
                .build();
    }

    /**
     *
     * @return GetSeatDto object
     */
    public GetSeatDto toGetSeatDto() {
        return GetSeatDto
                .builder()
                .id(id)
                .row(row)
                .place(place)
                .cinemaRoom(cinemaRoom.toGetCinemaRoomDto())
                .build();
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
}
