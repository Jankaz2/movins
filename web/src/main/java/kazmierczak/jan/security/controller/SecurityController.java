package kazmierczak.jan.security.controller;

import kazmierczak.jan.controller.dto.ResponseDto;
import kazmierczak.jan.security.dto.RefreshTokenDto;
import kazmierczak.jan.security.tokens.MovinsTokensService;
import kazmierczak.jan.security.tokens.dto.TokensDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kazmierczak.jan.controller.dto.ResponseDto.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/security")
public class SecurityController {
    private final MovinsTokensService movinsTokensService;

    @PostMapping("/refresh-tokens")
    public ResponseDto<TokensDto> refreshTokens(@RequestBody RefreshTokenDto refreshTokenDto){
        return toResponse(movinsTokensService.refreshTokens(refreshTokenDto));
    }
}
