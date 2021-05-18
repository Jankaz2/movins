package model.seance;

import lombok.*;
import model.cinema_room.CinemaRoom;
import model.movie.Movie;
import model.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Seance {
    Long id;
    Movie movie;
    CinemaRoom cinemaRoom;
    LocalDate date;
    List<Ticket> tickets;
}
