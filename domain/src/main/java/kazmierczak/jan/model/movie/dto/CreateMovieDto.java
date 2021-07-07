package kazmierczak.jan.model.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.movie.Movie;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMovieDto {
    private String title;
    private String genre;
    private Integer duration;
    private LocalDate releaseDate;

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
                .build();
    }
}
