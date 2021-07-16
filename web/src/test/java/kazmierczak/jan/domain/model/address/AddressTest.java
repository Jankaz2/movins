package kazmierczak.jan.domain.model.address;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class AddressTest {

    @Test
    @DisplayName("test for method toCreateAddressDto")
    public void test1() {
        var address = Address
                .builder()
                .id(1L)
                .city("City")
                .street("Street")
                .number(12)
                .cinemas(new ArrayList<>())
                .build();

        var addressDto = CreateAddressDto
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();

        assertThat(address.toCreateAddressDto())
                .isEqualTo(addressDto);
    }

    @Test
    @DisplayName("test for withChangeData method")
    public void test2() {
        var address = Address
                .builder()
                .id(1L)
                .city("City")
                .street("Street")
                .number(12)
                .cinemas(new ArrayList<>())
                .build();

        var addressDto = CreateAddressDto
                .builder()
                .city("Citydto")
                .street("Streetdto")
                .number(13)
                .build();

        var addressAfterChange = Address
                .builder()
                .id(1L)
                .city("Citydto")
                .street("Streetdto")
                .number(13)
                .cinemas(new ArrayList<>())
                .build();

        assertThat(address.withChangedData(addressDto))
                .isEqualTo(addressAfterChange);
    }
}
