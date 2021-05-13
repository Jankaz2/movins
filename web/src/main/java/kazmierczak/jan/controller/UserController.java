package kazmierczak.jan.controller;

import config.validator.Validator;
import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.user.UserService;
import lombok.RequiredArgsConstructor;
import model.user.dto.CreateUserDto;
import model.user.dto.CreateUserResponseDto;
import model.user.dto.GetUserDto;
import model.user.dto.validator.CreateUserDtoValidator;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static config.validator.Validator.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cinema")
public class UserController {
    private final UserService userService;

    /**
     * @return list of all users
     */
    @GetMapping("/users")
    public ResponseDto<List<GetUserDto>> getAllUsers() {
        return ResponseDto
                .<List<GetUserDto>>builder()
                .data(userService.findAll())
                .build();
    }

    /**
     *
     * @param createUserDto body of user we want to post
     * @return posted user object
     */
    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateUserResponseDto> createUser(@RequestBody CreateUserDto createUserDto) {
        validate(new CreateUserDtoValidator(), createUserDto);
        return ResponseDto
                .<CreateUserResponseDto>builder()
                .data(userService.createUser(createUserDto))
                .build();
    }
}
