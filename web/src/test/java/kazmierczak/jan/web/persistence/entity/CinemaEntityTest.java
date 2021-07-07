package kazmierczak.jan.web.persistence.entity;

import kazmierczak.jan.persistence.entity.AddressEntity;
import kazmierczak.jan.persistence.entity.CinemaEntity;
import kazmierczak.jan.model.cinema.Cinema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CinemaEntityTest {

    @Test
    @DisplayName("when method works correctly")
    public void test1() {
        var address = AddressEntity
                .builder()
                .id(1L)
                .city("city")
                .street("street")
                .number(12)
                .build();

        var cinema = Cinema
                .builder()
                .id(1L)
                .name("name")
                .address(address.toAddress())
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaEntity = CinemaEntity
                .builder()
                .id(1L)
                .name("name")
                .address(address)
                .cinemaRooms(new ArrayList<>())
                .build();

        assertThat(cinemaEntity.toCinema())
                .isEqualTo(cinema);
    }
}
