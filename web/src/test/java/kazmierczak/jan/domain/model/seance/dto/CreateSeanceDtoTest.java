package kazmierczak.jan.domain.model.seance.dto;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CreateSeanceDtoTest {

    @Test
    @DisplayName("when method toSeance works correct")
    public void test1() {
        var seance = Seance
                .builder()
                .date(of(2021, 12, 12))
                .build();

        var createSeanceDto = CreateSeanceDto
                .builder()
                .date(of(2021, 12, 12))
                .build();

        assertThat(createSeanceDto.toSeance())
                .isEqualTo(seance);
    }
}
