package model.seat;

import lombok.*;
import model.cinema_room.CinemaRoom;
import model.seat.dto.CreateSeatDto;
import model.seat.dto.CreateSeatResponseDto;
import model.ticket.Ticket;

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
}
