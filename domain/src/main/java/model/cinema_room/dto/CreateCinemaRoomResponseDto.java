package model.cinema_room.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.cinema.Cinema;
import model.seat.Seat;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCinemaRoomResponseDto {
    private Long id;
    private String name;
    private Integer rows;
    private Integer places;
}
