package kazmierczak.jan.model.seance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.ticket.Ticket;

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
