package kazmierczak.jan.model.seat.dto;

import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
