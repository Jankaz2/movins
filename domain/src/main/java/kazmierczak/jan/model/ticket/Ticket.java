package kazmierczak.jan.model.ticket;

import kazmierczak.jan.model.ticket.dto.CreateTicketResponseDto;
import kazmierczak.jan.model.ticket.dto.GetTicketDto;
import lombok.*;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.user.User;

import java.time.LocalDate;

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
    LocalDate purchaseDate;

    /**
     * @return createTicketResponseDto object mapped from Ticket
     */
    public CreateTicketResponseDto toCreateTicketResponseDto() {
        return CreateTicketResponseDto
                .builder()
                .id(id)
                .build();
    }

    /**
     *
     * @return getTicketDto object mapped from Ticket
     */
    public GetTicketDto toGetTicketDto() {
        return  GetTicketDto
                .builder()
                .id(id)
                .seat(seat.toGetSeatDto())
                .seance(seance.toGetSeanceDto())
                .user(user.toGetUserDto())
                .price(price)
                .purchaseDate(purchaseDate)
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
