package kazmierczak.jan.web.persistence.entity;

import kazmierczak.jan.persistence.entity.AddressEntity;
import model.address.Address;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class AddressEntityTest {

    @Test
    @DisplayName("when mapping method works correctly")
    public void test1() {
        var address = Address
                .builder()
                .id(1L)
                .cinemas(new ArrayList<>())
                .city("city")
                .number(1)
                .build();

        var entityAddress = AddressEntity
                .builder()
                .id(1L)
                .cinemas(new ArrayList<>())
                .city("city")
                .number(1)
                .build();

        assertThat(entityAddress.toAddress())
                .isEqualTo(address);
    }
}
