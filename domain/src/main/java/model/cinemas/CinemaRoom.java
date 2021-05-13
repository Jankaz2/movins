package model.cinemas;

import lombok.*;

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
