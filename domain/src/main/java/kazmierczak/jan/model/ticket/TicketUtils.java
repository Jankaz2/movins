package kazmierczak.jan.model.ticket;

import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.user.User;

import java.time.LocalDate;
import java.util.function.Function;

public interface TicketUtils {
    /**
     * map ticket object to id of this obejct
     */
    Function<Ticket, Long> toTicketId = ticket -> ticket.id;

    /**
     * map ticket object to seat of this obejct
     */
    Function<Ticket, Seat> toTicketSeat = ticket -> ticket.seat;

    /**
     * map ticket object to seance of this obejct
     */
    Function<Ticket, Seance> toTicketSeance = ticket -> ticket.seance;

    /**
     * map ticket object to user of this obejct
     */
    Function<Ticket, User> toTicketUser = ticket -> ticket.user;

    /**
     * map ticket object to price of this obejct
     */
    Function<Ticket, Double> toTicketPrice = ticket -> ticket.price;

    /**
     * map ticket object to purhcase date of this obejct
     */
    Function<Ticket, LocalDate> toTicketPurchaseDate = ticket -> ticket.purchaseDate;
}
