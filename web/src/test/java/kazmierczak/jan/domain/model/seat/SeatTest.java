package kazmierczak.jan.domain.model.seat;

import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class SeatTest {

    @Test
    @DisplayName("when method toCreateSeatDto works correct")
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

        assertThat(seat.toCreateSeatDto())
                .isEqualTo(createSeatDto);
    }

    @Test
    @DisplayName("when method toListOfCreateSeatDto works correct")
    public void test2() {
        var seatsList = List.of(
                Seat.builder().row(1).place(1).build(),
                Seat.builder().row(2).place(2).build()
        );

        var createSeatDtosList = List.of(
          CreateSeatDto.builder().row(1).place(1).build(),
          CreateSeatDto.builder().row(2).place(2).build()
        );

        assertThat(Seat.toListOfCreateSeatDto(seatsList))
                .isEqualTo(createSeatDtosList);
    }
}
