package kazmierczak.jan.model.ticket.dto;

import kazmierczak.jan.model.seat.dto.CreateSeatDto;
import kazmierczak.jan.model.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketDto {
    private Long userId;
    private Long seanceId;
    private CreateSeatDto seat;
    private Double price;
    private LocalDate purchaseDate;

    /**
     *
     * @return Ticket object mapped from TicketDto
     */
    public Ticket toTicket() {
        return Ticket
                .builder()
                .seat(seat.toSeat())
                .price(price)
                .purchaseDate(purchaseDate)
                .build();
    }
}
