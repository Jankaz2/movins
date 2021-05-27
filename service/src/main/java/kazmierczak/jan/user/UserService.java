package kazmierczak.jan.user;

import kazmierczak.jan.user.exception.UserServiceException;
import lombok.RequiredArgsConstructor;
import model.user.User;
import model.user.dto.CreateUserDto;
import model.user.dto.CreateUserResponseDto;
import model.user.dto.GetUserDto;
import model.user.dto.validator.CreateUserDtoValidator;
import model.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static config.validator.Validator.*;
import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    /**
     * @param createUserDto object we want to build responseDto from
     * @return created UserResponseDto object
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public CreateUserResponseDto createUser(CreateUserDto createUserDto) {
        validate(new CreateUserDtoValidator(), createUserDto);

        var email = createUserDto.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserServiceException("User with email " + email + " already exists");
        }

        var user = createUserDto.toUser();

        return userRepository
                .add(user)
                .map(User::toCreateUserResponseDto)
                .orElseThrow(() -> new UserServiceException("Cannot create new user"));
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
     *
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
     *
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
