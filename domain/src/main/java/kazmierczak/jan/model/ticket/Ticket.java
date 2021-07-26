package kazmierczak.jan.model.ticket;

import kazmierczak.jan.model.ticket.dto.CreateTicketResponseDto;
import lombok.*;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Ticket {
    Long id;
    Seat seat;
    Seance seance;
    User user;
    Double price;

    /**
     *
     * @return createTicketResponseDto object mapped from Ticket
     */
    public CreateTicketResponseDto toCreateTicketResponseDto() {
        return CreateTicketResponseDto
                .builder()
                .id(id)
                .build();
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
