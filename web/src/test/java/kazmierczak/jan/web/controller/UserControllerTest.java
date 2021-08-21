package kazmierczak.jan.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kazmierczak.jan.controller.UserController;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import kazmierczak.jan.model.user.dto.CreateUserResponseDto;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.types.Role;
import kazmierczak.jan.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
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
                .role(Role.USER)
                .build();

        var userDto2 = GetUserDto
                .builder()
                .id(1L)
                .username("Usernamee")
                .email("email@gmail.com")
                .age(12)
                .role(Role.USER)
                .build();

        var users = List.of(userDto, userDto2);

        when(userService.findAll()).thenReturn(users);

        mockMvc
                .perform(
                        get("/users")
                                .contentType(MediaType.APPLICATION_JSON)
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
                .role(Role.USER)
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
                                .contentType(MediaType.APPLICATION_JSON)
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
                .role(Role.USER)
                .build();

        var userId = 1L;

        when(userService.findById(userId)).thenReturn(userDto);

        mockMvc
                .perform(
                        get("/users/{id}", userId)
                                .contentType(MediaType.APPLICATION_JSON)
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
                .role(Role.USER)
                .build();

        var userId = 1L;

        when(userService.deleteById(userId)).thenReturn(userDto);

        mockMvc
                .perform(
                        delete("/users/admin/{id}", userId)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
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
