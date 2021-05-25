package model.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.cinema_room.CinemaRoom;
import model.seat.Seat;
import model.ticket.Ticket;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateSeatDto {
    private Integer row;
    private Integer place;
    private CinemaRoom cinemaRoom;
    private List<Ticket> tickets;

    /**
     *
     * @return Seat object mapped from SeatDto
     */
    public Seat toSeat() {
        return Seat
                .builder()
                .row(row)
                .place(place)
                .cinemaRoom(cinemaRoom)
                .tickets(tickets)
                .build();
    }
}
