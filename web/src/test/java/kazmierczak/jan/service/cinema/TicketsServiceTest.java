package kazmierczak.jan.service.cinema;

import kazmierczak.jan.cinema.TicketsService;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.seance.Seance;
import kazmierczak.jan.model.seance.repository.SeanceRepository;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seat.dto.CreateSeatDto;
import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.model.ticket.dto.CreateTicketDto;
import kazmierczak.jan.model.ticket.repository.TicketRepository;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static java.util.List.of;
import static kazmierczak.jan.types.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TicketsServiceTest {

    @TestConfiguration
    public static class TicketsServiceTestConfiguration {
        @MockBean
        public TicketRepository ticketRepository;

        @MockBean
        public SeanceRepository seanceRepository;

        @MockBean
        public CinemaRoomRepository cinemaRoomRepository;

        @MockBean
        public UserRepository userRepository;

        @Bean
        public TicketsService ticketsService() {
            return new TicketsService(ticketRepository, seanceRepository, cinemaRoomRepository, userRepository);
        }
    }

    @Autowired
    private TicketsService ticketsService;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SeanceRepository seanceRepository;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("when findAll method works correctly")
    public void test1() {
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
                .role(USER)
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

        when(ticketRepository.findAll())
                .thenReturn(of(ticket));

        assertThat(ticketsService.findAll())
                .hasSize(1)
                .containsExactly(ticket.toGetTicketDto());
    }

    @Test
    @DisplayName("when findAllByUserId works correctly")
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
                .id(1L)
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
                .role(USER)
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

        userRepository.add(user);

        when(ticketRepository.findTicketsByUserId(1L))
                .thenReturn(of(ticket));

        assertThat(ticketsService.findAllByUserId(1L))
                .hasSize(1)
                .containsExactly(ticket.toGetTicketDto());
    }

    //FIXME: fix
    @Test
    @DisplayName("when createTicket method works correct")
    public void test3() {
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
                .id(1L)
                .name("Name")
                .rows(10)
                .places(11)
                .cinema(cinema)
                .build();

        var seat = Seat
                .builder()
                .id(1L)
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
                .role(USER)
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

        var seatDto = CreateSeatDto
                .builder()
                .cinemaRoomId(1L)
                .row(1)
                .place(1)
                .build();

        var ticketDto = CreateTicketDto
                .builder()
                .userId(1L)
                .seanceId(1L)
                .seat(seatDto)
                .price(2.5)
                .purchaseDate(LocalDate.of(2020, 12, 12))
                .build();


        when(userRepository.add(any(User.class)))
                .thenReturn(Optional.of(user));

        when(ticketRepository.add(any(Ticket.class)))
                .thenReturn(Optional.of(ticket));

        var createdTicket = ticketsService.createTicket(ticketDto);

        assertThat(createdTicket.getId())
                .isEqualTo(1L);
    }
}
