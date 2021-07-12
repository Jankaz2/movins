package kazmierczak.jan.cinema.service;

import kazmierczak.jan.cinema.CinemaService;
import kazmierczak.jan.model.address.Address;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import kazmierczak.jan.model.cinema.Cinema;
import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema.repository.CinemaRepository;
import kazmierczak.jan.model.cinema_room.repository.CinemaRoomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class CinemaServiceTest {

    @TestConfiguration
    public static class TeamsServiceTestConfiguration {

        @MockBean
        public CinemaRepository cinemaRepository;

        @MockBean
        public CinemaRoomRepository cinemaRoomRepository;

        @Bean
        public CinemaService cinemaService() {
            return new CinemaService(cinemaRepository, cinemaRoomRepository);
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
        Mockito
                .when(cinemaRepository.findAll())
                .thenReturn(List.of(Cinema
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

        Assertions
                .assertThat(cinemaService.findAll())
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
        Mockito
                .when(cinemaRepository.findById(1L))
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

        Assertions
                .assertThat(cinemaService.findById(1L))
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
        Mockito
                .when(cinemaRepository.delete(1L))
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

        Assertions
                .assertThat(cinemaService.deleteById(1L))
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
    @DisplayName("testing createCinema method")
    public void test4() {
        var createCinemaDto = CreateCinemaDto
                .builder()
                .name("Cinema")
                .address(CreateAddressDto
                        .builder()
                        .city("City")
                        .street("Street")
                        .number(1)
                        .build())
                .cinemaRooms(new ArrayList<>())
                .build();

        Mockito
                .when(cinemaRepository.delete(1L))
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

        Assertions
                .assertThat(cinemaService.deleteById(1L))
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
}
