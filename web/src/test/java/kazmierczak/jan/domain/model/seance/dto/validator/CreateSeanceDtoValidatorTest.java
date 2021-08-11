package kazmierczak.jan.domain.model.seance.dto.validator;

import kazmierczak.jan.config.validator.exception.ValidatorException;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import kazmierczak.jan.model.seance.dto.validator.CreateSeanceDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static kazmierczak.jan.config.validator.Validator.validate;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class CreateSeanceDtoValidatorTest {

    @Test
    @DisplayName("when object is null")
    public void test1() {
        var validator = new CreateSeanceDtoValidator();
        assertThatThrownBy(() -> validate(validator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessage("[VALIDATION ERRORS] -> createSeanceDto: is null");
    }

    @Test
    @DisplayName("when object has wrong date")
    public void test2() {
        var validator = new CreateSeanceDtoValidator();
        var seanceDto = CreateSeanceDto
                .builder()
                .date(null)
                .build();

        assertThatThrownBy(() -> validate(validator, seanceDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("date: is incorrect: null");
    }
}
