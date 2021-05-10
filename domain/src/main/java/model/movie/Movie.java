package model.movie;

import lombok.*;

import java.time.LocalDate;

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
}
