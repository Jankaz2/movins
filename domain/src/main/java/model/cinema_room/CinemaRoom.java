package model.cinema_room;

import lombok.*;
import model.cinema.Cinema;
import model.seat.Seat;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class CinemaRoom {
    Long id;
    String name;
    Integer rows;
    Integer places;
    Cinema cinema;
    List<Seat> seats;
}
