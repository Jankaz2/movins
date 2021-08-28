package kazmierczak.jan.service;

import kazmierczak.jan.cinema.SeatsService;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seat.repository.SeatRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SeatsServiceTest {

    @TestConfiguration
    public static class SeatsServiceTestConfiguration {
        @MockBean
        public SeatRepository seatRepository;

        @Bean
        public SeatsService seatsService() {
            return new SeatsService(seatRepository);
        }
    }

    @Autowired
    private SeatsService seatsService;

    @Autowired
    private SeatRepository seatRepository;

    @Test
    @DisplayName("when method findAll works correctly")
    public void test1() {
        var address = Address
                .builder()
                .id(1L)
                .city("City")
                .street("Street")
                .number(1)
                .build();

        var cinema = Cinema
                .builder()
                .id(1L)
                .name("Cinema")
                .address(address)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaRoom = CinemaRoom
                .builder()
                .id(1L)
                .name("Name")
                .cinema(cinema)
                .rows(10)
                .places(10)
                .seances(new ArrayList<>())
                .seats(new ArrayList<>())
                .build();

        var seat = Seat
                .builder()
                .id(1L)
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoom)
                .tickets(new ArrayList<>())
                .build();

        when(seatRepository.findAll())
                .thenReturn(of(seat));

        assertThat(seatsService.findAll())
                .hasSize(1)
                .containsExactly(seat.toGetSeatDto());
    }
}
