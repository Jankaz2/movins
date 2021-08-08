package kazmierczak.jan.controller;

import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.model.user.dto.CreateUserDto;
import kazmierczak.jan.model.user.dto.CreateUserResponseDto;
import kazmierczak.jan.model.user.dto.GetUserDto;
import kazmierczak.jan.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kazmierczak.jan.controller.dto.ResponseDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    /**
     * @return list of all users
     */
    @GetMapping
    public ResponseDto<List<GetUserDto>> getAllUsers() {
        return toResponse(userService.findAll());
    }

    /**
     * @param createUserDto body of user we want to post
     * @return posted user object
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto<CreateUserResponseDto> createUser(@RequestBody CreateUserDto createUserDto) {
        return toResponse(userService.createUser(createUserDto));
    }

    @GetMapping("/activation")
    public ResponseDto<Long> activate(@RequestParam("token") String token) {
        return toResponse(userService.activateUser(token));
    }

    /**
     * @param id of user we want to get
     * @return userDto with this id
     */
    @GetMapping("/{id}")
    public ResponseDto<GetUserDto> getUserById(@PathVariable Long id) {
        return toResponse(userService.findById(id));
    }

    /**
     * @param id of user we want to delete
     * @return deleted object
     */
    @DeleteMapping("/{id}")
    public ResponseDto<GetUserDto> deleteUserById(@PathVariable Long id) {
        return toResponse(userService.deleteById(id));
    }

    /**
     *
     * @param id of user we want to count tickets purchased by
     * @return number of tickets
     */
    @GetMapping("/purchase/{id}")
    public ResponseDto<Integer> purchasedTickets(@PathVariable Long id) {
        return toResponse(userService.countPurchasedTickets(id));
    }
}
