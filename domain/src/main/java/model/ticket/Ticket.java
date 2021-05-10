package model.ticket;

import lombok.*;
import model.cinemas.Seat;
import model.seance.Seance;
import model.user.User;

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
}
