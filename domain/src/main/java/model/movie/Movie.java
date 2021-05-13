package model.movie;

import lombok.*;
import model.seance.Seance;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Movie {
    Long id;
    String title;
    String genre;
    Integer duration;
    LocalDate releaseDate;
    List<Seance> seances;
}
