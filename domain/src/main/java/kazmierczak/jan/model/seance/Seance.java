package kazmierczak.jan.model.seance;

import lombok.*;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.ticket.Ticket;

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

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
}
