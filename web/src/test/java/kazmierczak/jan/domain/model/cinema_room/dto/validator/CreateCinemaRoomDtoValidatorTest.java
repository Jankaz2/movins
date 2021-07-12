package kazmierczak.jan.domain.model.cinema_room.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.config.validator.exception.ValidatorException;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
import kazmierczak.jan.model.cinema_room.dto.validator.CreateCinemaRoomDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static kazmierczak.jan.config.validator.Validator.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateCinemaRoomDtoValidatorTest {
    @Test
    @DisplayName("when object is null")
    public void test1() {
        var validator = new CreateCinemaRoomDtoValidator();

        assertThatThrownBy(() -> validate(validator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessage("[VALIDATION ERRORS] -> CreateCinemaRoomDto: is null");
    }

    @Test
    @DisplayName("when name is incorrect")
    public void test2() {
        var validator = new CreateCinemaRoomDtoValidator();

        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name("Abc")
                .rows(10)
                .places(10)
                .build();

        assertThatThrownBy(() -> validate(validator,cinemaRoomDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("name: is incorrect: Abc");
    }

    @Test
    @DisplayName("when rows number is incorrect")
    public void test3() {
        var validator = new CreateCinemaRoomDtoValidator();

        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name("Abcd")
                .rows(0)
                .places(10)
                .build();

        assertThatThrownBy(() -> validate(validator,cinemaRoomDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("rows: number is less than 1");
    }

    @Test
    @DisplayName("when places number is incorrect")
    public void test4() {
        var validator = new CreateCinemaRoomDtoValidator();

        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name("Abcd")
                .rows(10)
                .places(0)
                .build();

        assertThatThrownBy(() -> validate(validator,cinemaRoomDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("places: number is less than 1");
    }
}
