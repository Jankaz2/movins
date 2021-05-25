package model.seance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.cinema_room.CinemaRoom;
import model.movie.Movie;
import model.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSeanceDto {
    private Long id;
    private Movie movie;
    private CinemaRoom cinemaRoom;
    private LocalDate date;
    private List<Ticket> tickets;
}
