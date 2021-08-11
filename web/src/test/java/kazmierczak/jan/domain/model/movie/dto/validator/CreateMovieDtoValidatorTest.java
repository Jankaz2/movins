package kazmierczak.jan.domain.model.movie.dto.validator;

import kazmierczak.jan.config.validator.exception.ValidatorException;
import kazmierczak.jan.model.movie.dto.CreateMovieDto;
import kazmierczak.jan.model.movie.dto.validator.CreateMovieDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static kazmierczak.jan.config.validator.Validator.validate;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class CreateMovieDtoValidatorTest {

    @Test
    @DisplayName("when object is null")
    public void test1() {
        var validator = new CreateMovieDtoValidator();
        assertThatThrownBy(() -> validate(validator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessage("[VALIDATION ERRORS] -> createMovieDto: is null");
    }

    @Test
    @DisplayName("when object has wrong title")
    public void test2() {
        var validator = new CreateMovieDtoValidator();
        var movieDto = CreateMovieDto
                .builder()
                .title("A")
                .genre("Genre")
                .duration(150)
                .releaseDate(LocalDate.of(2020, 12, 12))
                .seances(new ArrayList<>())
                .build();

        assertThatThrownBy(() -> validate(validator, movieDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("title: is incorrect: A");
    }

    @Test
    @DisplayName("when object has wrong genre")
    public void test3() {
        var validator = new CreateMovieDtoValidator();
        var movieDto = CreateMovieDto
                .builder()
                .title("Abc")
                .genre("G")
                .duration(150)
                .releaseDate(LocalDate.of(2020, 12, 12))
                .seances(new ArrayList<>())
                .build();

        assertThatThrownBy(() -> validate(validator, movieDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("genre: is incorrect: G");
    }

    @Test
    @DisplayName("when object has wrong duration")
    public void test4() {
        var validator = new CreateMovieDtoValidator();
        var movieDto = CreateMovieDto
                .builder()
                .title("Abc")
                .genre("Genre")
                .duration(0)
                .releaseDate(LocalDate.of(2020, 12, 12))
                .seances(new ArrayList<>())
                .build();

        assertThatThrownBy(() -> validate(validator, movieDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("duration: is incorrect: 0");
    }

    @Test
    @DisplayName("when object has wrong date")
    public void test5() {
        var validator = new CreateMovieDtoValidator();
        var movieDto = CreateMovieDto
                .builder()
                .title("Abc")
                .genre("Genre")
                .duration(150)
                .releaseDate(null)
                .seances(new ArrayList<>())
                .build();

        assertThatThrownBy(() -> validate(validator, movieDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("releaseDate: is incorrect: null");
    }
}
