package model.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.address.Address;
import model.cinema.Cinema;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateAddressDto {
    private String city;
    private String street;
    private Integer number;

    /**
     *
     * @return Address object mapped from AddressDto
     */
    public Address toAddress() {
        return Address
                .builder()
                .city(city)
                .street(street)
                .number(number)
                .build();
    }
}
