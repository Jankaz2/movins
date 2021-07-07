package kazmierczak.jan.domain.model.user.dto.validator;

import kazmierczak.jan.config.validator.Validator;
import kazmierczak.jan.config.validator.exception.ValidatorException;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import kazmierczak.jan.model.user.dto.validator.CreateUserDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateUserDtoValidatorTest {
    @Test
    @DisplayName("when object is null")
    public void test1() {
        var validator = new CreateUserDtoValidator();
        assertThatThrownBy(() -> Validator.validate(validator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessage("[VALIDATION ERRORS] -> create user dto: is null");
    }

    @Test
    @DisplayName("when name is wrong")
    public void test2() {
        var validator = new CreateUserDtoValidator();
        var createUserDto = CreateUserDto.builder()
                .username("a")
                .age(10)
                .email("john2001@wp.pl")
                .build();
        assertThatThrownBy(() -> Validator.validate(validator, createUserDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("name: is incorrect: a");
    }

    @Test
    @DisplayName("when surname is wrong")
    public void test3() {
        var validator = new CreateUserDtoValidator();
        var createUserDto = CreateUserDto.builder()
                .username("Jonas")
                .age(10)
                .email("john2001@wp.pl")
                .build();
        assertThatThrownBy(() -> Validator.validate(validator, createUserDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] ->")
                .hasMessageContaining("surname: is incorrect: a");
    }

    @Test
    @DisplayName("when age is wrong")
    public void test4() {
        var validator = new CreateUserDtoValidator();
        var createUserDto = CreateUserDto.builder()
                .username("Jonas")
                .age(0)
                .email("john2001@wp.pl")
                .build();
        assertThatThrownBy(() -> Validator.validate(validator, createUserDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] ->")
                .hasMessageContaining("age: is incorrect: 0");
    }

    @Test
    @DisplayName("when email is wrong")
    public void test5() {
        var validator = new CreateUserDtoValidator();
        var createUserDto = CreateUserDto.builder()
                .username("Jonas")
                .age(10)
                .email("john2001_@wp.pl")
                .build();
        assertThatThrownBy(() -> Validator.validate(validator, createUserDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] ->")
                .hasMessageContaining("email: is incorrect: john2001_@wp.pl");
    }
}
