package kazmierczak.jan.controller;

import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.user.UserService;
import lombok.RequiredArgsConstructor;
import model.user.dto.CreateUserDto;
import model.user.dto.CreateUserResponseDto;
import model.user.dto.GetUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cinema")
public class UserController {
    private final UserService userService;

    /**
     * @return list of all users
     */
    @GetMapping("/user")
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
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateUserResponseDto> createUser(@RequestBody CreateUserDto createUserDto) {
        return ResponseDto
                .<CreateUserResponseDto>builder()
                .data(userService.createUser(createUserDto))
                .build();
    }
}
