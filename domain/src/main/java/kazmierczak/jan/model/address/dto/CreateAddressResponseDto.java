package kazmierczak.jan.model.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressResponseDto {
    private Long id;
    private String city;
    private String street;
    private Integer number;
}
