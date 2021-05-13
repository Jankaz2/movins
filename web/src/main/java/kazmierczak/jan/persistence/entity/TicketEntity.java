package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import model.ticket.Ticket;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tickets")
public class TicketEntity extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "seat_id")
    private SeatEntity seat;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "seance_id")
    private SeanceEntity seance;

    private Double price;

    /**
     *
     * @return Ticket object mapped from TicketEntity
     */
    public Ticket toTicket() {
        return Ticket.builder()
                .id(id)
                .seance(seance.toSeance())
                .seat(seat.toSeat())
                .user(user.toUser())
                .price(price)
                .build();
    }
}
