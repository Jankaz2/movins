package model.cinemas;

import lombok.*;
import model.adress.Address;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Cinema {
    Long id;
    String name;
    Address address;
    List<CinemaRoom> cinemaRooms;
}
