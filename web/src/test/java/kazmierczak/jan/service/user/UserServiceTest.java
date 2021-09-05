package kazmierczak.jan.service.user;

import kazmierczak.jan.model.ticket.repository.TicketRepository;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.ForgotPasswordDto;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.model.user.dto.UserToActivateDto;
import kazmierczak.jan.model.user.repository.UserRepository;
import kazmierczak.jan.model.verification_token.VerificationToken;
import kazmierczak.jan.model.verification_token.repository.VerificationTokenRepository;
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

import static java.time.LocalDateTime.now;
import static java.util.List.of;
import static kazmierczak.jan.types.Role.ROLE_USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
        public EventPublisher<ForgotPasswordDto> eventPublisherPassword;


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
                    eventPublisherPassword,
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
    private EventPublisher<ForgotPasswordDto> eventPublisherPassword;

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
                                .role(ROLE_USER)
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
                                .role(ROLE_USER)
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
                                .role(ROLE_USER)
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
                                .role(ROLE_USER)
                                .build()
                );
    }

    @Test
    @DisplayName("testing deleteById method")
    public void test3() {
        var user = User
                .builder()
                .id(1L)
                .username("Username")
                .email("email@gmail.com")
                .age(18)
                .password("password")
                .enabled(true)
                .role(ROLE_USER)
                .tickets(new ArrayList<>())
                .build();

        var verificationToken = VerificationToken
                .builder()
                .id(1L)
                .token("token")
                .dateTime(now())
                .user(user)
                .build();


        when(verificationTokenRepository.findById(1L))
                .thenReturn(Optional.of(verificationToken));

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(verificationTokenRepository.deleteByUserId(1L))
                .thenReturn(Optional.of(verificationToken));

        assertThat(userService.deleteById(1L))
                .isEqualTo(user.toGetUserDto());
    }

    @Test
    @DisplayName("testing createUser method")
    public void test4() {
        var user = User
                .builder()
                .id(1L)
                .username("Username")
                .email("email@wp.pl")
                .age(12)
                .password("password")
                .role(ROLE_USER)
                .tickets(new ArrayList<>())
                .enabled(true)
                .build();

        var userDto = CreateUserDto
                .builder()
                .username("Username")
                .age(12)
                .email("email@wp.pl")
                .password("password")
                .role(ROLE_USER)
                .build();

        when(userRepository.add(any(User.class)))
                .thenReturn(Optional.of(user));

        var createdUser = userService.createUser(userDto);

        assertThat(createdUser.getUsername())
                .isEqualTo("Username");

        assertThat(createdUser.getId())
                .isEqualTo(1L);

        assertThat(createdUser.getUsername())
                .isNotEqualTo("Username2");
    }
}