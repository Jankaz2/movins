package model.cinema.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.address.Address;
import model.address.dto.CreateAddressDto;
import model.cinema.Cinema;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCinemaDto {
    private String name;
    private CreateAddressDto address;

    /**
     *
     * @return Cinema object mapped from CinemaDto
     */
    public Cinema toCinema() {
        return Cinema
                .builder()
                .name(name)
                .address(address.toAddress())
                .build();
    }
}
