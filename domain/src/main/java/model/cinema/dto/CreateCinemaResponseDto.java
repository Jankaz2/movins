package model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.address.Address;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateCinemaResponseDto {
    private Long id;
    private String name;
    private Address address;
}
