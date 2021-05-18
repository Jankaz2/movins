package model.seat;

import lombok.*;
import model.cinema_room.CinemaRoom;
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
}
