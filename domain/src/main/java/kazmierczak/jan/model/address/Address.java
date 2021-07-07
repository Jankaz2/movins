package kazmierczak.jan.model.address;

import lombok.*;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import kazmierczak.jan.model.cinema.Cinema;

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

    /**
     *
     * @return AddressDto object mapped from Address
     */
    public CreateAddressDto toCreateAddressDto() {
        return CreateAddressDto
                .builder()
                .city(city)
                .street(street)
                .number(number)
                .build();
    }
}
