package kazmierczak.jan.web.persistence.entity;

import kazmierczak.jan.persistence.entity.*;
import model.ticket.Ticket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class TicketEntityTest {

    @Test
    @DisplayName("when mapping method works correctly")
    public void test1() {
        var addressEntity = AddressEntity
                .builder()
                .id(1L)
                .street("street")
                .city("city")
                .number(2)
                .cinemas(new ArrayList<>())
                .build();

        var cinemaEntity = CinemaEntity
                .builder()
                .id(1L)
                .name("name")
                .address(addressEntity)
                .build();

        var cinemaRoomEntity = CinemaRoomEntity
                .builder()
                .id(1L)
                .name("name")
                .rows(1)
                .places(1)
                .cinema(cinemaEntity)
                .build();


        var seatEntity =  SeatEntity
                .builder()
                .id(1L)
                .cinemaRoom(cinemaRoomEntity)
                .place(12)
                .row(1)
                .tickets(new ArrayList<>())
                .build();

        var movieEntity = MovieEntity.builder()
                .id(1L)
                .seances(new ArrayList<>())
                .title("title")
                .genre("genre")
                .duration(10)
                .releaseDate(LocalDate.of(2020, 12, 12))
                .build();

        var seanceEntity = SeanceEntity
                .builder()
                .id(1L)
                .movie(movieEntity)
                .tickets(new ArrayList<>())
                .cinemaRoom(cinemaRoomEntity)
                .date(LocalDate.of(2020, 12, 12))
                .build();

        var userEntity = UserEntity
                .builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .age(12)
                .password("password")
                .email("email")
                .tickets(new ArrayList<>())
                .build();

        var ticket = Ticket
                .builder()
                .id(1L)
                .seat(seatEntity.toSeat())
                .user(userEntity.toUser())
                .seance(seanceEntity.toSeance())
                .price(2.0)
                .build();

        var ticketEntity = TicketEntity
                .builder()
                .id(1L)
                .seat(seatEntity)
                .user(userEntity)
                .seance(seanceEntity)
                .price(2.0)
                .build();

        assertThat(ticketEntity.toTicket())
                .isEqualTo(ticket);
    }
}
