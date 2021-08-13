package kazmierczak.jan.domain.model.ticket.dto.validator;

import kazmierczak.jan.config.validator.exception.ValidatorException;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketDtoValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.of;
import static kazmierczak.jan.config.validator.Validator.validate;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
public class CreateTicketDtoValidatorTest {

    @Test
    @DisplayName("when object is null")
    public void test1() {
        var validator = new CreateTicketDtoValidator();
        assertThatThrownBy(() -> validate(validator, null))
                .isInstanceOf(ValidatorException.class)
                .hasMessage("[VALIDATION ERRORS] -> createTicketDto: is null");
    }

    @Test
    @DisplayName("when ticket has incorrect user id")
    public void test2() {
        var validator = new CreateTicketDtoValidator();

        var seat = CreateSeatDto
                .builder()
                .row(1)
                .place(1)
                .cinemaRoomId(1L)
                .build();

        var ticketDto = CreateTicketDto
                .builder()
                .userId(null)
                .seanceId(1L)
                .seat(seat)
                .price(2.0)
                .purchaseDate(of(2020, 10, 12))
                .build();

        assertThatThrownBy(() -> validate(validator, ticketDto))
                .isInstanceOf(ValidatorException.class)
                .hasMessageStartingWith("[VALIDATION ERRORS] -> ")
                .hasMessageContaining("userId: is incorrect: null");
    }
}
