package kazmierczak.jan.domain.model.address.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.config.validator.exception.ValidatorException;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import kazmierczak.jan.model.address.dto.validator.CreateAddressDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateAddressDtoValidatorTest {
    @Test
    @DisplayName("when object is null")
    public void test1() {
        var validator = new CreateAddressDtoValidator();
        assertThatThrownBy(() -> Validator.validate(validator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessage("[VALIDATION ERRORS] -> createAddressDto: is null");
    }

    @Test
    @DisplayName("when city is wrong")
    public void test2() {
        var validator = new CreateAddressDtoValidator();
        var createAddressDto = CreateAddressDto.builder()
                .city("ab")
                .street("Abc")
                .number(1)
                .build();

        assertThatThrownBy(() -> Validator.validate(validator, createAddressDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("city: is incorrect: ab");
    }

    @Test
    @DisplayName("when street is wrong")
    public void test3() {
        var validator = new CreateAddressDtoValidator();
        var createAddressDto = CreateAddressDto.builder()
                .city("Abc")
                .street("ab")
                .number(1)
                .build();

        assertThatThrownBy(() -> Validator.validate(validator, createAddressDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("street: is incorrect: ab");
    }

    @Test
    @DisplayName("when number is wrong")
    public void test4() {
        var validator = new CreateAddressDtoValidator();
        var createAddressDto = CreateAddressDto.builder()
                .city("Abc")
                .street("Abc")
                .number(-1)
                .build();

        assertThatThrownBy(() -> Validator.validate(validator, createAddressDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("number: is incorrect: -1");
    }
}
