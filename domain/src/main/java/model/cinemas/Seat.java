package model.cinemas;

import lombok.*;
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
