package model.seance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.cinema_room.CinemaRoom;
import model.movie.Movie;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSeanceResponseDto {
    private Long id;
    private Movie movie;
    private CinemaRoom cinemaRoom;
    private LocalDate date;
}
