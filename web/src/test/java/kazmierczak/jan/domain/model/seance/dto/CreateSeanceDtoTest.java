package kazmierczak.jan.domain.model.seance.dto;

import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateSeanceDtoTest {

    @Test
    @DisplayName("when method toSeance works correct")
    public void test1() {
        var creatreMovieDto = CreateMovieDto
                .builder()
                .title("Title")
                .genre("Genre")
                .duration(1)
                .releaseDate(LocalDate.of(2021, 12, 12))
                .build();

        var seance = Seance
                .builder()
                .movie(creatreMovieDto.toMovie())
                .date(LocalDate.of(2021, 12, 12))
                .build();

        var createSeanceDto = CreateSeanceDto
                .builder()
                .date(LocalDate.of(2021, 12, 12))
                .build();

        assertThat(createSeanceDto.toSeance())
                .isEqualTo(seance);
    }
}
