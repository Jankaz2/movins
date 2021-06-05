package model.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.seance.Seance;
import model.seance.dto.CreateSeanceDto;
import model.seat.Seat;
import model.ticket.Ticket;
import model.user.User;
import model.user.dto.CreateUserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateTicketDto {
    private Long id;
    private CreateSeanceDto seance;
    private CreateUserDto user;
    private Double price;

    /**
     *
     * @return Ticket object mapped from TicketDto
     */
    public Ticket toTicket() {
        return Ticket
                .builder()
                .seance(seance.toSeance())
                .user(user.toUser())
                .price(price)
                .build();
    }
}
