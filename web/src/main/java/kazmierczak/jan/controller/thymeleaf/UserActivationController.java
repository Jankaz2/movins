package kazmierczak.jan.controller.thymeleaf;

import kazmierczak.jan.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserActivationController {
    private final UserService userService;

    /**
     *
     * @param token to activate user
     * @return activated user
     */
    @GetMapping(value = "/users/activation")
    public String get(@RequestParam("token") String token) {
        userService.activateUser(token);
        return "userActivation";
    }
}
