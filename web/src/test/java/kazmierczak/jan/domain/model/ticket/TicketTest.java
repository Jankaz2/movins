package kazmierczak.jan.domain.model.ticket;

import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.model.ticket.dto.CreateTicketResponseDto;
import kazmierczak.jan.model.ticket.dto.GetTicketDto;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.types.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(SpringExtension.class)
public class TicketTest {
    @Test
    @DisplayName("when toCreateTicketResponseDto object works correct")
    public void test1() {
        var ticket = Ticket
                .builder()
                .id(1L)
                .seat(null)
                .seance(null)
                .user(null)
                .price(2.5)
                .purchaseDate(LocalDate.of(2020, 12, 12))
                .build();

        var ticketResponseDto = CreateTicketResponseDto
                .builder()
                .id(1L)
                .build();

        assertThat(ticket.toCreateTicketResponseDto())
                .isEqualTo(ticketResponseDto);
    }

    @Test
    @DisplayName("when toGetTicketDto object works correct")
    public void test2() {
        var address = Address
                .builder()
                .id(1L)
                .city("City")
                .street("Street")
                .number(10)
                .cinemas(new ArrayList<>())
                .build();

        var cinema = Cinema
                .builder()
                .id(1L)
                .name("Name")
                .address(address)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaRoom = CinemaRoom
                .builder()
                .name("Name")
                .rows(10)
                .places(11)
                .cinema(cinema)
                .build();

        var seat = Seat
                .builder()
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoom)
                .build();

        var movie = Movie
                .builder()
                .id(1L)
                .title("Title")
                .genre("Genre")
                .duration(150)
                .releaseDate(LocalDate.of(2020, 2, 12))
                .seances(new ArrayList<>())
                .build();

        var seance = Seance
                .builder()
                .id(1L)
                .movie(movie)
                .cinemaRoom(cinemaRoom)
                .date(LocalDate.of(2020, 12, 12))
                .tickets(new ArrayList<>())
                .build();

        var user = User
                .builder()
                .id(1L)
                .username("Username")
                .email("email@wp.pl")
                .age(12)
                .enabled(true)
                .role(Role.USER)
                .tickets(new ArrayList<>())
                .build();

        var ticket = Ticket
                .builder()
                .id(1L)
                .seat(seat)
                .seance(seance)
                .user(user)
                .price(2.5)
                .purchaseDate(LocalDate.of(2020, 12, 12))
                .build();

        var getTicketDto = GetTicketDto
                .builder()
                .id(1L)
                .seat(seat.toGetSeatDto())
                .seance(seance.toGetSeanceDto())
                .user(user.toGetUserDto())
                .price(2.5)
                .purchaseDate(LocalDate.of(2020, 12, 12))
                .build();

        assertThat(ticket.toGetTicketDto())
                .isEqualTo(getTicketDto);
    }
}
