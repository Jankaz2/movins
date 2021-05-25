package model.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.movie.Movie;
import model.seance.Seance;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMovieDto {
    private String title;
    private String genre;
    private Integer duration;
    private LocalDate releaseDate;
    private List<Seance> seances;

    /**
     *
     * @return Movie obejct mapped from MovieDto
     */
    public Movie toMovie() {
        return Movie
                .builder()
                .title(title)
                .genre(genre)
                .duration(duration)
                .releaseDate(releaseDate)
                .seances(seances)
                .build();
    }
}
