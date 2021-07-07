package kazmierczak.jan.persistence.entity;

import kazmierczak.jan.persistence.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import kazmierczak.jan.model.ticket.Ticket;

import javax.persistence.*;

import java.util.List;

import static kazmierczak.jan.persistence.entity.SeanceEntity.*;
import static kazmierczak.jan.persistence.entity.SeatEntity.*;
import static kazmierczak.jan.persistence.entity.UserEntity.*;
import static kazmierczak.jan.model.ticket.TicketUtils.*;

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

    /**
     *
     * @param tickets list we want to map
     * @return list of ticket entity objects
     */
    public static List<TicketEntity> fromTicketsToEntityList(List<Ticket> tickets) {
        return tickets
                .stream()
                .map(TicketEntity::fromTicketToEntity)
                .toList();
    }

    /**
     *
     * @param ticket object we want to map
     * @return ticket entity object
     */
    public static TicketEntity fromTicketToEntity(Ticket ticket) {
        var ticketId = toTicketId.apply(ticket);
        var ticketSeance = toTicketSeance.apply(ticket);
        var ticketSeat= toTicketSeat.apply(ticket);
        var ticketUser= toTicketUser.apply(ticket);
        var ticketPrice= toTicketPrice.apply(ticket);

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
