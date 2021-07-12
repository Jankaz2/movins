package kazmierczak.jan.cinema.service;

import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.model.user.dto.UserToActivateDto;
import kazmierczak.jan.model.user.repository.UserRepository;
import kazmierczak.jan.types.Role;
import kazmierczak.jan.user.EventPublisher;
import kazmierczak.jan.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @TestConfiguration
    public static class UserServiceTestConfiguration {
        @MockBean
        public UserRepository userRepository;

        @MockBean
        public PasswordEncoder passwordEncoder;

        @MockBean
        public EventPublisher<UserToActivateDto> eventPublisher;

        @Bean
        public UserService userService() {
            return new UserService(userRepository, passwordEncoder, eventPublisher);
        }
    }

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EventPublisher<UserToActivateDto> eventPublisher;

    @Test
    @DisplayName("testing findAll method")
    public void test1() {
        Mockito
                .when(userRepository.findAll())
                .thenReturn(List.of(
                        User
                                .builder()
                                .id(1L)
                                .username("Username")
                                .email("email@gmail.com")
                                .age(18)
                                .password("password")
                                .enabled(true)
                                .role(Role.USER)
                                .tickets(new ArrayList<>())
                                .build()
                ));

        Assertions
                .assertThat(userService.findAll())
                .hasSize(1)
                .containsExactly(
                        GetUserDto
                                .builder()
                                .id(1L)
                                .username("Username")
                                .email("email@gmail.com")
                                .age(18)
                                .password("password")
                                .role(Role.USER)
                                .build()
                );
    }

    @Test
    @DisplayName("testing findById method")
    public void test2() {
        Mockito
                .when(userRepository.findById(1L))
                .thenReturn(Optional.of(
                        User
                                .builder()
                                .id(1L)
                                .username("Username")
                                .email("email@gmail.com")
                                .age(18)
                                .password("password")
                                .enabled(true)
                                .role(Role.USER)
                                .tickets(new ArrayList<>())
                                .build()
                ));

        Assertions
                .assertThat(userService.findById(1L))
                .isEqualTo(
                        GetUserDto
                                .builder()
                                .id(1L)
                                .username("Username")
                                .email("email@gmail.com")
                                .age(18)
                                .password("password")
                                .role(Role.USER)
                                .build()
                );
    }

    @Test
    @DisplayName("testing deleteById method")
    public void test3() {
        Mockito
                .when(userRepository.delete(1L))
                .thenReturn(Optional.of(
                        User
                                .builder()
                                .id(1L)
                                .username("Username")
                                .email("email@gmail.com")
                                .age(18)
                                .password("password")
                                .enabled(true)
                                .role(Role.USER)
                                .tickets(new ArrayList<>())
                                .build()
                ));

        Assertions
                .assertThat(userService.deleteById(1L))
                .isEqualTo(GetUserDto
                                .builder()
                                .id(1L)
                                .username("Username")
                                .email("email@gmail.com")
                                .age(18)
                                .password("password")
                                .role(Role.USER)
                                .build()
                );
    }
}
