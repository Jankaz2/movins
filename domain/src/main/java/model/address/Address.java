package model.address;

import lombok.*;
import model.cinema.Cinema;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class Address {
    Long id;
    String city;
    String street;
    Integer number;
    List<Cinema> cinemas;
}
