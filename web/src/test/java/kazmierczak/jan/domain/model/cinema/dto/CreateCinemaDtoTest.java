package kazmierczak.jan.domain.model.cinema.dto;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class CreateCinemaDtoTest {

    @Test
    @DisplayName("when method toCinema works correct")
    public void test1() {
        var address = Address
                .builder()
                .city("City")
                .street("Street")
                .number(10)
                .build();

        var cinema = Cinema
                .builder()
                .name("Name")
                .address(address)
                .cinemaRooms(null)
                .build();

        var createCinemaDto = CreateCinemaDto
                .builder()
                .name("Name")
                .address(address.toCreateAddressDto())
                .cinemaRooms(null)
                .build();

        assertThat(createCinemaDto.toCinema())
                .isEqualTo(cinema);
    }
}
