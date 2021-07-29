package kazmierczak.jan.model.seat;

import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.ticket.Ticket;

import java.util.List;
import java.util.function.Function;

public interface SeatUtils {
    /**
     * map seat object to id of this seat
     */
    Function<Seat, Long> toSeatId = seat -> seat.id;

    /**
     * map seat object to row of this seat
     */
    Function<Seat, Integer> toSeatRow = seat -> seat.row;

    /**
     * map seat object to place of this seat
     */
    Function<Seat, Integer> toSeatPlace = seat -> seat.place;

    /**
     * map seat object to cinemaRoom of this seat
     */
    Function<Seat, CinemaRoom> toSeatCinemaRoom = seat -> seat.cinemaRoom;

    /**
     * map seat object to tickets of this seat
     */
    Function<Seat, List<Ticket>> toSeatTicket = seat -> seat.tickets;

    /**
     * map seat object to booked value of this seat
     */
    Function<Seat, Boolean> toSeatBooked = seat -> seat.booked;
 }
