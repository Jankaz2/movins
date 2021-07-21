package kazmierczak.jan.model.seance.dto;

import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.ticket.Ticket;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSeanceDto {
    private Long id;
    private GetCinemaRoomDto cinemaRoom;
    private LocalDate date;
}
