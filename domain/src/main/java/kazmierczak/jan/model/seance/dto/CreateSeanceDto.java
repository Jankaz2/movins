package kazmierczak.jan.model.seance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.seance.Seance;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSeanceDto {
    private LocalDate date;

    /**
     *
     * @return Seance object mapped from SeanceDto
     */
    public Seance toSeance() {
        return Seance
                .builder()
                .date(date)
                .build();
    }
}
