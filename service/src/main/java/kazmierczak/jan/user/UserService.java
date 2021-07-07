package kazmierczak.jan.user;

import kazmierczak.jan.user.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import kazmierczak.jan.model.user.User;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import kazmierczak.jan.model.user.dto.CreateUserResponseDto;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.model.user.dto.UserToActivateDto;
import kazmierczak.jan.model.user.dto.validator.CreateUserDtoValidator;
import kazmierczak.jan.model.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static kazmierczak.jan.config.validator.Validator.*;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventPublisher<UserToActivateDto> eventPublisher;

    /**
     * @param createUserDto object we want to build responseDto from
     * @return created UserResponseDto object
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateUserResponseDto createUser(CreateUserDto createUserDto) {
        validate(new CreateUserDtoValidator(), createUserDto);

        var username = createUserDto.getUsername();
        if(userRepository.findByUsername(username).isPresent()) {
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
    public GetUserDto deleteById(Long id) {
        return userRepository
                .delete(id)
                .map(User::toGetUserDto)
                .orElseThrow(() -> new UserServiceException("Cannot find user with this id: " + id));
    }
}
