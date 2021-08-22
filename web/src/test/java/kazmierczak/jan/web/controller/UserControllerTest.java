package kazmierczak.jan.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kazmierczak.jan.controller.UserController;
import kazmierczak.jan.model.ticket.Ticket;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import kazmierczak.jan.model.user.dto.CreateUserResponseDto;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.user.UserService;
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

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.List.of;
import static kazmierczak.jan.types.Role.USER;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = {UserService.class, UserController.class})
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("when getAllUsers method works correctly")
    public void test1() throws Exception {
        var userDto = GetUserDto
                .builder()
                .id(1L)
                .username("Username")
                .email("email@gmail.com")
                .age(12)
                .role(USER)
                .build();

        var userDto2 = GetUserDto
                .builder()
                .id(1L)
                .username("Usernamee")
                .email("email@gmail.com")
                .age(12)
                .role(USER)
                .build();

        var users = of(userDto, userDto2);

        when(userService.findAll()).thenReturn(users);

        mockMvc
                .perform(
                        get("/users")
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data[0].username", equalTo("Username")));
    }

    @Test
    @DisplayName("when method createUser works correctly")
    public void test2() throws Exception {
        var userDto = CreateUserDto
                .builder()
                .username("Username")
                .email("email@gmail.com")
                .age(12)
                .password("password")
                .role(USER)
                .build();

        var responseDto = CreateUserResponseDto
                .builder()
                .id(1L)
                .build();

        when(userService.createUser(userDto)).thenReturn(responseDto);

        mockMvc
                .perform(
                        post("/users/register")
                                .content(toJson(userDto))
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().is(201))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.id", equalTo(1)));
    }

    @Test
    @DisplayName("when getUserById method works correctly")
    public void test3() throws Exception {
        var userDto = GetUserDto
                .builder()
                .id(1L)
                .username("Username")
                .email("email@gmail.com")
                .age(12)
                .role(USER)
                .build();

        var userId = 1L;

        when(userService.findById(userId)).thenReturn(userDto);

        mockMvc
                .perform(
                        get("/users/{id}", userId)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.username", equalTo("Username")))
                .andExpect(jsonPath("$.data.id", equalTo(1)));
    }

    @Test
    @DisplayName("when deleteUserById method works correctly")
    public void test4() throws Exception {
        var userDto = GetUserDto
                .builder()
                .id(1L)
                .username("Username")
                .email("email@gmail.com")
                .age(12)
                .role(USER)
                .build();

        var userId = 1L;

        when(userService.deleteById(userId)).thenReturn(userDto);

        mockMvc
                .perform(
                        delete("/users/admin/{id}", userId)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.id", equalTo(1)));
    }

    @Test
    @DisplayName("when purchaesedTickets method works correctly")
    public void test5() throws Exception {
        var ticket1 = Ticket
                .builder()
                .id(2L)
                .seat(null)
                .seance(null)
                .user(null)
                .price(2.5)
                .purchaseDate(LocalDate.of(2020, 12, 12))
                .build();

        var ticket2 = Ticket
                .builder()
                .id(3L)
                .seat(null)
                .seance(null)
                .user(null)
                .price(2.5)
                .purchaseDate(LocalDate.of(2021, 12, 12))
                .build();

        var tickets = of(ticket1, ticket2);

        var user = User
                .builder()
                .id(1L)
                .username("Username")
                .email("email@wp.pl")
                .age(12)
                .role(USER)
                .password("passwordd")
                .tickets(tickets)
                .build();

        var userId = 1L;

        when(userService.countPurchasedTickets(userId)).thenReturn(2);

        mockMvc
                .perform(
                        get("/users/purchase/{id}", userId)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data", equalTo(2)));
    }

    @Test
    @DisplayName("when findByUsername works correctly")
    public void test6() throws Exception {
        var user = User
                .builder()
                .id(1L)
                .username("Username")
                .email("email@wp.pl")
                .age(12)
                .role(USER)
                .password("passwordd")
                .tickets(new ArrayList<>())
                .build();

        var userDto = user.toGetUserDto();
        var username = "Username";

        when(userService.findByUsername(username)).thenReturn(userDto);

        mockMvc
                .perform(
                        get("/users/username/{username}", username)
                                .contentType(APPLICATION_JSON)
                )
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.data.username", equalTo("Username")))
                .andExpect(jsonPath("$.data.id", equalTo(1)));
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
