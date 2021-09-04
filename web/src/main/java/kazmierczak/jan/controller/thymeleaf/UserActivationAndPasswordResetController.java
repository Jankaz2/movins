package kazmierczak.jan.controller.thymeleaf;

import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.model.user.dto.ChangePasswordDto;
import kazmierczak.jan.model.user.dto.CreateUserResponseDto;
import kazmierczak.jan.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static kazmierczak.jan.controller.dto.ResponseDto.toResponse;

@Controller
@RequiredArgsConstructor
public class UserActivationAndPasswordResetController {
    private final UserService userService;

    /**
     * @param token to activate user
     * @return activated user
     */
    @GetMapping(value = "/users/activation")
    public String get(@RequestParam("token") String token) {
        userService.activateUser(token);
        return "userActivation";
    }

    /**
     * @param token to activate user
     * @return activated user
     */
    @GetMapping(value = "/users/reset")
    public String changePasswordEmail(@RequestParam("token") String token, Model model) {
        userService.confirmResetToken(token);
        model.addAttribute("token", token);
        return "resetPassword";
    }

    /**
     * @param request we get
     * @return response dto object of user who updated his password
     */
    @PostMapping(value = "/users/reset")
    public String resetPassword(HttpServletRequest request) {
        var token = request.getParameter("token");
        var password = request.getParameter("password");

        var changePasswordDto = ChangePasswordDto
                .builder()
                .token(token)
                .password(password)
                .repeatPassword(password)
                .build();

        userService.resetPassword(changePasswordDto);
        return "goToLoginPage";
    }
}
