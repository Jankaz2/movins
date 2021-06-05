package kazmierczak.jan.domain.model.seat.dto;

import model.seat.Seat;
import model.seat.dto.CreateSeatDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateSeatDtoTest {

    @Test
    @DisplayName("when method toSeat works correct")
    public void test1() {
        var seat = Seat
                .builder()
                .row(1)
                .place(1)
                .build();

        var createSeatDto = CreateSeatDto
                .builder()
                .row(1)
                .place(1)
                .build();

        assertThat(createSeatDto.toSeat())
                .isEqualTo(seat);
    }
}
