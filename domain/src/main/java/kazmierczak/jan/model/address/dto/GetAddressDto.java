package kazmierczak.jan.model.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAddressDto {
    private Long id;
    private String city;
    private String street;
    private Integer number;
}
