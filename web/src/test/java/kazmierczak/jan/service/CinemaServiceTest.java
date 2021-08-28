package kazmierczak.jan.service;

import kazmierczak.jan.cinema.CinemaService;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema.repository.CinemaRepository;
import kazmierczak.jan.model.cinema_room.CinemaRoom;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import kazmierczak.jan.model.seance.repository.SeanceRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CinemaServiceTest {

    @TestConfiguration
    public static class CinemaServiceTestConfiguration {

        @MockBean
        public CinemaRepository cinemaRepository;

        @MockBean
        public CinemaRoomRepository cinemaRoomRepository;

        @MockBean
        public SeanceRepository seanceRepository;

        @Bean
        public CinemaService cinemaService() {
            return new CinemaService(cinemaRepository, cinemaRoomRepository, seanceRepository);
        }
    }

    @Autowired
    private CinemaService cinemaService;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private CinemaRoomRepository cinemaRoomRepository;

    @Test
    @DisplayName("testing findAll method")
    public void test1() {
        when(cinemaRepository.findAll())
                .thenReturn(of(Cinema
                        .builder()
                        .id(1L)
                        .name("Cinema")
                        .address(Address
                                .builder()
                                .id(1L)
                                .cinemas(new ArrayList<>())
                                .city("City")
                                .street("Street")
                                .number(1)
                                .build())
                        .cinemaRooms(new ArrayList<>())
                        .build()));

        assertThat(cinemaService.findAll())
                .hasSize(1)
                .containsExactly(
                        GetCinemaDto.builder()
                                .id(1L)
                                .name("Cinema")
                                .address(CreateAddressDto
                                        .builder()
                                        .city("City")
                                        .street("Street")
                                        .number(1)
                                        .build())
                                .cinemaRooms(new ArrayList<>())
                                .build()
                );
    }

    @Test
    @DisplayName("testing findById method")
    public void test2() {
        when(cinemaRepository.findById(1L))
                .thenReturn(Optional.of(Cinema
                        .builder()
                        .id(1L)
                        .name("Cinema")
                        .address(Address
                                .builder()
                                .id(1L)
                                .cinemas(new ArrayList<>())
                                .city("City")
                                .street("Street")
                                .number(1)
                                .build())
                        .cinemaRooms(new ArrayList<>())
                        .build()));

        assertThat(cinemaService.findById(1L))
                .isEqualTo(GetCinemaDto
                        .builder()
                        .id(1L)
                        .name("Cinema")
                        .address(CreateAddressDto
                                .builder()
                                .city("City")
                                .street("Street")
                                .number(1)
                                .build())
                        .cinemaRooms(new ArrayList<>())
                        .build()
                );
    }

    @Test
    @DisplayName("testing deleteById method")
    public void test3() {
        var cinema = Cinema
                .builder()
                .id(1L)
                .name("Cinema")
                .address(Address
                        .builder()
                        .id(1L)
                        .cinemas(new ArrayList<>())
                        .city("City")
                        .street("Street")
                        .number(1)
                        .build())
                .cinemaRooms(new ArrayList<>())
                .build();

        when(cinemaRepository.findById(1L))
                .thenReturn(Optional.of(cinema));

        when(cinemaRepository.delete(1L))
                .thenReturn(Optional.of(cinema));

        assertThat(cinemaService.deleteById(1L))
                .isEqualTo(cinema.toGetCinemaDto());
    }

    @Test
    @DisplayName("when createCinema method works correctly")
    public void test4() {
        var address = Address
                .builder()
                .id(1L)
                .city("City")
                .street("Street")
                .number(1)
                .build();

        var cinemaRoom = CinemaRoom
                .builder()
                .id(1L)
                .name("Name")
                .cinema(null)
                .rows(10)
                .places(10)
                .seances(new ArrayList<>())
                .seats(new ArrayList<>())
                .build();

        var cinema = Cinema
                .builder()
                .id(1L)
                .name("Cinema")
                .address(address)
                .cinemaRooms(of(cinemaRoom))
                .build();

        cinemaRoom.setCinema(cinema);
        cinema.setCinemaRooms(of(cinemaRoom));
        var cinemaRoomDto = cinemaRoom.toCreateCinemaRoomDto();

        var cinemaDto = CreateCinemaDto
                .builder()
                .name("Cinema")
                .address(address.toCreateAddressDto())
                .cinemaRooms(of(cinemaRoomDto))
                .build();

        when(cinemaRepository.add(any(Cinema.class)))
                .thenReturn(Optional.of(cinema));

        var createdCinema = cinemaService.createCinema(cinemaDto);

        assertThat(createdCinema.getCinemaId())
                .isEqualTo(1L);
    }
}
