package kazmierczak.jan.model.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.seance.Seance;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMovieDto {
    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private LocalDate releaseDate;
    private List<Seance> seances;
}
