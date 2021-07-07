package kazmierczak.jan.model.cinema_room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCinemaRoomDto {
    private Long id;
    private String name;
    private Integer rows;
    private Integer places;
}