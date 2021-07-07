package kazmierczak.jan.model.seat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.ticket.Ticket;

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
