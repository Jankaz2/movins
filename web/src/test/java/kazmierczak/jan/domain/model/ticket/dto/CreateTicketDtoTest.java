package kazmierczak.jan.domain.model.ticket.dto;

import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.model.ticket.dto.CreateTicketDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.time.LocalDate.of;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CreateTicketDtoTest {

    @Test
    @DisplayName("when toTicket method works correct")
    public void test1() {
        var seat = Seat
                .builder()
                .row(1)
                .place(1)
                .build();

        var createTicketDto = CreateTicketDto
                .builder()
                .userId(1L)
                .seanceId(1L)
                .seat(seat.toCreateSeatDto())
                .price(2.5)
                .purchaseDate(of(2022, 12, 12))
                .build();

        var ticket = Ticket
                .builder()
                .seat(seat)
                .price(2.5)
                .purchaseDate(of(2022, 12, 12))
                .build();

        assertThat(createTicketDto.toTicket())
                .isEqualTo(ticket);
    }
}
