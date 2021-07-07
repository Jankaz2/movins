package kazmierczak.jan.model.ticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.seance.dto.CreateSeanceDto;
import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.model.user.dto.CreateUserDto;

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
