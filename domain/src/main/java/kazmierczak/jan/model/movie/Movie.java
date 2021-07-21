package kazmierczak.jan.model.movie;

import kazmierczak.jan.model.movie.dto.CreateMovieResponseDto;
import kazmierczak.jan.model.movie.dto.GetMovieDto;
import lombok.*;
import kazmierczak.jan.model.seance.Seance;

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

    /**
     *
     * @return createMovieResponseDto object
     */
    public CreateMovieResponseDto toCreateMovieResponseDto() {
        return CreateMovieResponseDto
                .builder()
                .id(id)
                .build();
    }

    /**
     *
     * @return getMovieDto object
     */
    public GetMovieDto toGetMovieDto() {
        return GetMovieDto
                .builder()
                .title(title)
                .genre(genre)
                .duration(duration)
                .releaseDate(releaseDate)
                .seances(seances)
                .build();
    }
}
