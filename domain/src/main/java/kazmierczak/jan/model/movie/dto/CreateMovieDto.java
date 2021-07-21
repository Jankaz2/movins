package kazmierczak.jan.model.movie.dto;

import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.movie.Movie;

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
    private List<CreateSeanceDto> seances;

    /**
     * @return Movie obejct mapped from MovieDto
     */
    public Movie toMovie() {
        return Movie
                .builder()
                .title(title)
                .genre(genre)
                .duration(duration)
                .releaseDate(releaseDate)
                .seances(seances
                        .stream()
                        .map(CreateSeanceDto::toSeance)
                        .toList())
                .build();
    }
}
