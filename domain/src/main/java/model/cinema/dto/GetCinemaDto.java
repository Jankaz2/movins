package model.cinema.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.address.dto.CreateAddressDto;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCinemaDto {
    private Long id;
    private String name;
    private CreateAddressDto address;
}
