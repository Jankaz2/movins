package kazmierczak.jan.domain.model.movie.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CreateMovieDtoTest {

    @Test
    @DisplayName("when method toMovie works correct")
    public void test1() {
     /*   var createMovieDto = CreateMovieDto
                .builder()
                .title("Title")
                .genre("Genre")
                .duration(12)
                .releaseDate(LocalDate.of(2021, 12, 12))
                .seances(new ArrayList<>())
                .build();

        var movie = Movie
                .builder()
                .title("Title")
                .genre("Genre")
                .duration(12)
                .releaseDate(LocalDate.of(2021, 12, 12))
                .seances(new ArrayList<>())
                .build();

        assertThat(createMovieDto.toMovie())
                .isEqualTo(movie);*/
    }
}
