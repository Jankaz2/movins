package kazmierczak.jan.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kazmierczak.jan.cinema.CinemaRoomService;
import kazmierczak.jan.cinema.CinemaService;
import kazmierczak.jan.controller.CinemaController;
import kazmierczak.jan.model.address.dto.CreateAddressDto;
import kazmierczak.jan.model.cinema.dto.CreateCinemaDto;
import kazmierczak.jan.model.cinema.dto.CreateCinemaResponseDto;
import kazmierczak.jan.model.cinema.dto.GetCinemaDto;
import kazmierczak.jan.model.cinema_room.dto.CreateCinemaRoomDto;
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

import static java.util.List.of;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CinemaController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {CinemaService.class, CinemaController.class})
public class CinemaControllerTest {
    @MockBean
    private CinemaService cinemaService;

    @MockBean
    private CinemaRoomService cinemaRoomService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when getAllCinemas method works correctly")
    public void test1() throws Exception {
        var addressDto = CreateAddressDto
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();

        var cinemaDto1 = GetCinemaDto
                .builder()
                .name("Cinema")
                .address(addressDto)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaDto2 = GetCinemaDto
                .builder()
                .name("Cinemaa")
                .address(addressDto)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemas = of(cinemaDto1, cinemaDto2);

        when(cinemaService.findAll()).thenReturn(cinemas);

        mockMvc
                .perform(
                        get("/cinema")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data[0].name", equalTo("Cinema")));
    }

    @Test
    @DisplayName("when createCinema method works correctly")
    public void test2() throws Exception {
        var addressDto = CreateAddressDto
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();

        var cinemaDto = CreateCinemaDto
                .builder()
                .name("Cinema")
                .address(addressDto)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaResponse = CreateCinemaResponseDto
                .builder()
                .cinemaId(1L)
                .build();

        when(cinemaService.createCinema(cinemaDto)).thenReturn(cinemaResponse);

        mockMvc
                .perform(
                        post("/cinema/admin")
                                .content(toJson(cinemaDto))
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.cinemaId", equalTo(1)));
    }

    @Test
    @DisplayName("when getCinemaById method works correctly")
    public void test3() throws Exception {
        var addressDto = CreateAddressDto
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();

        var cinemaDto = GetCinemaDto
                .builder()
                .id(1L)
                .name("Cinema")
                .address(addressDto)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaId = 1L;

        when(cinemaService.findById(cinemaId)).thenReturn(cinemaDto);

        mockMvc
                .perform(
                        get("/cinema/{id}", cinemaId)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.name", equalTo("Cinema")))
                .andExpect(jsonPath("$.data.id", equalTo(1)));
    }

    @Test
    @DisplayName("when deleteCinema method works correctly")
    public void test4() throws Exception {
        var addressDto = CreateAddressDto
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();

        var cinemaDto = GetCinemaDto
                .builder()
                .id(1L)
                .name("Cinema")
                .address(addressDto)
                .cinemaRooms(new ArrayList<>())
                .build();

        var cinemaId = 1L;

        when(cinemaService.deleteById(cinemaId)).thenReturn(cinemaDto);

        mockMvc
                .perform(
                        delete("/cinema/admin/{id}", cinemaId)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.name", equalTo("Cinema")))
                .andExpect(jsonPath("$.data.id", equalTo(1)));
    }

    @Test
    @DisplayName("when addCinemaRoomToCinema method works correctly")
    public void test5() throws Exception {
        var addressDto = CreateAddressDto
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();

        var cinemaRoomDto = CreateCinemaRoomDto
                .builder()
                .name("Cinemaroom")
                .rows(10)
                .places(10)
                .build();

        var cinemaRoomDtos = of(cinemaRoomDto);
        var cinemaResponseDto = CreateCinemaResponseDto
                .builder()
                .cinemaId(1L)
                .build();
        var name = "Cinema";

        when(cinemaService.addCinemaRoomsToExistedCinema(name, cinemaRoomDtos))
                .thenReturn(cinemaResponseDto);

        mockMvc
                .perform(
                        patch("/cinema/admin/{name}", name)
                                .content(toJson(cinemaRoomDtos))
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.cinemaId", equalTo(1)));
    }

    @Test
    @DisplayName("when updateCinema method workss correctly")
    public void test6() throws Exception {
        var addressDto = CreateAddressDto
                .builder()
                .city("City")
                .street("Street")
                .number(12)
                .build();

        var cinemaDto = CreateCinemaDto
                .builder()
                .name("Cinema")
                .address(addressDto)
                .cinemaRooms(new ArrayList<>())
                .build();

        var oldName = "Cinema";
        var cinemaResponseDto = CreateCinemaResponseDto
                .builder()
                .cinemaId(1L)
                .build();

        when(cinemaService.updateCinema(oldName, cinemaDto)).thenReturn(cinemaResponseDto);

        mockMvc
                .perform(
                        put("/cinema/admin/{oldName}", oldName)
                                .content(toJson(cinemaDto))
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.cinemaId", equalTo(1)));
    }

    /**
     * @param t   object we want to parse
     * @param <T>
     * @return parsed object
     */
    private static <T> String toJson(T t) {
        try {
            return new ObjectMapper().writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}