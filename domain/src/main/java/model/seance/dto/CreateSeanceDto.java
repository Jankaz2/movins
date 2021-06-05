package model.seance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.cinema_room.CinemaRoom;
import model.movie.Movie;
import model.movie.dto.CreateMovieDto;
import model.seance.Seance;
import model.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSeanceDto {
    private CreateMovieDto movie;
    private LocalDate date;

    /**
     *
     * @return Seance object mapped from SeanceDto
     */
    public Seance toSeance() {
        return Seance
                .builder()
                .movie(movie.toMovie())
                .date(date)
                .build();
    }
}
