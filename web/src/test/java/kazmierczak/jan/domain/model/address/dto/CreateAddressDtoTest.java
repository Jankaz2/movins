package kazmierczak.jan.domain.model.address.dto;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CreateAddressDtoTest {

    @Test
    @DisplayName("when method works correct")
    public void test1 () {
        var createAddressDto = CreateAddressDto
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();

        var address = Address
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();
        assertThat(address).isEqualTo(createAddressDto.toAddress());
    }
}
