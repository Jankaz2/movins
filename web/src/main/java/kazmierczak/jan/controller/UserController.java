package kazmierczak.jan.controller;

import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.user.UserService;
import lombok.RequiredArgsConstructor;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import kazmierczak.jan.model.user.dto.CreateUserResponseDto;
import kazmierczak.jan.model.user.dto.GetUserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    /**
     * @return list of all users
     */
    @GetMapping
    public ResponseDto<List<GetUserDto>> getAllUsers() {
        return ResponseDto
                .<List<GetUserDto>>builder()
                .data(userService.findAll())
                .build();
    }

    /**
     * @param createUserDto body of user we want to post
     * @return posted user object
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateUserResponseDto> createUser(@RequestBody CreateUserDto createUserDto) {
        return ResponseDto
                .<CreateUserResponseDto>builder()
                .data(userService.createUser(createUserDto))
                .build();
    }

    /**
     * @param id of user we want to get
     * @return userDto with this id
     */
    @GetMapping("/{id}")
    public ResponseDto<GetUserDto> getUserById(@PathVariable Long id) {
        return ResponseDto
                .<GetUserDto>builder()
                .data(userService.findById(id))
                .build();
    }

    /**
     *
     * @param id of user we want to delete
     * @return deleted object
     */
    @DeleteMapping("/{id}")
    public ResponseDto<GetUserDto> deleteUserById(@PathVariable Long id) {
        return ResponseDto
                .<GetUserDto>builder()
                .data(userService.deleteById(id))
                .build();
    }
}
