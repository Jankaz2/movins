package model.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMovieResponseDto {
    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private LocalDate releaseDate;
}
