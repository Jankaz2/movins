package model.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.seance.Seance;
import model.seat.Seat;
import model.ticket.Ticket;
import model.user.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketDto {
    private Long id;
    private Seat seat;
    private Seance seance;
    private User user;
    private Double price;

    /**
     *
     * @return Ticket object mapped from TicketDto
     */
    public Ticket toTicket() {
        return Ticket
                .builder()
                .seat(seat)
                .seance(seance)
                .user(user)
                .price(price)
                .build();
    }
}
