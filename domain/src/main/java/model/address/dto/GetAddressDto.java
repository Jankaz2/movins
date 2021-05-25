package model.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.cinema.Cinema;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAddressDto {
    private Long id;
    private String city;
    private String street;
    private Integer number;
    private List<Cinema> cinemas;
}
