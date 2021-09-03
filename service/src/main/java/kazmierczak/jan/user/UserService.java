package kazmierczak.jan.user;

import kazmierczak.jan.model.ticket.repository.TicketRepository;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.*;
import kazmierczak.jan.model.user.dto.validator.CreateUserDtoValidator;
import kazmierczak.jan.model.user.repository.UserRepository;
import kazmierczak.jan.model.verification_token.VerificationToken;
import kazmierczak.jan.model.verification_token.repository.VerificationTokenRepository;
import kazmierczak.jan.user.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static kazmierczak.jan.config.validator.Validator.validate;
import static kazmierczak.jan.model.user.UserUtils.toId;
import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventPublisher<UserToActivateDto> eventPublisher;
    private final EventPublisher<ForgotPasswordDto> changePasswordEventPublisher;
    private final VerificationTokenRepository verificationTokenRepository;

    /**
     * @param createUserDto object we want to build responseDto from
     * @return created UserResponseDto object
     */
    @Transactional(isolation = SERIALIZABLE)
    public CreateUserResponseDto createUser(CreateUserDto createUserDto) {
        validate(new CreateUserDtoValidator(), createUserDto);

        var username = createUserDto.getUsername();
        if (userRepository.findByUsername(username).isPresent()) {
            throw new UserServiceException("User with this username: " + username + " already exists");
        }

        var email = createUserDto.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserServiceException("User with email " + email + " already exists");
        }

        createUserDto.setPassword(passwordEncoder.encode(createUserDto.getPassword()));

        var user = createUserDto.toUser();
        var insertedUser = userRepository
                .add(user)
                .map(User::toCreateUserResponseDto)
                .orElseThrow(() -> new UserServiceException("Cannot create new user"));

        eventPublisher.publishEvent(UserToActivateDto.builder().id(insertedUser.getId()).build());
        return insertedUser;
    }

    /**
     *
     * @param forgotPasswordDto - email of the use who wants to reset password
     * @return dto response of this user
     */
    public CreateUserResponseDto getEmailToResetPassword(ForgotPasswordDto forgotPasswordDto) {
        var email = forgotPasswordDto.getEmail();
        if (email == null) {
            throw new UserServiceException("Email is null");
        }

        if (userRepository.findByEmail(email).isEmpty()) {
            throw new UserServiceException("User with this email -> [" + email + "] does not exist");
        }

        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserServiceException("Cannot find user with this email: " + email));
        user.deactivate();
        userRepository.add(user);

        changePasswordEventPublisher.publishEvent(ForgotPasswordDto.builder().email(email).build());

        return user.toCreateUserResponseDto();
    }

    /**
     *
     * @param token to reset password
     * @return id of activated user if token is valid
     */
    public Long confirmResetToken(String token) {
        if (token == null) {
            throw new UserServiceException("Token is null");
        }

        if (verificationTokenRepository.findByToken(token).isEmpty()) {
            throw new UserServiceException("This token does not exist: " + token);
        }

        return activateUser(token);
    }

    /**
     *
     * @param changePasswordDto data to change password
     * @return dto response of user who changed password
     */
    public CreateUserResponseDto resetPassword(ChangePasswordDto changePasswordDto) {
        if (changePasswordDto == null) {
            throw new UserServiceException("changePasswordDto object is null");
        }

        var token = changePasswordDto.getToken();
        if (verificationTokenRepository.findByToken(token).isEmpty()) {
            throw new UserServiceException("Cannot find this token: " + token);
        }

        var verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new UserServiceException("Cannot find verification token with this token: " + token));

        var userId = verificationToken.getUserId();
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserServiceException("Cannot find user with this id: " + userId));
        var newPassword = changePasswordDto.getPassword();

        user.setPassword(passwordEncoder.encode(newPassword));
        var updatedUser = userRepository
                .add(user)
                .orElseThrow(() -> new UserServiceException("Cannot add updatedUser"));

        return updatedUser.toCreateUserResponseDto();
    }

    /**
     * @param token we want to activate user by
     * @return id of activated user
     */
    @Transactional(isolation = SERIALIZABLE)
    public Long activateUser(String token) {
        if (token == null) {
            throw new UserServiceException("Activation token is null");
        }

        return verificationTokenRepository
                .findByToken(token)
                .filter(VerificationToken::isValid)
                .flatMap(verificationToken -> userRepository
                        .findById(verificationToken.getUserId())
                        .map(user -> {
                            user.activate();
                            return userRepository
                                    .add(user)
                                    .map(toId)
                                    .orElseThrow(() -> new UserServiceException("Cannot activate user"));
                        }))
                .orElseThrow(() -> new UserServiceException("User activation failed"));
    }

    /**
     * @return all getUserDto objects
     */
    public List<GetUserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(User::toGetUserDto)
                .collect(toList());
    }

    /**
     * @param id we want to find user with
     * @return user with this id if this user exists, otherwise
     * throw exception
     */
    public GetUserDto findById(Long id) {
        return userRepository
                .findById(id)
                .map(User::toGetUserDto)
                .orElseThrow(() -> new UserServiceException("Cannot find user with this id: " + id));
    }

    /**
     * @param id of user we want to delete
     * @return deleted object
     */
    @Transactional
    public GetUserDto deleteById(Long id) {
        var userDto = userRepository
                .findById(id)
                .map(User::toGetUserDto)
                .orElseThrow(() -> new UserServiceException("Cannot find user with this id: " + id));

        verificationTokenRepository
                .deleteByUserId(id)
                .orElseThrow();

        return userDto;
    }

    /**
     * @param id of user we want to count tickets
     * @return number of purchased tickets by this user
     */
    public Integer countPurchasedTickets(Long id) {
        return ticketRepository
                .findTicketsByUserId(id)
                .size();
    }

    /**
     * @param username we want to find user by
     * @return user dto
     */
    public GetUserDto findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .map(User::toGetUserDto)
                .orElseThrow(() -> new UserServiceException("Cannot find user with this username: " + username));
    }
}
