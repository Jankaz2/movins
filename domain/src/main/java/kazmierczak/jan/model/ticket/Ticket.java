package kazmierczak.jan.model.ticket;

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
}
