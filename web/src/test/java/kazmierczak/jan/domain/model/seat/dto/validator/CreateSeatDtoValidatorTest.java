package kazmierczak.jan.domain.model.seat.dto.validator;

import kazmierczak.jan.config.validator.exception.ValidatorException;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;
import kazmierczak.jan.model.seat.dto.validator.CreateSeatDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static kazmierczak.jan.config.validator.Validator.validate;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class CreateSeatDtoValidatorTest {
    @Test
    @DisplayName("when object is null")
    public void test1() {
        var validator = new CreateSeatDtoValidator();
        assertThatThrownBy(() -> validate(validator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessage("[VALIDATION ERRORS] -> createSeatDto: is null");
    }

    @Test
    @DisplayName("when object has wrong row")
    public void test2() {
        var validator = new CreateSeatDtoValidator();
        var seatDto = CreateSeatDto
                .builder()
                .row(0)
                .place(10)
                .cinemaRoomId(1L)
                .build();

        assertThatThrownBy(() -> validate(validator, seatDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("row: is incorrect: 0");
    }

    @Test
    @DisplayName("when object has wrong place")
    public void test3() {
        var validator = new CreateSeatDtoValidator();
        var seatDto = CreateSeatDto
                .builder()
                .row(10)
                .place(0)
                .cinemaRoomId(1L)
                .build();

        assertThatThrownBy(() -> validate(validator, seatDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("place: is incorrect: 0");
    }

    @Test
    @DisplayName("when object has wrong cinemaRoomId")
    public void test4() {
        var validator = new CreateSeatDtoValidator();
        var seatDto = CreateSeatDto
                .builder()
                .row(10)
                .place(10)
                .cinemaRoomId(null)
                .build();

        assertThatThrownBy(() -> validate(validator, seatDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("cinemaRoomId: is incorrect: null");
    }
}
