package kazmierczak.jan.web.persistence.entity;

import kazmierczak.jan.persistence.entity.MovieEntity;
import kazmierczak.jan.model.movie.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class MovieEntityTest {

    @Test
    @DisplayName("when mapping method works correclty")
    public void test1() {
        var movie = Movie
                .builder()
                .id(1L)
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(12)
                .genre("genre")
                .title("title")
                .seances(new ArrayList<>())
                .build();

        var movieEntity = MovieEntity
                .builder()
                .id(1L)
                .releaseDate(LocalDate.of(2000, 12, 12))
                .duration(12)
                .genre("genre")
                .title("title")
                .seances(new ArrayList<>())
                .build();

        assertThat(movieEntity.toMovie())
                .isEqualTo(movie);
    }
}
