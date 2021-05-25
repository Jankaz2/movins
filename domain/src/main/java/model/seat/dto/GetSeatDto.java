package model.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.cinema_room.CinemaRoom;
import model.ticket.Ticket;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSeatDto {
    private Long id;
    private Integer row;
    private Integer place;
    private CinemaRoom cinemaRoom;
    private List<Ticket> tickets;
}
