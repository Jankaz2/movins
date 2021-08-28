package kazmierczak.jan.service;

import kazmierczak.jan.cinema.CinemaRoomService;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CinemaRoomServiceTest {

    @TestConfiguration
    public static class CinemaRoomServiceTestConfiguration {

        @MockBean
        public CinemaRoomRepository cinemaRoomRepository;

        @Bean
        public CinemaRoomService cinemaRoomService() {
            return new CinemaRoomService(cinemaRoomRepository);
        }
    }

    @Autowired
    private CinemaRoomService cinemaRoomService;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Test
    @DisplayName("when findById method works correctly")
    public void test1() {
        var cinemaRoom = CinemaRoom
                .builder()
                .id(1L)
                .name("Name")
                .rows(10)
                .places(10)
                .cinema(null)
                .seances(new ArrayList<>())
                .seats(new ArrayList<>())
                .build();

        var getCinemaRoomDto = cinemaRoom.toGetCinemaRoomDto();

        when(cinemaRoomRepository.findById(1L))
                .thenReturn(Optional.ofNullable(cinemaRoom));

        assertThat(cinemaRoomService.findById(1L))
                .isEqualTo(getCinemaRoomDto);
    }

    @Test
    @DisplayName("when findAll method works correctly")
    public void test2() {
        var cinemaRoom = CinemaRoom
                .builder()
                .id(1L)
                .name("Name")
                .rows(10)
                .places(10)
                .cinema(null)
                .seances(new ArrayList<>())
                .seats(new ArrayList<>())
                .build();

        var getCinemaRoomDto = cinemaRoom.toGetCinemaRoomDto();

        when(cinemaRoomRepository.findAll())
                .thenReturn(of(cinemaRoom));

        assertThat(cinemaRoomService.findAll())
                .hasSize(1)
                .containsExactly(getCinemaRoomDto);
    }

    @Test
    @DisplayName("when findByCinemaId method works correctly")
    public void test3() {
        var cinema = Cinema
                .builder()
                .id(1L)
                .name("Name")
                .address(null)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaRoom = CinemaRoom
                .builder()
                .id(1L)
                .name("Name")
                .rows(10)
                .places(10)
                .cinema(cinema)
                .seances(new ArrayList<>())
                .seats(new ArrayList<>())
                .build();

        var getCinemaRoomDto = cinemaRoom.toGetCinemaRoomDto();

        when(cinemaRoomRepository.findCinemaRoomsByCinemaId(1L))
                .thenReturn(of(cinemaRoom));

        assertThat(cinemaRoomService.findByCinemaId(1L))
                .isEqualTo(of(getCinemaRoomDto));
    }
}
