package kazmierczak.jan.service;

import kazmierczak.jan.model.ticket.repository.TicketRepository;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.model.user.dto.UserToActivateDto;
import kazmierczak.jan.model.user.repository.UserRepository;
import kazmierczak.jan.model.verification_token.repository.VerificationTokenRepository;
import kazmierczak.jan.types.Role;
import kazmierczak.jan.user.EventPublisher;
import kazmierczak.jan.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

        @MockBean
        public VerificationTokenRepository verificationTokenRepository;

        @MockBean
        public TicketRepository ticketRepository;

        @Bean
        public UserService userService() {
            return new UserService(
                    userRepository,
                    ticketRepository,
                    passwordEncoder,
                    eventPublisher,
                    verificationTokenRepository
            );
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

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Test
    @DisplayName("testing findAll method")
    public void test1() {
        when(userRepository.findAll())
                .thenReturn(of(
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

        assertThat(userService.findAll())
                .hasSize(1)
                .containsExactly(
                        GetUserDto
                                .builder()
                                .id(1L)
                                .username("Username")
                                .email("email@gmail.com")
                                .age(18)
                                .role(Role.USER)
                                .build()
                );
    }

    @Test
    @DisplayName("testing findById method")
    public void test2() {
        when(userRepository.findById(1L))
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

        assertThat(userService.findById(1L))
                .isEqualTo(
                        GetUserDto
                                .builder()
                                .id(1L)
                                .username("Username")
                                .email("email@gmail.com")
                                .age(18)
                                .role(Role.USER)
                                .build()
                );
    }

    @Test
    @DisplayName("testing deleteById method")
    public void test3() {
       /* when(userRepository.delete(1L))
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

        assertThat(userService.deleteById(1L))
                .isEqualTo(GetUserDto
                        .builder()
                        .id(1L)
                        .username("Username")
                        .email("email@gmail.com")
                        .age(18)
                        .role(Role.USER)
                        .build()
                );*/
    }

    //TODO: TESTUJ
    @Test
    @DisplayName("testing createUser method")
    public void test4() {
       /* var user = User
                .builder()
                .id(1L)
                .username("Username")
                .age(12)
                .email("email@wp.pl")
                .password("password")
                .role(Role.USER)
                .enabled(false)
                .build();

        var userDto = CreateUserDto
                .builder()
                .username("Username")
                .age(12)
                .email("email@wp.pl")
                .password("password")
                .role(Role.USER)
                .build();

        var userResponseDto = CreateUserResponseDto
                .builder()
                .id(1L)
                .username("Username")
                .build();

        when(userService.createUser(userDto))
                .thenReturn(userResponseDto);*/
    }

    @Test
    @DisplayName("testing countPurchasedTickets method")
    public void test5() {

    }
}
