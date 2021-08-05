package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static kazmierczak.jan.model.ticket.TicketUtils.*;
import static kazmierczak.jan.persistence.entity.SeanceEntity.fromSeanceToEntity;
import static kazmierczak.jan.persistence.entity.SeatEntity.fromSeatToEntity;
import static kazmierczak.jan.persistence.entity.UserEntity.fromUserToEntity;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "tickets")
public class TicketEntity extends BaseEntity {
    @ManyToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
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
     * @return Ticket object mapped from TicketEntity
     */
    public Ticket toTicket() {
        return Ticket.builder()
                .id(id)
                .seance(seance == null ? null : seance.toSeance())
                .seat(seat == null ? null : seat.toSeat())
                .user(user == null ? null : user.toUser())
                .price(price)
                .build();
    }

    /**
     * @param ticket object we want to map
     * @return ticket entity object
     */
    public static TicketEntity fromTicketToEntity(Ticket ticket) {
        var ticketId = toTicketId.apply(ticket);
        var ticketSeance = toTicketSeance.apply(ticket);
        var ticketUser = toTicketUser.apply(ticket);
        var ticketPrice = toTicketPrice.apply(ticket);
        var ticketSeat = toTicketSeat.apply(ticket);

        return TicketEntity
                .builder()
                .id(ticketId)
                .seance(fromSeanceToEntity(ticketSeance))
                .seat(fromSeatToEntity(ticketSeat))
                .user(fromUserToEntity(ticketUser))
                .price(ticketPrice)
                .build();
    }
}
