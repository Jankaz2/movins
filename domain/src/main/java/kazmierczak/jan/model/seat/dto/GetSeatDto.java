package kazmierczak.jan.model.seat.dto;

import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import kazmierczak.jan.model.ticket.dto.GetTicketDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetSeatDto {
    private Long id;
    private Integer row;
    private Integer place;
    private GetCinemaRoomDto cinemaRoom;
}
