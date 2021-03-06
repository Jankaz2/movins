package kazmierczak.jan.model.cinema_room.dto;

import lombok.*;
import kazmierczak.jan.model.cinema_room.CinemaRoom;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCinemaRoomDto {
    private String name;
    private Integer rows;
    private Integer places;

    /**
     * @return CinemaRoom object mapped from CinemaRoomDto
     */
    public CinemaRoom toCinemaRoom() {
        return CinemaRoom
                .builder()
                .name(name)
                .rows(rows)
                .places(places)
                .build();
    }
}
