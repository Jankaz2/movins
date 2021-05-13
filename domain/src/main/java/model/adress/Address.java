package model.adress;

import lombok.*;
import model.cinemas.Cinema;

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
