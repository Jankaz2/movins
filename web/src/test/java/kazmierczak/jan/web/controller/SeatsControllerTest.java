package kazmierczak.jan.web.controller;

import kazmierczak.jan.cinema.SeatsService;
import kazmierczak.jan.controller.SeatsController;
import kazmierczak.jan.model.cinema_room.dto.GetCinemaRoomDto;
import kazmierczak.jan.model.seat.dto.GetSeatDto;
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

import static java.util.List.of;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(SeatsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {SeatsService.class, SeatsController.class})
public class SeatsControllerTest {
    @MockBean
    private SeatsService seatsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when getAllSeats method works correct")
    public void test1() throws Exception {
        var cinemaRoomDto = GetCinemaRoomDto
                .builder()
                .id(1L)
                .name("Name")
                .rows(10)
                .places(10)
                .build();

        var seatDto1 = GetSeatDto
                .builder()
                .id(1L)
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoomDto)
                .build();

        var seatDto2 = GetSeatDto
                .builder()
                .id(2L)
                .row(1)
                .place(1)
                .cinemaRoom(cinemaRoomDto)
                .build();

        var seatsDto = of(seatDto1, seatDto2);

        when(seatsService.findAll()).thenReturn(seatsDto);

        mockMvc
                .perform(
                        get("/cinema/seats")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data[0].id", equalTo(1)))
                .andExpect(jsonPath("$.data[1].id", equalTo(2)));
    }
}
