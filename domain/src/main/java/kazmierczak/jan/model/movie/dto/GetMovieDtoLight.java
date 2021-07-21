package kazmierczak.jan.model.movie.dto;

import kazmierczak.jan.model.seance.dto.GetSeanceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMovieDtoLight {
    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private LocalDate releaseDate;
}
