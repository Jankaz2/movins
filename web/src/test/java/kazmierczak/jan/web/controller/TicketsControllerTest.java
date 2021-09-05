package kazmierczak.jan.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kazmierczak.jan.cinema.TicketsService;
import kazmierczak.jan.controller.TicketsController;
import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import kazmierczak.jan.model.movie.Movie;
import kazmierczak.jan.model.seance.dto.GetSeanceDto;
import kazmierczak.jan.model.seat.Seat;
import kazmierczak.jan.model.seat.dto.GetSeatDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketDto;
import kazmierczak.jan.model.ticket.dto.CreateTicketResponseDto;
import kazmierczak.jan.model.ticket.dto.GetTicketDto;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.types.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.time.LocalDate.of;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {TicketsService.class, TicketsController.class})
public class TicketsControllerTest {

    @MockBean
    private TicketsService ticketsService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("when createTicket works correctly")
    public void test1() throws Exception {
        var seat = Seat
                .builder()
                .row(1)
                .place(1)
                .build();

        var ticketDto = CreateTicketDto
                .builder()
                .userId(2L)
                .seanceId(3L)
                .seat(seat.toCreateSeatDto())
                .price(2.5)
                .purchaseDate(of(2022, 12, 12))
                .build();

        var responseDto = CreateTicketResponseDto
                .builder()
                .id(1L)
                .build();

        when(ticketsService.createTicket(ticketDto)).thenReturn(responseDto);

        mockMvc
                .perform(
                        post("/tickets")
                                .content(mapper
                                        .writeValueAsString(ticketDto)
                                        .getBytes(UTF_8))
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.id", equalTo(1)));
    }

    @Test
    @DisplayName("when getAllTickets works correctly")
    public void test2() throws Exception {
        var cinemaRoomDto = GetCinemaRoomDto
                .builder()
                .id(1L)
                .name("Name")
                .rows(10)
                .places(10)
                .build();

        var seatDto = GetSeatDto
                .builder()
                .id(1L)
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoomDto)
                .build();

        var userDto = GetUserDto
                .builder()
                .id(1L)
                .username("Username")
                .email("email@gmail.com")
                .age(12)
                .role(Role.ROLE_USER)
                .build();


        var movie = Movie
                .builder()
                .id(1L)
                .title("Title")
                .genre("Genre")
                .duration(150)
                .releaseDate(of(2020, 2, 12))
                .seances(new ArrayList<>())
                .build();


        var seanceDto = GetSeanceDto
                .builder()
                .id(1L)
                .cinemaRoom(cinemaRoomDto)
                .movie(movie.toGetMovieDtoLight())
                .date(of(2020, 12, 12))
                .build();

        var ticketDto1 = GetTicketDto
                .builder()
                .seance(seanceDto)
                .user(userDto)
                .seat(seatDto)
                .price(20.0)
                .purchaseDate(of(2021, 12, 12))
                .build();

        var ticketDto2 = GetTicketDto
                .builder()
                .seance(seanceDto)
                .user(userDto)
                .seat(seatDto)
                .price(22.0)
                .purchaseDate(of(2021, 12, 12))
                .build();


        var ticketDtos = List.of(ticketDto1, ticketDto2);

        when(ticketsService.findAll()).thenReturn(ticketDtos);

        mockMvc
                .perform(
                        get("/tickets")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data[0].price", equalTo(20.0)))
                .andExpect(jsonPath("$.data[1].price", equalTo(22.0)));
    }

    @Test
    @DisplayName("when getAllTicketsByUserId works correctly")
    public void test3() throws Exception {
        var cinemaRoomDto = GetCinemaRoomDto
                .builder()
                .id(1L)
                .name("Name")
                .rows(10)
                .places(10)
                .build();

        var seatDto = GetSeatDto
                .builder()
                .id(1L)
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoomDto)
                .build();

        var userDto = GetUserDto
                .builder()
                .id(1L)
                .username("Username")
                .email("email@gmail.com")
                .age(12)
                .role(Role.ROLE_USER)
                .build();

        var movie = Movie
                .builder()
                .id(1L)
                .title("Title")
                .genre("Genre")
                .duration(150)
                .releaseDate(of(2020, 2, 12))
                .seances(new ArrayList<>())
                .build();


        var seanceDto = GetSeanceDto
                .builder()
                .id(1L)
                .cinemaRoom(cinemaRoomDto)
                .movie(movie.toGetMovieDtoLight())
                .date(of(2020, 12, 12))
                .build();

        var ticketDto1 = GetTicketDto
                .builder()
                .seance(seanceDto)
                .user(userDto)
                .seat(seatDto)
                .price(20.0)
                .purchaseDate(of(2021, 12, 12))
                .build();

        var ticketDto2 = GetTicketDto
                .builder()
                .seance(seanceDto)
                .user(userDto)
                .seat(seatDto)
                .price(22.0)
                .purchaseDate(of(2021, 12, 12))
                .build();


        var ticketDtos = List.of(ticketDto1, ticketDto2);

        when(ticketsService.findAllByUserId(1L)).thenReturn(ticketDtos);

        mockMvc
                .perform(
                        get("/tickets/1")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data[0].price", equalTo(20.0)))
                .andExpect(jsonPath("$.data[1].price", equalTo(22.0)));
    }
}
