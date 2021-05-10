package model.seance;

import lombok.*;
import model.cinemas.CinemaRoom;
import model.movie.Movie;

import java.time.LocalDate;

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
}
