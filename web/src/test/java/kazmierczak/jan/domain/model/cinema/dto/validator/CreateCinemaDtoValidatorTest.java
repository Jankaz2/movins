package kazmierczak.jan.domain.model.cinema.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.config.validator.exception.ValidatorException;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import kazmierczak.jan.model.cinema.dto.validator.CreateCinemaDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static kazmierczak.jan.config.validator.Validator.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateCinemaDtoValidatorTest {
    @Test
    @DisplayName("when object is null")
    public void test1() {
        var validator = new CreateCinemaDtoValidator();
        assertThatThrownBy(() -> validate(validator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessage("[VALIDATION ERRORS] -> createCinemaDto: is null");
    }

    @Test
    @DisplayName("when name is incorrect")
    public void test2() {
        var validator = new CreateCinemaDtoValidator();
        var address = CreateAddressDto.builder()
                .city("Abc")
                .street("Abc")
                .number(1)
                .build();
        var createCinemaDto = CreateCinemaDto
                .builder()
                .address(address)
                .cinemaRooms(new ArrayList<>())
                .name("Abc")
                .build();

        assertThatThrownBy(() -> Validator.validate(validator, createCinemaDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("name: is incorrect: Abc");
    }
}
